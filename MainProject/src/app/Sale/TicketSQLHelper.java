package app.Sale;

import app.JDBC;


import java.util.ArrayList;
import java.sql.SQLException;


public class TicketSQLHelper extends JDBC {

    public TicketSQLHelper(){}

    public ArrayList<Ticket> getAllTickets(){
        ArrayList<Ticket> tickets = new ArrayList<>();

        sql = "SELECT ticketType," +
                "ticketNumber," +
                "dateReceived," +
                "dateAssigned," +
                "advisorID," +
                "saleID " +
                "FROM Ticket";
        makeStatement();

        try {
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
