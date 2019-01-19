package Server.Jettyserver;

import FighterZ.Models.Game;
import FighterZ.Models.IGame;
import Server.Messager.MessageDecoderFactory;
import Server.Messager.Messagehandlers.IServerMessageCreator;
import Server.Messager.Messagehandlers.ServerMessageCreator;
import Server.Messager.ServerMessageProcessor;
import Server.Websockets.ServerWebsocket;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

public class Jettyserver {
    public static void main(String[] args) {

        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8090);
        server.addConnector(connector);

        MessageDecoderFactory factory = new MessageDecoderFactory();
        ServerMessageProcessor processor = new ServerMessageProcessor(factory);
        final ServerWebsocket socket = new ServerWebsocket();
        socket.setMessageProcessor(processor);

        IServerMessageCreator serverMessageCreator = new ServerMessageCreator(socket);

        IGame game = new Game(serverMessageCreator);
        processor.registerGame(game);

        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        try {
            // Initialize javax.websocket layer
            ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(context);

            // Add WebSocket endpoint to javax.websocket layer
            ServerEndpointConfig config = ServerEndpointConfig.Builder.create(socket.getClass(), socket.getClass().getAnnotation(ServerEndpoint.class).value())
                    .configurator(new ServerEndpointConfig.Configurator() {
                        @Override
                        public <T> T getEndpointInstance(Class<T> endpointClass) {
                            return (T) socket;
                        }
                    })
                    .build();

            wscontainer.addEndpoint(config);
            server.start();
            server.join();
        }
        catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }
}
