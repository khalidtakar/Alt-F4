package app.Sale;

import app.System.SystemSQLHelper;

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

    public TicketController() {
    }

    /**
     * Get an arraylist of all tickets in the DB
     *
     * @return arraylist of tickets
     */
    public ArrayList<Ticket> getAllTickets() {
        tickets = ticketSQLHelper.getAllTickets();
        return tickets;
    }

    /**
     * Get an arraylist of tickets assigned to an advisor
     *
     * @param advID advisor ID
     * @return arraylist of tickets
     */
    public ArrayList<Ticket> getAdvisorsTickets(int advID) {
        tickets = ticketSQLHelper.getAdvisorsTickets(advID);
        return tickets;
    }

    public ArrayList<Ticket> addTickets(long startRange, long endRange) {
        Ticket ticket;
        LocalDate now = LocalDate.now();
        Date sqlNow = java.sql.Date.valueOf(now);
        int startRangeType = Integer.parseInt(Long.toString(startRange).substring(0, 3));
        int startRangeNumber = Integer.parseInt(Long.toString(startRange).substring(3));

        for (int i = 0; i < (endRange - startRange); i++) {
            ticket = new Ticket(startRangeType, startRangeNumber, sqlNow);
            ticketSQLHelper.addTicket(ticket);
            startRangeNumber++;
        }

        return getAllTickets();
    }

    public ArrayList<Ticket> removeTickets(long startRange, long endRange) {
        int startRangeType = Integer.parseInt(Long.toString(startRange).substring(0, 3));
        int startRangeNumber = Integer.parseInt(Long.toString(startRange).substring(3));

        int endRangeNumber = Integer.parseInt(Long.toString(endRange).substring(3));

        while (startRangeNumber <= (endRangeNumber + 1)) {
            ticketSQLHelper.removeTicket(startRangeType, startRangeNumber);
            startRangeNumber++;

            java.lang.System.out.println(endRange - startRange);
            java.lang.System.out.println(startRangeType + " " + startRangeNumber);
        }

        return getAllTickets();
    }

    /**
     * Assigns a start and end range of tickets to an AdvisorID
     * @param startRange Ticket ID and number marking start of range
     * @param endRange Ticket ID and number marking end of range
     * @param advID Advisor ID
     * @return Ticket Arraylist with where advisor is now assigned
     */
    public ArrayList<Ticket> assignTickets(long startRange, long endRange, int advID) {
        ArrayList<Ticket> allTickets = ticketSQLHelper.getAllTickets();
        ArrayList<Ticket> ticketsToUpdate = new ArrayList<>();

        LocalDate now = LocalDate.now();
        Date sqlNow = java.sql.Date.valueOf(now);

        System.out.println(advID);

        int startRangeType = Integer.parseInt(Long.toString(startRange).substring(0, 3));
        int startRangeNumber = Integer.parseInt(Long.toString(startRange).substring(3));

        int endRangeNumber = Integer.parseInt(Long.toString(endRange).substring(3));

        System.out.println(startRangeType + " " + startRangeNumber + " " + endRangeNumber);
        //update ticket arraylist
        for(Ticket i : allTickets){
            if((i.getTicketType() == startRangeType)
                    && ((i.getTicketNumber() <= endRangeNumber) && (i.getTicketNumber() >= startRangeNumber))
                    && (i.getSaleID() == 0)){
                i.setAdvisorID(advID);
                i.setDateAssigned(sqlNow);
                ticketsToUpdate.add(i);
            }
        }

        //use ticket arraylist to update DB
        for(Ticket i : ticketsToUpdate){
            System.out.println(i.getTicketType() + " "
                    + i.getTicketNumber() + " "
                    + i.getDateReceived() + " "
                    + i.getDateAssigned() + " "
                    + i.getSaleID() + " "
                    + i.getAdvisorID());
            ticketSQLHelper.updateTicket(i);
        }

        //get new state of tickets in DB
        allTickets = ticketSQLHelper.getAllTickets();

        return allTickets;
    }
}
