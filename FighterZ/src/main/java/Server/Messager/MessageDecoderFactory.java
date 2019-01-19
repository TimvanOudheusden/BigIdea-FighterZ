package Server.Messager;

import FighterZ.Models.IGame;
import Server.Messager.Messagehandlers.*;
import org.json.*;

public class MessageDecoderFactory implements IMessageDecoderFactory {

    public IMessageHandler getMessageHandler (JSONObject playerMessage, Object game) {
        String messageType = playerMessage.getString("messageType");

        IGame igame = (IGame)game;
        switch (messageType){
            case "LoginPlayerMessage":
                return new LoginPlayerMessageHandler(igame);
            case "PlayerAttackMessage":
                return new PlayerAttackMessageHandler(igame);
            case "RegisterPlayerMessage":
                return new RegisterPlayerMessageHandler(igame);
                default:
                    return null;
        }
    }
}
