package FighterZ.Models;

import FighterZ.Models.AttackModels.Attack;
import FighterZ.Models.AttackModels.LightAttack;
import FighterZ.Models.Enums.AttackType;
import FighterZ.Models.Enums.GameState;
import Server.Messager.Messagehandlers.IServerMessageCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import stubs.ServerMessageCreatorStub;

import static org.junit.Assert.*;

public class GameTest {

    private IPlayer p1;
    private IPlayer p2;
    private Attack p1Attack;
    private Attack p2Attack;

    IServerMessageCreator messageCreator;
    IGame game;

    @Before
    public void prepareTest(){
        p1 = new Player("1","PlayerOne1", "1234");
        p2 = new Player("2","PlayerTwo1", "5678");

        p1Attack = new LightAttack();
        p2Attack = new LightAttack();

        messageCreator = new ServerMessageCreatorStub();
        game = new Game(messageCreator);
    }

    @Test
    public void loginPlayer() {
        game.loginPlayer("playerTwo2", p2.getPassword(), p2.getSessionId());
        Assert.assertEquals(1, game.getNumberOfPlayers());
        Assert.assertEquals(GameState.WAITINGFORPLAYERS, game.getGameState());
    }

    @Test
    public void registerNewPlayer() {
         game.registerNewPlayer(p1.getName(), p1.getSessionId(), p1.getPassword());
         Assert.assertEquals(1, game.getNumberOfPlayers());
         Assert.assertEquals(GameState.WAITINGFORPLAYERS, game.getGameState());
    }

    @Test
    public void processPlayerAttack() {

        game.loginPlayer(p1.getName(), p1.getPassword(), p1.getSessionId());
        game.loginPlayer(p2.getName(), p2.getPassword(), p2.getSessionId());

        game.processPlayerAttack("1", p1Attack);
        game.processPlayerAttack("2", p2Attack);
        Assert.assertEquals(game.getCurrentRound().getDamageReceiver().getName(), "Spelertje2");
    }

    @Test
    public void processClientDisconnect() {
        game.registerNewPlayer("Test", "Test123", "12");
        game.registerNewPlayer("Test2", "Test1234", "13");
        game.processClientDisconnect("13");
    }

    @Test
    public void getNumberOfPlayers() {
        Assert.assertEquals(game.getNumberOfPlayers(), 0);
    }
}