package stubs;

import Server.Messager.Messagehandlers.IServerMessageCreator;

public class ServerMessageCreatorStub implements IServerMessageCreator {

    @Override
    public void notifyPlayerAdded(String username) {

    }

    @Override
    public void notifyResultOfRegistration(String sessionId, boolean success) {

    }

    @Override
    public void notifyStartRound() {

    }

    @Override
    public void notifyRoundEnded(String damageReceiver, int dmg) {

    }

    @Override
    public void notifyResultOfLogin(String sessionId, boolean success) {

    }
}
