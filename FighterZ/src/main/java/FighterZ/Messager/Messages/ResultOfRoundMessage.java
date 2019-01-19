package FighterZ.Messager.Messages;

import FighterZ.Models.Enums.MessageType;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultOfRoundMessage implements Serializable {

    @SerializedName("messageType")
    private MessageType messageType;
    @SerializedName("DamageReceiver")
    private String damageReceiver;
    @SerializedName("Damage")
    private int damage;

    public ResultOfRoundMessage(String damageReceiver, int damage){
        this.damageReceiver = damageReceiver;
        this.damage = damage;
        this.messageType = MessageType.ResultOfRoundMessage;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getDamageReceiver() {
        return damageReceiver;
    }

    public int getDamage() {
        return damage;
    }

}
