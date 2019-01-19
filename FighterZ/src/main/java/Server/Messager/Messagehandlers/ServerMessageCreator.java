package Server.Messager.Messagehandlers;

import FighterZ.Messager.Messages.*;
import Server.Websockets.IServerWebsocket;

public class ServerMessageCreator implements IServerMessageCreator {

    private IServerWebsocket websocket;

    public ServerMessageCreator(IServerWebsocket serverSocket) {
        this.websocket = serverSocket;
    }

    public void notifyPlayerAdded(String username) {
        PlayerHasRegisteredMessage message = new PlayerHasRegisteredMessage(username);
        websocket.broadcast(message);
    }

    public void notifyResultOfRegistration(String sessionId, boolean success) {
        ResultOfRegistrationMessage message = new ResultOfRegistrationMessage(success);
        websocket.sendTo(sessionId, message);
    }

    public void notifyStartRound() {
        websocket.broadcast(new StartOfRoundMessage());
    }

    public void notifyRoundEnded(String damageReceiver, int dmg) {
        ResultOfRoundMessage message = new ResultOfRoundMessage(damageReceiver, dmg);
        websocket.broadcast(message);
    }

    public void notifyResultOfLogin(String sessionId, boolean success) {
        ResultOfLoginMessage message = new ResultOfLoginMessage(success);
        websocket.sendTo(sessionId, message);
    }
}
