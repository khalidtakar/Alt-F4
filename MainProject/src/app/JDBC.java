package app;

import app.GUI.Setup;
import app.System.SetupController;

import java.sql.DriverManager;
import java.util.Properties;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public abstract class JDBC{
    protected static String ADMINUSERNAME;
    protected static String ADMINPASSWORD;
    protected static String USERUSERNAME;
    protected static String USERPASSWORD;
    protected static String URL;
    protected static String SERVERNAME;
    protected static String PORTNUMBER;
    protected static String DBNAME;

    //variables to be inherited by SQL helpers
    protected static Connection connection;
    protected static PreparedStatement preparedStatement;
    protected static Statement statement;
    protected static ResultSet resultSet;
    protected static String sql;

    {
        //If this is the first time this class is initialised
        //Make a new connection
        if (connection == null){
            SetupController setup = new SetupController();

            USERUSERNAME = setup.getUSERUSERNAME();
            USERPASSWORD = setup.getUSERPASSWORD();
            URL = setup.getUrl();
            SERVERNAME = setup.getServerName();
            PORTNUMBER = setup.getPortNumber();
            DBNAME = setup.getDbName();


            try {
                this.setupConnection();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Inhereted by SQL helper classes to manipulate and fetch data from database
     */
    public JDBC(){}

    /**
     * Method to start connection with database
     * @throws SQLException
     */
    private void setupConnection() throws SQLException{

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Properties connectionProps = new Properties();
            connection = DriverManager.getConnection(
                    URL + SERVERNAME + ":" + PORTNUMBER + "/" + DBNAME,
                    USERUSERNAME,
                    USERPASSWORD);
            connection.setTransactionIsolation(2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
