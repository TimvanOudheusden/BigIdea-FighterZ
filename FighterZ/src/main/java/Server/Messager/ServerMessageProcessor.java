package Server.Messager;

import FighterZ.Models.IGame;
import Server.Messager.Messagehandlers.IMessageHandler;
import org.json.JSONObject;

public class ServerMessageProcessor implements IServerMessageProcessor {

    private IGame game;
    private MessageDecoderFactory messageDecoderFactory;

    public ServerMessageProcessor(MessageDecoderFactory messageDecoderFactory){

        this.messageDecoderFactory = messageDecoderFactory;
    }

    public void registerGame(IGame game)
    {
        this.game = game;
    }

    public IGame getGame()
    {
        return game;
    }

    public MessageDecoderFactory getMessageDecoderFactory() {
        return messageDecoderFactory;
    }

    public void processMessage(String message, String sessionId) {
        JSONObject playerMessage = new JSONObject(message);
        IMessageHandler messageHandler = getMessageDecoderFactory().getMessageHandler(playerMessage, getGame());
        messageHandler.handleMessage(playerMessage, sessionId);
    }

    public void handleDisconnect(String sessionId)
    {
        getGame().processClientDisconnect(sessionId);
    }
}
