package FighterZ.Rest;

public class RESTUser {

    private final String username;
    private final String password;

    public RESTUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

}
