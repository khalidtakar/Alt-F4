package app.Tests;

import app.Sale.Ticket;
import app.Sale.TicketController;

import java.util.ArrayList;

public class TicketTests {
    public TicketTests(){}

    public void testGetAllTickets(){
        //DB connection and SELECT test
        TicketController ticketController = new TicketController();
        ArrayList<Ticket> tickets = ticketController.getAllTickets();
        for(Ticket i: tickets){
            System.out.println(i.getTicketType() + " "
                    + i.getTicketNumber() + " "
                    + i.getDateReceived() + " "
                    + i.getDateAssigned() + " "
                    + i.getDateAssigned() + " "
                    + i.getSaleID() + " "
                    + i.getAdvisorID());
        }
        System.out.println("\n");
    }
}
