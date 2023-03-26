package app;

import java.io.*;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public abstract class JDBC{
    private static final String USERNAME = "in2018g11_d";
    private static final String PASSWORD = "qj3GNH0I";
    private static final String URL = "jdbc:mysql://";
    private static final String SERVERNAME = "smcse-stuproj00.city.ac.uk";
    private static final int PORTNUMBER = 3306;
    private static final String DBNAME = "in2018g11";

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
                    USERNAME,
                    PASSWORD);
            connection.setTransactionIsolation(2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
