package Server.Messager.Messagehandlers;

import FighterZ.Models.IGame;
import org.json.JSONObject;

public class LoginPlayerMessageHandler implements IMessageHandler {

    private IGame game;

    public LoginPlayerMessageHandler(IGame game){
        this.game = game;
    }

    public void handleMessage(JSONObject playerMessage, String sessionId){
        game.loginPlayer(playerMessage.getString("Username"), playerMessage.getString("Password"), sessionId);
    }
}
