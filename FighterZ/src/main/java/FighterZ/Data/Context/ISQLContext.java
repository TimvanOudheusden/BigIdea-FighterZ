package FighterZ.Data.Context;

import FighterZ.Rest.DBUserObject;

public interface ISQLContext {

    DBUserObject checkLogin(String username, String password);
    boolean registerPlayer(String name, String password);

}

