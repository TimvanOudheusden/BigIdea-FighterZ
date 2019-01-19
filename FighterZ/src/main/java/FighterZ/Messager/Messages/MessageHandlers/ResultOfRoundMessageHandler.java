package FighterZ.Messager.Messages.MessageHandlers;

import FighterZ.Messager.client.IGameClient;
import Server.Messager.Messagehandlers.IMessageHandler;
import org.json.JSONObject;

public class ResultOfRoundMessageHandler implements IMessageHandler {

    private IGameClient gameClient;

    public ResultOfRoundMessageHandler(IGameClient gameClient){
            this.gameClient = gameClient;
    }

    public void handleMessage(JSONObject playerMessage, String sessionId) {
        gameClient.processResultOfRound(playerMessage.getString("DamageReceiver"), playerMessage.getInt("Damage"));
    }
}
