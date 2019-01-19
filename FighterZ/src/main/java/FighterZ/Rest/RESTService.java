package FighterZ.Rest;

import FighterZ.Data.Context.SQLContext;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/FighterZ")
public class RESTService {

    private final Gson gson = new Gson();
    private static final Logger log = Logger.getLogger(RESTService.class.getName());
    private final SQLContext sqlContext = new SQLContext();

    @POST
    @Path("/registerPlayer")
    @Consumes("application/json")
    @Produces("application/json")
    public Response registerPlayer(String received){
        RESTUser restUser = gson.fromJson(received, RESTUser.class);
        log.info("[Server registerPlayer]");
        if(restUser == null){
            return Response.status(400).entity(RESTResponseHelper.getErrorResponseString()).build();
        }
        log.info("valid user");
        String username = restUser.getUsername();
        String password = restUser.getPassword();

        boolean correctRegistered;
        if (username.isEmpty() || password.isEmpty()) {
            correctRegistered = false;
        }
        else {
            correctRegistered = sqlContext.registerPlayer(username, password);
        }
        return Response.status(200).entity(RESTResponseHelper.returnBoolean(correctRegistered)).build();
    }


    @POST
    @Path("/checkLogin")
    @Consumes("application/json")
    @Produces("application/json")
    public Response checkLogin(String received){
        RESTUser restModel = gson.fromJson(received, RESTUser.class);
        log.info("[Server checkLogin]");
        if(restModel == null){
            return Response.status(400).entity(RESTResponseHelper.getErrorResponseString()).build();
        }
        log.info("valid model");
        String username = restModel.getUsername();
        String password = restModel.getPassword();

        DBUserObject dbUserObject = sqlContext.checkLogin(username, password);

        if(dbUserObject.isSuccess()){
            return Response.status(200).entity(RESTResponseHelper.checkLogin(dbUserObject.getUsername())).build();
        }
        return Response.status(400).entity(RESTResponseHelper.getErrorResponseString()).build();
    }
}

