package FighterZ.Models;

import FighterZ.Models.AttackModels.Attack;

public interface IRound {

    boolean RoundHasEnded();

    IPlayer getDamageReceiver();

    void processAttack(IPlayer p, Attack a);

    void effectuateRound();

    boolean checkLethal(int hp, int dmg);


}
