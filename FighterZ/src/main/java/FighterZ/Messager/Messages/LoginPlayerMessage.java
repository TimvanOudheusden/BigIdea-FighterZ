package FighterZ.Messager.Messages;

import FighterZ.Models.AttackModels.Attack;
import FighterZ.Models.Enums.MessageType;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class LoginPlayerMessage implements Serializable {

    @SerializedName("messageType")
    private MessageType messageType;
    @SerializedName("Username")
    private String username;
    @SerializedName("Password")
    private String password;

    public LoginPlayerMessage(String aName, String aPassword){
        this.username = aName;
        this.password = aPassword;
        this.messageType = MessageType.LoginPlayerMessage;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
