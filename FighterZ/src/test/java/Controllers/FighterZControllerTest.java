package Controllers;

import FighterZ.Messager.client.*;
import FighterZ.Models.AttackModels.LightAttack;
import FighterZ.Models.Game;
import FighterZ.Models.Player;
import Server.Websockets.ClientWebsocket;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import stubs.GameClientStub;
import stubs.ServerMessageCreatorStub;

import javax.websocket.ContainerProvider;
import javax.websocket.EncodeException;
import javax.websocket.WebSocketContainer;
import java.net.URI;

import static org.junit.Assert.*;

public class FighterZControllerTest {

    private FighterZController gui;


    @Before
    public void prepareTest() {
        this.gui = new FighterZController();
    }


    @Test
    public void processRoundLightAttack() throws EncodeException {
        gui.processRoundLightAttack();
        Assert.assertEquals(gui.roundInput, false);
    }

    @Test
    public void processPlayerRegistered() throws EncodeException {
        gui.processPlayerRegistered();
        Assert.assertEquals(gui.loginOrRegister, false);

    }

    @Test
    public void processPlayerLoggedIn() throws EncodeException {
        gui.processPlayerLoggedIn();
        Assert.assertEquals(gui.loginOrRegister, false);
    }
}