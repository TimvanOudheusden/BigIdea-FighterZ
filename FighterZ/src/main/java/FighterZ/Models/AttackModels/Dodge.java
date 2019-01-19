package FighterZ.Models.AttackModels;

import FighterZ.Models.Enums.AttackType;

public class Dodge extends Attack implements IAction {

    private Attack dodge;

    public Dodge(){
        dodge = new Attack();
        dodge.setAttackType(AttackType.DODGE);
    }

    /* Block base damage = 0  */

    @Override
    public Attack opponentUsesLightAttack(){
        this.dodge.setDamage(25);
        this.dodge.setDamageReceiver(1);
        return this.dodge;
    }

    @Override
    public Attack opponentUsesHeavyAttack(){
        this.dodge.setDamage(25);
        this.dodge.setDamageReceiver(2);
        return this.dodge;
    }

    @Override
    public Attack opponentUsesGuardbreak(){
        this.dodge.setDamage(0);
        this.dodge.setDamageReceiver(2);
        return this.dodge;
    }

    @Override
    public Attack opponentUsesBlock(){
        this.dodge.setDamage(0);
        this.dodge.setDamageReceiver(2);
        return this.dodge;
    }

    @Override
    public Attack opponentUsesDodge(){
        this.dodge.setDamage(0);
        this.dodge.setDamageReceiver(2);
        return this.dodge;
    }
}
