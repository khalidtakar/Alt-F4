package app;

import app.Tests.EmployeeTests;
import app.Tests.TicketTests;

public class main {
    public static void main(String[] args){
        EmployeeTests employeeTests = new EmployeeTests();
        employeeTests.testEmployeeLogin();

        TicketTests ticketTests = new TicketTests();
        ticketTests.testGetAllTickets();
    }
}
