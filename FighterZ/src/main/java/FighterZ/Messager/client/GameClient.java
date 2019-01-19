package FighterZ.Messager.client;

import FighterZ.Models.AttackModels.Attack;
import javax.websocket.EncodeException;

public class GameClient implements IGameClient {

    private IClientMessageCreator clientMessageCreator;

    private IClientGUI clientGUI;

    public void registerClientGUI(IClientGUI gui)
    {
        this.clientGUI = gui;
    }

    public GameClient(IClientMessageCreator clientMessageCreator){
        this.clientMessageCreator = clientMessageCreator;
    }

    public void loginPlayer(String username, String password) throws EncodeException {
        clientMessageCreator.loginPlayer(username, password);
    }

    public void handleLoginResponse(boolean success) {
        clientGUI.processLoginResponse(success);
    }

    public void registerPlayer(String username, String password) throws EncodeException{
        clientMessageCreator.registerPlayer(username, password);
    }

    public void handlePlayerRegistered(String username) {
        clientGUI.processPlayerRegisteredResponse(username);
    }

    public void handlePlayerRegistrationResponse(boolean success) {
        clientGUI.processRegistrationResponse(success);
    }


    public void sendPlayerAttack(Attack attack) throws EncodeException {
        clientMessageCreator.sendPlayerAttack(attack);
    }

    public void processStartOfRound() {
        clientGUI.processStartOfRound();
    }

    public void processResultOfRound(String damageReceiver, int damage) {
        clientGUI.processResultOfRound(damageReceiver, damage);
    }

    public boolean processLethal() {
        //not implemented
        return true;
    }
}
