package FighterZ.Models;


public class Player implements IPlayer {

    private String playerName;

    private String playerPassword;

    private int playerHp;

    private int damageReceived;

    private String sessionId;

    public Player(String sessionId, String name)
    {
        this.playerName = name;
        this.sessionId = sessionId;
        this.playerHp = 200;
    }

    public Player(String sessionId, String name, String password)
    {
        this.playerName = name;
        this.playerPassword = password;
        this.sessionId = sessionId;
        this.playerHp = 200;
    }

    public void setPassword(String password){
        this.playerPassword = password;
    }

    public String getPassword(){
        return playerPassword;
    }

    public String getName() {
        return playerName;
    }

    public int getHp(){
        return playerHp;
    }

    public String getSessionId(){
        return sessionId;
    }

    public int getDamageReceived() {
        return damageReceived;
    }

    public void setDamageReceived(int dmg){
        this.damageReceived = dmg;
    }
}
