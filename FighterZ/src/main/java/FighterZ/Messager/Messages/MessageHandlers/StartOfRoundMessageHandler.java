package FighterZ.Messager.Messages.MessageHandlers;

import FighterZ.Messager.client.IGameClient;
import Server.Messager.Messagehandlers.IMessageHandler;
import org.json.JSONObject;

public class StartOfRoundMessageHandler implements IMessageHandler {

    private IGameClient gameClient;

    public StartOfRoundMessageHandler(IGameClient gameClient){
        this.gameClient = gameClient;
    }

    public void handleMessage(JSONObject playerMessage, String sessionId) {
        gameClient.processStartOfRound();
    }
}
