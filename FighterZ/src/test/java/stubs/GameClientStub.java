package stubs;

import FighterZ.Messager.client.IClientGUI;
import FighterZ.Messager.client.IGameClient;
import FighterZ.Models.AttackModels.Attack;

import javax.websocket.EncodeException;

public class GameClientStub implements IGameClient {

    @Override
    public void loginPlayer(String username, String password) throws EncodeException {

    }

    @Override
    public void handleLoginResponse(boolean success) {

    }

    @Override
    public void registerPlayer(String username, String password) throws EncodeException {

    }

    @Override
    public void handlePlayerRegistered(String username) {

    }

    @Override
    public void handlePlayerRegistrationResponse(boolean success) {

    }

    @Override
    public void registerClientGUI(IClientGUI gui) {

    }

    @Override
    public void sendPlayerAttack(Attack attack) throws EncodeException {

    }

    @Override
    public void processStartOfRound() {

    }

    @Override
    public void processResultOfRound(String damageReceiver, int damage) {

    }

    @Override
    public boolean processLethal() {
        return true;
    }
}
