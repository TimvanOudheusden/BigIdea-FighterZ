package FighterZ.Messager.client;

import FighterZ.Messager.Messages.LoginPlayerMessage;
import FighterZ.Messager.Messages.PlayerAttackMessage;
import FighterZ.Messager.Messages.RegisterPlayerMessage;
import FighterZ.Models.AttackModels.Attack;
import Server.Websockets.IClientWebsocket;

import javax.websocket.EncodeException;

public class ClientMessageCreator implements IClientMessageCreator {

    private IClientWebsocket clientSocket;

    public ClientMessageCreator(IClientWebsocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void loginPlayer(String username, String password) throws EncodeException {
        clientSocket.send(new LoginPlayerMessage(username, password));
    }

    public void registerPlayer(String username, String password) throws EncodeException{
        clientSocket.send(new RegisterPlayerMessage(username, password));
    }

    public void sendPlayerAttack(Attack attack) throws EncodeException {

        clientSocket.send(new PlayerAttackMessage(attack));
    }
}
