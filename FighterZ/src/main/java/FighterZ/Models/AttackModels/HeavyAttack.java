package FighterZ.Models.AttackModels;

import FighterZ.Models.Enums.AttackType;

public class HeavyAttack extends Attack implements IAction {

    private Attack heavyAttack;

    public HeavyAttack(){
        heavyAttack = new Attack();
        heavyAttack.setAttackType(AttackType.HEAVY_ATTACK);
    }

    /* HeavyAttack base damage = 50  */

    @Override
    public Attack opponentUsesLightAttack(){
        this.heavyAttack.setDamage(25);
        this.heavyAttack.setDamageReceiver(1);
        return this.heavyAttack;
    }

    @Override
    public Attack opponentUsesHeavyAttack(){
        this.heavyAttack.setDamage(50);
        this.heavyAttack.setDamageReceiver(2);
        return this.heavyAttack;
    }

    @Override
    public Attack opponentUsesGuardbreak(){
        this.heavyAttack.setDamage(50);
        this.heavyAttack.setDamageReceiver(2);
        return this.heavyAttack;
    }

    @Override
    public Attack opponentUsesBlock(){
        this.heavyAttack.setDamage(25);
        this.heavyAttack.setDamageReceiver(2);
        return this.heavyAttack;
    }

    @Override
    public Attack opponentUsesDodge(){
        this.heavyAttack.setDamage(25);
        this.heavyAttack.setDamageReceiver(1);
        return this.heavyAttack;
    }
}
