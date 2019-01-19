package Server.Messager;

import Server.Messager.Messagehandlers.IMessageHandler;
import org.json.JSONObject;

public interface IMessageDecoderFactory {
    IMessageHandler getMessageHandler (JSONObject playerMessage, Object game);
}
