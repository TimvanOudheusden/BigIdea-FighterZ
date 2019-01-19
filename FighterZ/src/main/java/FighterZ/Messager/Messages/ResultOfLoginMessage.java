package FighterZ.Messager.Messages;

import FighterZ.Models.Enums.MessageType;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ResultOfLoginMessage implements Serializable {

    @SerializedName("messageType")
    private MessageType messageType;
    @SerializedName("Result")
    private boolean result;

    public ResultOfLoginMessage(boolean result) {
        this.result = result;
        this.messageType = MessageType.ResultOfLoginMessage;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public boolean isResult() {
        return result;
    }
}
