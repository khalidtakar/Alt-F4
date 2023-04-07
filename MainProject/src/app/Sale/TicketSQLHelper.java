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

    /**
     * Fetches all tickets with the advisor ID
     * @param advID advisor ID
     * @return Arraylist of instances of Ticket
     */
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

    /**
     * Update all columns for a ticket row with attributes from ticket instance
     * @param ticket ticket to be updated in DB
     */
    public void updateTicket(Ticket ticket){

        sql = "UPDATE Ticket SET " +
                "ticketType = ?, " +
                "dateReceived = ?, " +
                "dateAssigned = ?, " +
                "advisorID = ?, " +
                "saleID = ? " +
                "WHERE ticketNumber = ?";

        try {
            //create SQL query to update all columns for a ticket row
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ticket.getTicketType());
            preparedStatement.setDate(2, ticket.getDateReceived());
            preparedStatement.setDate(3, ticket.getDateAssigned());
            preparedStatement.setInt(4, ticket.getAdvisorID());
            preparedStatement.setInt(5, ticket.getSaleID());
            preparedStatement.setInt(6, ticket.getTicketNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds new row for Ticket in DB based on instance of ticket provided
     * * SaleID in DB uses auto increment, so you do not need to define it here
     */
    public void addTicket(Ticket ticket){

        sql = "INSERT INTO Ticket (ticketType, " +
                "ticketNumber, " +
                "dateReceived) " +
                "VALUES (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ticket.getTicketType());
            preparedStatement.setInt(2, ticket.getTicketNumber());
            preparedStatement.setDate(3, new java.sql.Date(ticket.getDateReceived().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * Removes ticket from the DB
     *
     * does not allow tickets to be removed where sale ID is not null
     * @param ticketType ticket type
     * @param ticketNo ticket number
     */
    public void removeTicket(int ticketType, int ticketNo){

        sql = "DELETE FROM Ticket WHERE ticketType=? AND ticketNumber=? AND saleID IS NULL";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ticketType);
            preparedStatement.setInt(2, ticketNo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}