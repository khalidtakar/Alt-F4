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

    public ArrayList<Ticket> getAllTickets(){
        tickets = ticketSQLHelper.getAllTickets();
        return tickets;
    }

    public ArrayList<Ticket> getAdvisorsTickets(int advID){
        tickets = ticketSQLHelper.getAdvisorsTickets(advID);
        return tickets;
    }
}
