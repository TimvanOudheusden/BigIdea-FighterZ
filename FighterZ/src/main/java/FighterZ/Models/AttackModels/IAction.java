package FighterZ.Models.AttackModels;

public interface IAction {

    Attack opponentUsesLightAttack();

    Attack opponentUsesHeavyAttack();

    Attack opponentUsesGuardbreak();

    Attack opponentUsesBlock();

    Attack opponentUsesDodge();
}
