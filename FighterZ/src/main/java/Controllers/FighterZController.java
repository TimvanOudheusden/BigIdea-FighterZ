package Controllers;

import FighterZ.Messager.client.*;
import FighterZ.Models.AttackModels.*;
import FighterZ.Models.Enums.AttackType;
import FighterZ.Rest.FighterZServerResponse;
import FighterZ.Rest.RESTUser;
import Server.Messager.MessageDecoderFactory;
import Server.Websockets.ClientWebsocket;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.websocket.ContainerProvider;
import javax.websocket.EncodeException;
import javax.websocket.WebSocketContainer;
import java.net.URI;

public class FighterZController implements IClientGUI {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label notificationTextField;

    @FXML
    private Button loginBtn;

    @FXML
    private Button registerBtn;

    @FXML
    private Label usernameDisplayField;

    @FXML
    private Label opponentsNameDisplayField;

    @FXML
    private Label playersHpField;

    @FXML
    private Label opponentsHpField;

    @FXML
    private Label opponentsAttack;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    //Voor Unit Test
    public boolean loginOrRegister;

    //Voor Unit Test
    public boolean roundInput;

    private GameClient gameClient;
    private ClientMessageCreator clientMessageCreator;
    private ClientWebsocket clientWebsocket;
    private ClientMessageProcessor processor;
    private ClientMessageDecoderFactory factory;

    private GameClient getGameClient() {
        return gameClient;
    }

    public FighterZController(){

        this.clientWebsocket = new ClientWebsocket();
        this.clientMessageCreator = new ClientMessageCreator(clientWebsocket);
        this.gameClient = new GameClient(clientMessageCreator);
        this.factory = new ClientMessageDecoderFactory();
        this.processor = new ClientMessageProcessor(factory);
        this.processor.registerGameClient(gameClient);
        this.clientWebsocket.setMessageProcessor(processor);
        gameClient.registerClientGUI(this);

        try {
            String dest = "ws://localhost:8090/FighterZ/";
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this.clientWebsocket, new URI(dest));
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void initialize(){
        setCanEnterRoundInput(false);
    }

    public void processRoundLightAttack() throws EncodeException {
        setCanEnterRoundInput(true);
        Attack lightAttack = new LightAttack();
        lightAttack.setAttackType(AttackType.LIGHT_ATTACK);
        getGameClient().sendPlayerAttack(lightAttack);
    }

    public void processRoundHeavyAttack() throws EncodeException {
        setCanEnterRoundInput(true);
        Attack heavyAttack = new HeavyAttack();
        heavyAttack.setAttackType(AttackType.HEAVY_ATTACK);
        getGameClient().sendPlayerAttack(heavyAttack);
    }

    public void processRoundGuardbreak() throws EncodeException {
        setCanEnterRoundInput(true);
        Attack guardbreak = new Guardbreak();
        guardbreak.setAttackType(AttackType.GUARD_BREAK);
        getGameClient().sendPlayerAttack(guardbreak);
    }

    public void processRoundBlock() throws EncodeException {
        setCanEnterRoundInput(true);
        Attack block = new Block();
        block.setAttackType(AttackType.BLOCK);
        getGameClient().sendPlayerAttack(block);
    }

    public void processRoundDodge() throws EncodeException {
        setCanEnterRoundInput(true);
        Attack dodge = new Dodge();
        dodge.setAttackType(AttackType.DODGE);
        getGameClient().sendPlayerAttack(dodge);
    }

    public void processRegistrationResponse(boolean response) {
        Platform.runLater(() -> {
            setCanLoginOrRegister(response);
        });
    }

    public void processLoginResponse(boolean response) {
        setCanLoginOrRegister(response);
    }

    public void processStartOfRound() {
        Platform.runLater(() -> {
            setCanEnterRoundInput(false);
        });
    }

    public void processPlayerRegistered() throws EncodeException {
        Platform.runLater(() -> {
            String username;
            String password;

            username = usernameField.getText();
            password = passwordField.getText();

            if(username.length() < 1 || password.length() < 1){
                notificationTextField.setText("No username or password entered");
                return;
            }

            try {
                getGameClient().registerPlayer(username, password);
            } catch (EncodeException e) {
                e.printStackTrace();
            }

            setCanLoginOrRegister(true);
        });
    }

    public void processPlayerLoggedIn() throws EncodeException{
        Platform.runLater(() -> {
            String username;
            String password;

            username = usernameField.getText();
            password = passwordField.getText();

            if(username.length() < 1 || password.length() < 1){
                notificationTextField.setText("No username or password entered");
                return;
            }

            try {
                getGameClient().loginPlayer(username, password);
            } catch (EncodeException e) {
                e.printStackTrace();
            }
            usernameDisplayField.setText(username);

            setCanLoginOrRegister(true);
        });
    }

    public void processPlayerRegisteredResponse(String username){

        Platform.runLater(() -> {
            if (!username.equals(usernameDisplayField.getText())) {
                opponentsNameDisplayField.setText(username);
            }
        });
    }

    public void processResultOfRound(String damageReceiver, int damage) {

        Platform.runLater(() -> {
            if (damageReceiver.equals(usernameDisplayField.getText())) {
                int hp = Integer.parseInt(playersHpField.getText());
                hp = hp - damage;
                playersHpField.setText(String.valueOf(hp));
            } else if (damageReceiver.equals(opponentsNameDisplayField.getText())) {
                int hp = Integer.parseInt(opponentsNameDisplayField.getText());
                hp = hp - damage;
                opponentsNameDisplayField.setText(String.valueOf(hp));
            }

            if (Integer.parseInt(playersHpField.getText()) <= 0 || Integer.parseInt(opponentsNameDisplayField.getText()) <= 0) {
                processLethal();
            }
        });
    }

    public void processLethal() {

        if(Integer.parseInt(playersHpField.getText()) <= 0){
            if(gameClient.processLethal()){
                setCanEnterRoundInput(true);
                setCanLoginOrRegister(true);
            }
        }
    }

    private void setCanEnterRoundInput(boolean state){
        roundInput = false;
        Platform.runLater(() -> {
            btn1.setDisable(state);
            btn2.setDisable(state);
            btn3.setDisable(state);
            btn4.setDisable(state);
            btn5.setDisable(state);
        });
    }

    private void setCanLoginOrRegister(boolean state){
        loginOrRegister = false;
        Platform.runLater(() -> {
            loginBtn.setDisable(state);
            registerBtn.setDisable(state);
            usernameField.setDisable(state);
            passwordField.setDisable(state);
        });
    }


}
