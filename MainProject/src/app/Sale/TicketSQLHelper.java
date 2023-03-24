package app.Sale;

import app.JDBC;

import java.util.ArrayList;
import java.sql.SQLException;

public class TicketSQLHelper extends JDBC {

    public TicketSQLHelper(){}

    /**
     * Fetches all tickets in the database
     * @return Arraylist of instances of Ticket
     */
    public ArrayList<Ticket> getAllTickets(){
        ArrayList<Ticket> tickets = new ArrayList<>();

        sql = "SELECT ticketType," +
                "ticketNumber," +
                "dateReceived," +
                "dateAssigned," +
                "advisorID," +
                "saleID " +
                "FROM Ticket";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            //for each row found, initialise a new Ticket and add to arraylist
            while (resultSet.next()) {
                tickets.add(new Ticket(resultSet.getInt("ticketType")
                        , resultSet.getInt("ticketNumber")
                        , resultSet.getDate("dateReceived")
                        , resultSet.getDate("dateAssigned")
                        , resultSet.getInt("advisorID")
                        , resultSet.getInt("saleID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return tickets;
    }

    public ArrayList<Ticket> getAdvisorsTickets(int advID){
        ArrayList<Ticket> tickets = new ArrayList<>();

        sql = "SELECT ticketType, " +
                "ticketNumber, " +
                "dateReceived, " +
                "dateAssigned, " +
                "advisorID, " +
                "saleID " +
                "FROM Ticket " +
                "WHERE advisorID = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, advID);
            resultSet = preparedStatement.executeQuery();

            //for each row found, initialise a new Ticket and add to arraylist
            while (resultSet.next()) {
                tickets.add(new Ticket(resultSet.getInt("ticketType")
                        , resultSet.getInt("ticketNumber")
                        , resultSet.getDate("dateReceived")
                        , resultSet.getDate("dateAssigned")
                        , resultSet.getInt("advisorID")
                        , resultSet.getInt("saleID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return tickets;
    }
}
