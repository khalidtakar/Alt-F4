package app.Sale;
import app.Account.Advisor;

import java.sql.Date;

/**
 * Stores ticket attributes
 */
public class Ticket {
    private int ticketType;
    private int ticketNumber;
    private Date dateReceived;
    private Date dateAssigned;

    //when initialised database key to be assigned for these values
    //if values need to be accessed, "if null" will be use and values to be loaded when needed
    private int saleID;
    private Sale sale;
    private int advisorID;
    private Advisor advisor;

    public Ticket(int ticketType, int ticketNumber, Date dateReceived, Date dateAssigned, int saleID, int advisorID) {
        this.ticketType = ticketType;
        this.ticketNumber = ticketNumber;
        this.dateReceived = dateReceived;
        this.dateAssigned = dateAssigned;
        this.saleID = saleID;
        this.advisorID = advisorID;
    }

    public Ticket(int ticketType, int ticketNumber, Date dateReceived){
        this.ticketType = ticketType;
        this.ticketNumber = ticketNumber;
        this.dateReceived = dateReceived;
    }

    public void setTicketType(int ticketType) {
        this.ticketType = ticketType;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public void setDateAssigned(Date dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public void setAdvisorID(int advisorID) {
        this.advisorID = advisorID;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public int getTicketType() {
        return ticketType;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public Date getDateAssigned() {
        return dateAssigned;
    }

    public int getSaleID() {
        return saleID;
    }

    public int getAdvisorID() {
        return advisorID;
    }

    public Sale getSale() {
        return sale;
    }

    public Advisor getAdvisor() {
        return advisor;
    }
}
