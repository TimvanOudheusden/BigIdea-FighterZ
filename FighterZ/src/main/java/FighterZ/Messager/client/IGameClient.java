package FighterZ.Messager.client;

import FighterZ.Models.AttackModels.Attack;
import javax.websocket.EncodeException;

public interface IGameClient {

    void loginPlayer(String username, String password) throws EncodeException;

    void handleLoginResponse(boolean success);

    void registerPlayer(String username, String password) throws EncodeException;

    void handlePlayerRegistered(String username);

    void handlePlayerRegistrationResponse(boolean success);

    void registerClientGUI(IClientGUI gui);

    void sendPlayerAttack(Attack attack) throws EncodeException;

    void processStartOfRound();

    void processResultOfRound(String damageReceiver, int damage);

    boolean processLethal();
}
