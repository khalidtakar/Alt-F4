import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class JDBC{
    private static String USERNAME = "in2018g11_d";
    private static String PASSWORD = "qj3GNH0I";
    private static String URL = "jdbc:mysql://";
    private static String SERVERNAME = "smcse-stuproj00.city.ac.uk";
    private static int PORTNUMBER = 3306;
    private static String DBNAME = "";

    protected static Connection con;

    public JDBC(){}

    private void setupConnection() throws SQLException{

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Properties connectionProps = new Properties();
        con = DriverManager.getConnection(
                URL + SERVERNAME + ":" + PORTNUMBER + "/" + DBNAME,
                USERNAME,
                PASSWORD);
        con.setTransactionIsolation(3);
    }


}
