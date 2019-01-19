package FighterZ.Models;

import Controllers.RESTController;
import FighterZ.Models.AttackModels.Attack;
import FighterZ.Models.Enums.GameState;
import FighterZ.Rest.FighterZServerResponse;
import FighterZ.Rest.RESTUser;
import Server.Messager.Messagehandlers.IServerMessageCreator;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Game implements IGame, Observer {

    private ArrayList<IPlayer> players = new ArrayList<>();
    private final RESTController restController = new RESTController();
    private GameState gameState = GameState.WAITINGFORPLAYERS;
    private IRound currentRound = null;

    private IServerMessageCreator messageCreator;

    public Game(IServerMessageCreator messageCreator){
        this.messageCreator = messageCreator;
    }

    public IRound getCurrentRound() {
        return currentRound;
    }

    public void loginPlayer(String username, String password, String sessionId) {
        if(players.size() < 2)
        {
            if(checkPlayerNameAlreadyExists(username)){
                messageCreator.notifyResultOfRegistration(sessionId, false);
                return;
            }

            RESTUser restUser = new RESTUser(username, password);
            String queryPost = "/checkLogin";
            FighterZServerResponse response = restController.executeQueryPost(restUser, queryPost);

            if(response.isSuccess()){
                Player p = new Player(sessionId, username);
                players.add(p);
                messageCreator.notifyResultOfLogin(sessionId, true);
                messageCreator.notifyPlayerAdded(username);
                checkStartingCondition();
            }
        }
        else
        {
            messageCreator.notifyResultOfRegistration(sessionId, false);
        }
    }

    public void registerNewPlayer(String username, String password, String sessionId) {
        RESTUser restUser = new RESTUser(username, password);
        String queryPost = "/registerPlayer";
        FighterZServerResponse response = restController.executeQueryPost(restUser, queryPost);

        if(response.isSuccess()){
            System.out.println(response);
            loginPlayer(username, password, sessionId);
        }
        else
        {
            messageCreator.notifyResultOfRegistration(sessionId, false);
        }
    }

    public void processPlayerAttack(String sessionId, Attack attack) {

        if(this.gameState == GameState.ROUNDACTIVE){
            IPlayer player = getPlayerWithSession(sessionId);
            currentRound.processAttack(player, attack);
        }
    }

    private boolean checkPlayerNameAlreadyExists(String username)
    {
        for(IPlayer pl : players)
            if(pl.getName().equals(username))
            {
                return true;
            }
        return false;
    }

    public void processClientDisconnect(String sessionId) {
        for(IPlayer pl : players)
            if(pl.getSessionId().equals(sessionId)) {
                players.remove(pl);
                System.out.println("Player disconnected, current amount of players is: " + players.size());
            }

        if(gameState != GameState.WAITINGFORPLAYERS)
        {
            currentRound = null;
            gameState = GameState.WAITINGFORPLAYERS;
        }
    }

    public IPlayer getPlayerWithSession(String sessionId)
    {
        for(IPlayer p : players)
            if(p.getSessionId().equals(sessionId))
                return p;
        return null;
    }


    public int getNumberOfPlayers() {
        return players.size();
    }

    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void update(Observable o, Object arg) {

        o.deleteObserver(this);
        gameState = GameState.ROUNDRESULT;

        if(currentRound.RoundHasEnded()){
            messageCreator.notifyRoundEnded(currentRound.getDamageReceiver().getName(), currentRound.getDamageReceiver().getDamageReceived());
        }
        startNewRound();
    }

    private void checkStartingCondition()
    {
        if(players.size() == 2)
        {
            startNewRound();
        }
        else{
            gameState = GameState.WAITINGFORPLAYERS;
        }
    }

    private void startNewRound()
    {
        Round round = new Round();
        round.addObserver(this);

        currentRound = round;

        gameState = GameState.ROUNDACTIVE;
        messageCreator.notifyStartRound();
    }

}
