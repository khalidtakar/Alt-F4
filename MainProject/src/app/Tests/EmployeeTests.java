package app.Tests;

import app.Account.Employee;
import app.Account.EmployeeController;

public class EmployeeTests {
    Employee employee = new Employee();
    EmployeeController employeeController;

    {
        employeeController = new EmployeeController(employee);
    }
    public EmployeeTests(){}

    public void testEmployeeLogin(){
        System.out.println("login bob:");

        //employeeController.login("bob@gmail.com", "123");

        System.out.println(employeeController.doHashing("bob@gmail.com", "123"));

        System.out.println(employee.getEmail());
        System.out.println(employee.getName());
        System.out.println(employee.getAdvisor().getAdvisorID());
        System.out.println(employee.getTypeOfEmployee());

        System.out.println("\n");
    }

}
