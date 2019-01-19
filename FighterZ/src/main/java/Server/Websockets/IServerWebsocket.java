package Server.Websockets;

public interface IServerWebsocket{

    void sendTo(String sessionId, Object object);

    void broadcast(Object object);

    void sendToOthers(String sessionId, Object object);

}
