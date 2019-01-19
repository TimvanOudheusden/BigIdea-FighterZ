package FighterZ.Rest;

public class DBUserObject {
    private String username = null;
    private boolean success = false;

    public DBUserObject(){
        //Empty constructor
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

}
