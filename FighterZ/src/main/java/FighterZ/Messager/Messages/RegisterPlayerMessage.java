package FighterZ.Messager.Messages;

import FighterZ.Models.Enums.MessageType;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class RegisterPlayerMessage implements Serializable {

    @SerializedName("messageType")
    private MessageType messageType;
    @SerializedName("Username")
    private String username;
    @SerializedName("Password")
    private String password;

    public RegisterPlayerMessage(String cName, String cPassword) {
        this.username = cName;
        this.password = cPassword;
        this.messageType = MessageType.RegisterPlayerMessage;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword() {
        return password;
    }
}
