package FighterZ.Messager.Messages;

import FighterZ.Models.Enums.MessageType;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class PlayerHasRegisteredMessage implements Serializable {

    @SerializedName("messageType")
    private MessageType messageType;
    @SerializedName("Username")
    private String username;

    public PlayerHasRegisteredMessage(String cUsername) {
        this.username = cUsername;
        this.messageType = MessageType.PlayerHasRegisteredMessage;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getUsername() {
        return username;
    }
}
