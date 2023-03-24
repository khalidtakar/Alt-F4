package app.Tests;

import app.Sale.Ticket;
import app.Sale.TicketController;

import java.util.ArrayList;

public class TicketTests {
    public TicketTests(){}

    public void testGetAllTickets(){
        //DB connection and SELECT test
        System.out.println("Get all tickets:");

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

    public void testGetAdvisorsTickets(int advID){
        //DB connection and SELECT test
        System.out.println("Get advisor ID " + advID + " tickets:");

        TicketController ticketController = new TicketController();
        ArrayList<Ticket> tickets = ticketController.getAdvisorsTickets(1);
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
