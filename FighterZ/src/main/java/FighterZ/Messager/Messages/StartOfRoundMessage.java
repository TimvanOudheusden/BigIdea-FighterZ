package FighterZ.Messager.Messages;

import FighterZ.Models.Enums.MessageType;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class StartOfRoundMessage implements Serializable {

    @SerializedName("messageType")
    private MessageType messageType;

    public StartOfRoundMessage(){
        this.messageType = MessageType.StartOfRoundMessage;
    }

    public MessageType getMessageType() {
        return messageType;
    }

}
