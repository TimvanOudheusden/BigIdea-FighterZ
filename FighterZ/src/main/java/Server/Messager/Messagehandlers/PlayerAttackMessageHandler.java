package Server.Messager.Messagehandlers;

import FighterZ.Models.IGame;
import org.json.JSONObject;

public class PlayerAttackMessageHandler implements IMessageHandler {

    private IGame game;
    private PlayerAttackMessageFactory factory;

    public PlayerAttackMessageHandler(IGame game){
        this.game = game;
        this.factory = new PlayerAttackMessageFactory();
    }

    public void handleMessage(JSONObject playerMessage, String sessionId){
        game.processPlayerAttack(sessionId, factory.getTypeOfAttack(playerMessage.getString("attackType")));
    }
}
