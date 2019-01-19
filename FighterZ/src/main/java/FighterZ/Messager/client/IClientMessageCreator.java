package FighterZ.Messager.client;

import FighterZ.Models.AttackModels.Attack;

import javax.websocket.EncodeException;

public interface IClientMessageCreator {

    void loginPlayer(String username, String password) throws EncodeException;

    void registerPlayer(String username, String password) throws EncodeException;

    void sendPlayerAttack(Attack attack) throws EncodeException;

}
