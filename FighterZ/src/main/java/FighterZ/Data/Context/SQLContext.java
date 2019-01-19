package FighterZ.Data.Context;

import FighterZ.Rest.DBUserObject;
import com.microsoft.sqlserver.jdbc.*;
import java.sql.*;

public class SQLContext implements ISQLContext {

    private Connection connection;

    public SQLContext(){

    }

    private void openConn(){

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String user = "dbi389694";
        String password = "Harrydoetdewas13";
        String dbURL = "jdbc:sqlserver://mssql.fhict.local;DatabaseName=dbi389694";
        try {
            connection = DriverManager.getConnection(dbURL, user, password);
        } catch (SQLException ex) {

            System.out.println(ex);
        }
        try
        {
            connection = DriverManager.getConnection(dbURL, user, password);
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
    }

    private void closeConn()
    {
        try
        {
            connection.close();
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
    }

    public boolean registerPlayer(String username, String password){
        System.out.println("Adding user to database");
        openConn();
        boolean success;
        String query = "INSERT INTO dbo.Player (username, password) VALUES (?,?)";
        try(PreparedStatement stat = connection.prepareStatement(query)) {
            stat.setString(1, username);
            stat.setString(2, password);
            stat.executeUpdate();
            success = true;
            closeConn();
        }
        catch (Exception ex){
            System.out.println(ex);
            success = false;
        }
        return success;
    }

    public DBUserObject checkLogin(String username, String password){
        openConn();
        String databasePassword = null;
        String databaseUsername = null;
        DBUserObject databaseObject = new DBUserObject();
        String query = "SELECT * FROM dbo.Player WHERE username = ?";
        try (PreparedStatement stat = connection.prepareStatement(query)) {
            stat.setString(1, username);
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                databasePassword = rs.getString("password");
                databaseUsername = rs.getString("username");
            }
            databaseObject.setSuccess(password.equals(databasePassword));
            databaseObject.setUsername(databaseUsername);
            rs.close();
            closeConn();
        } catch (Exception ex) {
            System.out.println(ex);
            databaseObject.setSuccess(false);
        }
        return databaseObject;
    }
}
