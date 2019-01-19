package Server.Messager.Messagehandlers;

import org.json.JSONObject;

public interface IMessageHandler {

    void handleMessage(JSONObject playerMessage, String sessionId);
}
