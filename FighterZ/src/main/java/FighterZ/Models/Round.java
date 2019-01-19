package FighterZ.Models;

import FighterZ.Models.AttackModels.*;
import FighterZ.Models.Enums.AttackType;
import java.util.HashMap;
import java.util.Observable;

public class Round extends Observable implements IRound {

    private HashMap<IPlayer, Attack> hmap = new HashMap<>();
    private IPlayer damageReceiver;
    private boolean roundHasEnded;

    public Round(){
        this.roundHasEnded = false;
    }

    public boolean RoundHasEnded() {
        return roundHasEnded;
    }

    public void setHasEnded(boolean value)
    {
        this.roundHasEnded = value;
        setChanged();
        notifyObservers();
    }


    public IPlayer getDamageReceiver() {
        return null;
    }

    public void processAttack(IPlayer p, Attack a)
    {
        if(this.roundHasEnded)
            return;

        if(!hmap.containsKey(p))
        {
            hmap.put(p, a);
            effectuateRound();
        }
    }


    public void effectuateRound(){
        if(hmap.size() == 2){
            Attack attack1 = (Attack)hmap.values().toArray()[0];
            Attack attack2 = (Attack)hmap.values().toArray()[1];

            switch (attack1.getAttackType()) {
                case LIGHT_ATTACK:
                    attack1 = new LightAttack();
                    break;
                case HEAVY_ATTACK:
                    attack1 = new HeavyAttack();
                    break;
                case GUARD_BREAK:
                    attack1 = new Guardbreak();
                    break;
                case BLOCK:
                    attack1 = new Block();
                    break;
                case DODGE:
                    attack1 = new Dodge();
                    break;
            }

            if(attack2.getAttackType() == AttackType.LIGHT_ATTACK){
                attack1.opponentUsesLightAttack();
            }
            else if(attack2.getAttackType() == AttackType.HEAVY_ATTACK){
                attack1.opponentUsesHeavyAttack();
            }
            else if(attack2.getAttackType() == AttackType.GUARD_BREAK){
                attack1.opponentUsesGuardbreak();
            }
            else if(attack2.getAttackType() == AttackType.BLOCK){
                attack1.opponentUsesBlock();
            }
            else if(attack2.getAttackType() == AttackType.DODGE){
                attack1.opponentUsesDodge();
            }


            if(attack1.getDamageReceiver() == 1){
                FirstPlayerReceivesDamage(attack1.getDamage());
            }
            if (attack1.getDamageReceiver() == 2){
                SecondPlayerReceivesDamage(attack1.getDamage());
            }
            setHasEnded(true);
        }
    }

    public boolean checkLethal(int hp, int dmg){
        boolean result = false;
        if(dmg >=  hp){
            result = true;
        }
        return result;
    }

    public void FirstPlayerReceivesDamage(int dmg) {
        damageReceiver = (Player)hmap.values().toArray()[0];
        ((Player) damageReceiver).setDamageReceived(dmg);

    }

    public void SecondPlayerReceivesDamage(int dmg){
        damageReceiver = (Player)hmap.values().toArray()[1];
        ((Player) damageReceiver).setDamageReceived(dmg);
    }
}
