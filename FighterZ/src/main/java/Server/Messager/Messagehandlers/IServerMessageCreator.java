package Server.Messager.Messagehandlers;

public interface IServerMessageCreator {

    void notifyPlayerAdded(String username);

    void notifyResultOfRegistration(String sessionId, boolean success);

    void notifyStartRound();

    void notifyRoundEnded(String damageReceiver, int dmg);

    void notifyResultOfLogin(String sessionId, boolean success);

}
