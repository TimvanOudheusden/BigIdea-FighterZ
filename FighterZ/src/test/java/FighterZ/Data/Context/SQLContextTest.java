package FighterZ.Data.Context;

import FighterZ.Rest.DBUserObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SQLContextTest {

    private SQLContext context;

    @Before
    public void prepareTest() {
        this.context = new SQLContext();
    }

    @Test
    public void registerPlayer() {
        Assert.assertEquals(context.registerPlayer("User456", "Password456"), true);
    }

    @Test
    public void checkLogin() {
        DBUserObject databaseObject = new DBUserObject();
        Assert.assertEquals(context.checkLogin("User456", "Password456"), databaseObject);
    }

}