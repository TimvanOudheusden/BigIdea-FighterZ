package FighterZ.Rest;

public class FighterZServerResponse {
    private boolean success;
    private String username;

    public FighterZServerResponse(){
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
