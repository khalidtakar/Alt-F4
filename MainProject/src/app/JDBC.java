package app;

import java.sql.DriverManager;
import java.util.Properties;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public abstract class JDBC{
    private static String USERNAME = "in2018g11_d";
    private static String PASSWORD = "qj3GNH0I";
    private static String URL = "jdbc:mysql://";
    private static String SERVERNAME = "smcse-stuproj00.city.ac.uk";
    private static int PORTNUMBER = 3306;
    private static String DBNAME = "in2018g11";

    //variables to be inherited by SQL helpers
    protected static Connection connection;
    protected static PreparedStatement preparedStatement;
    protected static Statement statement;
    protected static ResultSet resultSet;

    {
        if (connection == null){
            try {
                this.setupConnection();
            } catch (SQLException e) {
                System.out.println(e);
                throw new RuntimeException(e);

            }
        }
    }

    public JDBC(){}

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
