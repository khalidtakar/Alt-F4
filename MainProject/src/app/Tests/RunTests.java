package app.Tests;

/**
 * Tests
 */
public class RunTests {
    public RunTests(){
        EmployeeTests employeeTests = new EmployeeTests();
        employeeTests.makeHash("dennis@gmail.com", "Gnasher");
        //employeeTests.testEmployeeLogin();

        TicketTests ticketTests = new TicketTests();
        //ticketTests.testGetAllTickets();
        //ticketTests.testGetAdvisorsTickets(1);
        //ticketTests.addTickets(44400000120L, 44400000150L);
        //ticketTests.removeTickets(44400000120L, 44400000130L);

        SaleTests saleTests = new SaleTests();
        //test exchange rate sparingly, API key has only 1000 uses per month
        //saleTests.testExchangeRate();

        SystemTests systemTests = new SystemTests();
        //systemTests.testLoad();
        //systemTests.testSetCommissionRate(12);
        //systemTests.testSetTaxRate(10);
        //systemTests.testSetAutoBackupFreqDays(4);
        //systemTests.testBackup();
        //systemTests.testRestoreBackup();
    }

    public static void main(String[] args){
        new RunTests();
    }
}
