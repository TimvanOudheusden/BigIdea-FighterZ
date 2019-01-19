package FighterZ.Messager.Messages.MessageHandlers;

import FighterZ.Messager.client.IGameClient;
import Server.Messager.Messagehandlers.IMessageHandler;
import org.json.JSONObject;

public class ResultOfRegistrationMessageHandler implements IMessageHandler {
    private IGameClient gameClient;

    public ResultOfRegistrationMessageHandler(IGameClient gameClient){
        this.gameClient = gameClient;
    }

    public void handleMessage(JSONObject playerMessage, String sessionId) {
        gameClient.handlePlayerRegistrationResponse(playerMessage.getBoolean("Result"));
    }
}
