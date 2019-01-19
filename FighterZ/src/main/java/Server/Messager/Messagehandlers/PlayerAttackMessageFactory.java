package Server.Messager.Messagehandlers;

import FighterZ.Models.AttackModels.*;

public class PlayerAttackMessageFactory {

    public Attack getTypeOfAttack(String attack){
        switch (attack){
            case "LIGHT_ATTACK":
                return new LightAttack();
            case "HEAVY_ATTACK":
                return new HeavyAttack();
            case "GUARDBREAK":
                return new Guardbreak();
            case "BLOCK":
                return new Block();
            case "DODGE":
                return new Dodge();
        }
        return null;
    }
}
