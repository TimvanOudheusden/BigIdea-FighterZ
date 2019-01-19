package Server.Websockets;

import Server.Messager.ServerMessageProcessor;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.inject.Singleton;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.*;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;

@Singleton
@ServerEndpoint(value="/FighterZ/")
public class ServerWebsocket implements IServerWebsocket {

    private static ArrayList<Session> sessions = new ArrayList<>();
    private ServerMessageProcessor processor;

    public ServerWebsocket(){

    }

    public void setMessageProcessor(ServerMessageProcessor processor)
    {
        this.processor = processor;
    }

    public ServerMessageProcessor getProcessor(){
        return processor;
    }

    @OnOpen
    public void onConnect(Session session) {
        sessions.add(session);
    }

    @OnMessage
    public void onText(String message, Session session) {
        try{
            System.out.println(message);
            String sessionId = session.getId();
            getProcessor().processMessage(message, sessionId);
        }
        catch (JsonSyntaxException e){
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println(reason);
        getProcessor().handleDisconnect(session.getId());
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) throws IOException {
        session.close();
        System.out.println(cause);
    }

    public void sendTo(String sessionId, Object o) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(o);
        sendToClient(getSessionFromId(sessionId), jsonString);
    }

    public void broadcast(Object object) {
        for(Session session : sessions) {
            sendTo(session.getId(), object);
        }
    }

    public void sendToOthers(String sessionId, Object object) {
        Session session = getSessionFromId(sessionId);
        for(Session ses : sessions) {
            if(!ses.getId().equals(session.getId()))
                sendTo(ses.getId(), object);
        }
    }

    public Session getSessionFromId(String sessionId) {
        for(Session s : sessions)
        {
            if(s.getId().equals(sessionId))
                return s;
        }
        return null;
    }

    private void sendToClient(Session session, String message)
    {
        try {
            session.getBasicRemote().sendText(message);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public void start() {

    }

    public void stop() {

    }
}
