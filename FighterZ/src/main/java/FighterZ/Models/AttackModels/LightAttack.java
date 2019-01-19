package FighterZ.Models.AttackModels;

import FighterZ.Models.Enums.AttackType;

public class LightAttack extends Attack implements IAction {

    private Attack lightAttack;

    public LightAttack(){
         lightAttack = new Attack();
         lightAttack.setAttackType(AttackType.LIGHT_ATTACK);
    }
    /* LightAttack base damage = 25  */

    @Override
    public Attack opponentUsesLightAttack(){
        this.lightAttack.setDamage(25);
        this.lightAttack.setDamageReceiver(2);
        return this.lightAttack;
    }

    @Override
    public Attack opponentUsesHeavyAttack(){
        this.lightAttack.setDamage(25);
        this.lightAttack.setDamageReceiver(2);
        return this.lightAttack;
    }

    @Override
    public Attack opponentUsesGuardbreak(){
        this.lightAttack.setDamage(25);
        this.lightAttack.setDamageReceiver(2);
        return this.lightAttack;
    }

    @Override
    public Attack opponentUsesBlock(){
        this.lightAttack.setDamage(25);
        this.lightAttack.setDamageReceiver(1);
        return this.lightAttack;
    }

    @Override
    public Attack opponentUsesDodge(){
        this.lightAttack.setDamage(25);
        this.lightAttack.setDamageReceiver(2);
        return this.lightAttack;
    }
}
