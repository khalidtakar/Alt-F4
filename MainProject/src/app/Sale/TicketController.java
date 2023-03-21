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
        return ticketSQLHelper.getAllTickets();
    }
}
