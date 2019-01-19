package FighterZ.Messager.client;

import Server.Messager.Messagehandlers.IMessageHandler;
import org.json.JSONObject;

public class ClientMessageProcessor {

    private IGameClient gameClient;
    private ClientMessageDecoderFactory factory;

    public ClientMessageProcessor(ClientMessageDecoderFactory factory)
    {
        this.factory = factory;
    }

    public void registerGameClient(IGameClient gameClient)
    {
        this.gameClient = gameClient;
    }

    public ClientMessageDecoderFactory getMessageDecoderFactory() {
        return factory;
    }

    public void processMessage(String message, String sessionId) {
        JSONObject playerMessage = new JSONObject(message);
        IMessageHandler messageHandler = getMessageDecoderFactory().getMessageHandler(playerMessage, gameClient);
        messageHandler.handleMessage(playerMessage, sessionId);
    }
}
