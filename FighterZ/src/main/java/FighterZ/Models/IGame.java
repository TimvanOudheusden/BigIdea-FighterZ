package FighterZ.Models;

import FighterZ.Models.AttackModels.Attack;
import FighterZ.Models.Enums.GameState;

public interface IGame {
    void loginPlayer(String userName, String password, String sessionId);

    void registerNewPlayer(String username, String password, String sessionId);

    void processPlayerAttack(String sessionId, Attack attack);

    void processClientDisconnect(String sessionId);

    int getNumberOfPlayers();

    GameState getGameState();

    IRound getCurrentRound();

    IPlayer getPlayerWithSession(String sessionId);

}
