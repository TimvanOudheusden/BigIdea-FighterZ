package FighterZ.Messager.client;

import FighterZ.Messager.Messages.MessageHandlers.*;
import Server.Messager.IMessageDecoderFactory;
import Server.Messager.Messagehandlers.IMessageHandler;
import org.json.JSONObject;

public class ClientMessageDecoderFactory implements IMessageDecoderFactory {

    public IMessageHandler getMessageHandler(JSONObject playerMessage, Object game) {
        String messageType = playerMessage.getString("messageType");
        IGameClient gameClient = (IGameClient)game;
        switch (messageType){
            case "PlayerHasRegisteredMessage":
                return new PlayerHasRegisteredMessageHandler(gameClient);
            case "ResultOfLoginMessage":
                return new ResultOfLoginMessageHandler(gameClient);
            case "ResultOFRegistrationMessage":
                return new ResultOfRegistrationMessageHandler(gameClient);
            case "ResultOfRoundMessage":
                return new ResultOfRoundMessageHandler(gameClient);
            case "StartOfRoundMessage":
                return new StartOfRoundMessageHandler(gameClient);
                default:
                    return null;
        }
    }
}
