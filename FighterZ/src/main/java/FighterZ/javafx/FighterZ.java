package FighterZ.javafx;

import Server.Websockets.ClientWebsocket;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.net.URI;

public class FighterZ extends Application {

    String dest = "ws://localhost:8080/FighterZ/";
    ClientWebsocket socket = new ClientWebsocket();
    WebSocketContainer container = ContainerProvider.getWebSocketContainer();

    public static void main(String[] args) {
        Application.launch(FighterZ.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FighterZ.fxml"));
        primaryStage.setTitle("FighterZ");
        primaryStage.setScene(new Scene(root, 1366, 768));
        primaryStage.show();
    }
}
