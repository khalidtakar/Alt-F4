package app.Tests;

public class RunTests {
    public RunTests(){
        EmployeeTests employeeTests = new EmployeeTests();
        employeeTests.testEmployeeLogin();

        TicketTests ticketTests = new TicketTests();
        ticketTests.testGetAllTickets();
        ticketTests.testGetAdvisorsTickets(1);

        SystemTests systemTests = new SystemTests();
        systemTests.testLoad();
        systemTests.testSetCommissionRate(10);
        systemTests.testSetTaxRate(20);
        systemTests.testSetAutoBackupFreqDays(3);
        systemTests.testBackup();
        systemTests.testRestoreBackup();
    }

    public static void main(String[] args){
        new RunTests();
    }
}
