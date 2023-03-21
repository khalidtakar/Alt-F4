package app.Sale;

import app.JDBC;


import java.util.ArrayList;
import java.sql.SQLException;


public class TicketSQLHelper extends JDBC {

    public TicketSQLHelper(){}

    public ArrayList<Ticket> getAllTickets(){
        ArrayList<Ticket> tickets = new ArrayList<>();

        String sql = "SELECT ticketType, " +
                "ticketNumber " +
                "FROM Ticket";

        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            //convert sql return into Ticket classes
            while(resultSet.next()){
                tickets.add(new Ticket(resultSet.getInt("ticketType")
                        , resultSet.getInt("ticketNumber")));
        }

        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

        return tickets;
    }
}
