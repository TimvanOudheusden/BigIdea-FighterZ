package Server.Websockets;

import javax.websocket.EncodeException;

public interface IClientWebsocket {

    void onWebsocketMessageReceived(String message, String sessionId);

    void send(Object o) throws EncodeException;

}
