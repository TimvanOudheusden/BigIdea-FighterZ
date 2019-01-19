package FighterZ.Models.AttackModels;

import FighterZ.Models.Enums.AttackType;

public class Attack {

    private int damage;
    private int damageReceiver;
    private AttackType attackType;


    public Attack opponentUsesLightAttack(){
        return null;
    }

    public Attack opponentUsesHeavyAttack(){
        return null;
    }

    public Attack opponentUsesGuardbreak(){
        return null;
    }

    public Attack opponentUsesBlock(){
        return null;
    }

    public Attack opponentUsesDodge(){
        return null;
    }



    public int getDamage() {
        return damage;
    }

    public int getDamageReceiver() {
        return damageReceiver;
    }

    public AttackType getAttackType(){
        return attackType;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDamageReceiver(int damageReceiver) {
        this.damageReceiver = damageReceiver;
    }

    public void setAttackType(AttackType attackType){
        this.attackType = attackType;
    }

}
