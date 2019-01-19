package FighterZ.Messager.Messages.MessageHandlers;

import FighterZ.Messager.client.IGameClient;
import FighterZ.Models.IGame;
import Server.Messager.Messagehandlers.IMessageHandler;
import org.json.JSONObject;

public class PlayerHasRegisteredMessageHandler implements IMessageHandler {

    private IGameClient gameClient;

    public PlayerHasRegisteredMessageHandler(IGameClient gameClient){
        this.gameClient = gameClient;
    }

    public void handleMessage(JSONObject playerMessage, String sessionId) {
        gameClient.handlePlayerRegistered(playerMessage.getString("Username"));
    }
}
