package FighterZ.Models.AttackModels;

import FighterZ.Models.Enums.AttackType;

public class Guardbreak extends Attack implements IAction {

    private Attack guardbreak;

    public Guardbreak(){
        guardbreak = new Attack();
        guardbreak.setAttackType(AttackType.GUARD_BREAK);
    }

    /* Guardbreak base damage = 0  */

    @Override
    public Attack opponentUsesLightAttack(){
        this.guardbreak.setDamage(25);
        this.guardbreak.setDamageReceiver(1);
        return this.guardbreak;
    }

    @Override
    public Attack opponentUsesHeavyAttack(){
        this.guardbreak.setDamage(50);
        this.guardbreak.setDamageReceiver(1);
        return this.guardbreak;
    }

    @Override
    public Attack opponentUsesGuardbreak(){
        this.guardbreak.setDamage(0);
        this.guardbreak.setDamageReceiver(2);
        return this.guardbreak;
    }

    @Override
    public Attack opponentUsesBlock(){
        this.guardbreak.setDamage(50);
        this.guardbreak.setDamageReceiver(2);
        return this.guardbreak;
    }

    @Override
    public Attack opponentUsesDodge(){
        this.guardbreak.setDamage(0);
        this.guardbreak.setDamageReceiver(2);
        return this.guardbreak;
    }
}
