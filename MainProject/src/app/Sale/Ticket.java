package app.Sale;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private int ticketType;
    private int ticketNumber;
    private DateTimeFormatter dateReceived;
    private DateTimeFormatter dateAssigned;

    //when initialised database key to be assigned for these values
    //if values need to be accessed, "if null" will be use and values to be loaded when needed
    private int saleID;
    //private Sale sale;
    private int advisorID;
    //private Advisor advisor;


    public Ticket(int ticketType, int ticketNumber, DateTimeFormatter dateReceived, DateTimeFormatter dateAssigned, int saleID, int advisorID) {
        this.ticketType = ticketType;
        this.ticketNumber = ticketNumber;
        this.dateReceived = dateReceived;
        this.dateAssigned = dateAssigned;
        this.saleID = saleID;
        this.advisorID = advisorID;
    }

    //remove this later
    public Ticket(int ticketType, int ticketNumber) {
        this.ticketType = ticketType;
        this.ticketNumber = ticketNumber;
    }

    public void printID(){
        System.out.print(Integer.toString(ticketType) + " " + Integer.toString(ticketNumber) + "\n");
    }
}
