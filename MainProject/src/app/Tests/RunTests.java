package app.Tests;

public class RunTests {
    public RunTests(){
        EmployeeTests employeeTests = new EmployeeTests();
        employeeTests.testEmployeeLogin();

        TicketTests ticketTests = new TicketTests();
        ticketTests.testGetAllTickets();
        ticketTests.testGetAdvisorsTickets(1);

        SystemSettingsTests systemSettingsTests = new SystemSettingsTests();
        systemSettingsTests.testLoad();
    }

    public static void main(String[] args){
        new RunTests();
    }
}
