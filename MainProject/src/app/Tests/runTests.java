package app.Tests;

public class runTests {
    public static void main(String[] args){
        EmployeeTests employeeTests = new EmployeeTests();
        employeeTests.testEmployeeLogin();

        TicketTests ticketTests = new TicketTests();
        ticketTests.testGetAllTickets();
        ticketTests.testGetAdvisorsTickets(1);
    }
}
