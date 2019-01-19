package FighterZ.Models.AttackModels;

import FighterZ.Models.Enums.AttackType;

public class Block extends Attack implements IAction {

    private Attack block;

    public Block(){
        block = new Attack();
        block.setAttackType(AttackType.BLOCK);
    }

    /* Block base damage = 0  */

    @Override
    public Attack opponentUsesLightAttack(){
        this.block.setDamage(25);
        this.block.setDamageReceiver(2);
        return this.block;
    }

    @Override
    public Attack opponentUsesHeavyAttack(){
        this.block.setDamage(25);
        this.block.setDamageReceiver(1);
        return this.block;
    }

    @Override
    public Attack opponentUsesGuardbreak(){
        this.block.setDamage(25);
        this.block.setDamageReceiver(1);
        return this.block;
    }

    @Override
    public Attack opponentUsesBlock(){
        this.block.setDamage(0);
        this.block.setDamageReceiver(2);
        return this.block;
    }

    @Override
    public Attack opponentUsesDodge(){
        this.block.setDamage(0);
        this.block.setDamageReceiver(2);
        return this.block;
    }
}
