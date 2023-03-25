package app.Sale;

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
}
