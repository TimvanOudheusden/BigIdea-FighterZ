package FighterZ.Messager.client;

import FighterZ.Models.AttackModels.Attack;

public interface IClientGUI {

    void processRegistrationResponse(boolean response);

    void processLoginResponse(boolean response);

    void processPlayerRegisteredResponse(String username);

    void processStartOfRound();

    void processResultOfRound(String damageReceiver, int damage);

    void processLethal();

}
