package Server.Websockets;

import FighterZ.Messager.client.ClientMessageProcessor;
import com.google.gson.Gson;
import org.json.JSONException;
import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class ClientWebsocket implements IClientWebsocket{

    private String serverUri = "ws://localhost:8080/FighterZ/";

    private Session session;

    private static ClientWebsocket instance = null;
    private ClientMessageProcessor processor;

    public static ClientWebsocket getInstance(){
        if (instance == null) {
            instance = new ClientWebsocket();
        }
        return instance;
    }

    public void setMessageProcessor(ClientMessageProcessor processor)
    {
        this.processor = processor;
    }

    public ClientMessageProcessor getProcessor(){
        return processor;
    }

    public void start() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(serverUri));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void stop() {
        try {
            if(session != null)
                session.close();

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @OnOpen
    public void onWebsocketConnect(Session session){
        this.session = session;
    }

    @OnMessage
    public void onWebSocketMessage(String message, Session session){
        onWebsocketMessageReceived(message, session.getId());
    }

    @OnError
    public void onWebSocketError(Session session, Throwable cause) {
        System.out.println(cause);
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason){
        session = null;
    }

    public void onWebsocketMessageReceived(String message, String sessionId) {
        getProcessor().processMessage(message, sessionId);
    }

    public void send(Object o) throws EncodeException {
        Gson gson = new Gson();
        String jsonString = gson.toJson(o);
        try {
            sendMessageToServer(jsonString);
        }
        catch (JSONException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private void sendMessageToServer(String message) throws EncodeException {
        try {
            session.getBasicRemote().sendText(message);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
