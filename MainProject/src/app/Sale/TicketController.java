package app.Sale;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class TicketController {
    protected ArrayList<Ticket> tickets;
    protected TicketSQLHelper ticketSQLHelper = new TicketSQLHelper();

    {
        //initialise ticket collection
        tickets = getAllTickets();
    }
    public TicketController(){}

    /**
     * Get an arraylist of all tickets in the DB
     * @return arraylist of tickets
     */
    public ArrayList<Ticket> getAllTickets(){
        tickets = ticketSQLHelper.getAllTickets();
        return tickets;
    }

    /**
     * Get an arraylist of tickets assigned to an advisor
     * @param advID advisor ID
     * @return arraylist of tickets
     */
    public ArrayList<Ticket> getAdvisorsTickets(int advID){
        tickets = ticketSQLHelper.getAdvisorsTickets(advID);
        return tickets;
    }

    public ArrayList<Ticket> addTickets(long startRange, long endRange){
        Ticket ticket;
        LocalDate now = LocalDate.now();
        Date sqlNow = java.sql.Date.valueOf(now);
        int startRangeType = Integer.parseInt(Long.toString(startRange).substring(0, 3));
        int startRangeNumber = Integer.parseInt(Long.toString(startRange).substring(3));

        for(int i = 0; i < (endRange - startRange); i++){
            ticket = new Ticket(startRangeType, startRangeNumber, sqlNow);
            ticketSQLHelper.addTicket(ticket);
            startRangeNumber++;
        }

        return getAllTickets();
    }
}
