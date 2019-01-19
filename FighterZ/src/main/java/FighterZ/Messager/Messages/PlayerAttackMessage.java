package FighterZ.Messager.Messages;

import FighterZ.Models.AttackModels.Attack;
import FighterZ.Models.Enums.AttackType;
import FighterZ.Models.Enums.MessageType;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class PlayerAttackMessage implements Serializable {

    @SerializedName("messageType")
    private MessageType messageType;
    @SerializedName("attackType")
    private AttackType attackType;
    @SerializedName("damage")
    private int damage;
    @SerializedName("damagereciever")
    private int damageReceiver;

    public PlayerAttackMessage(Attack attack){
        this.attackType = attack.getAttackType();
        this.damage = attack.getDamage();
        this.damageReceiver = attack.getDamageReceiver();
        this.messageType = MessageType.PlayerAttackMessage;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public int getDamage() {
        return damage;
    }

    public int getDamageReceiver() {
        return damageReceiver;
    }

}
