package Server.Messager.Messagehandlers;

import FighterZ.Models.IGame;
import org.json.JSONObject;

public class RegisterPlayerMessageHandler implements IMessageHandler {

    private IGame game;

    public RegisterPlayerMessageHandler(IGame game){
        this.game = game;
    }

    public void handleMessage(JSONObject playerMessage, String sessionId){
        game.registerNewPlayer(playerMessage.getString("Username"), playerMessage.getString("Password"), sessionId);
    }
}
