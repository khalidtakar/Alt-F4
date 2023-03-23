package app.Tests;

import app.Account.Employee;
import app.Account.EmployeeController;

public class EmployeeTests {

    public EmployeeTests(){}

    public void testEmployeeLogin(){
        EmployeeController employeeController = new EmployeeController();

        Employee employee = employeeController.login("bob@gmail.com", "123");
        System.out.println(employee.getEmail());
        System.out.println(employee.getName());

        System.out.println("\n");
    }

}
