package FighterZ.Rest;

import com.google.gson.Gson;
import java.util.logging.Logger;

public class RESTResponseHelper {
    private static final Gson gson = new Gson();

    private RESTResponseHelper(){
        //Empty constructor
    }

    public static String getErrorResponseString()
    {
        FighterZServerResponse response = new FighterZServerResponse();
        response.setSuccess(false);
        String output = gson.toJson(response);
        return output;
    }

    public static String returnBoolean(boolean correctRegistered){
        FighterZServerResponse response = new FighterZServerResponse();
        response.setSuccess(correctRegistered);
        String output = gson.toJson(response);
        return output;
    }

    public static String checkLogin(String username){
        FighterZServerResponse response = new FighterZServerResponse();
        response.setSuccess(true);
        response.setUsername(username);
        String output = gson.toJson(response);
        return output;
    }
}
