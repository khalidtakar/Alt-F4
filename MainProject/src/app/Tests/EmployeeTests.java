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

    public void makeHash(String salt, String password){
        System.out.println(salt + " : " + password);
        System.out.println(employeeController.doHashing(salt, password));
    }

    public void testEmployeeLogin(){
        System.out.println("login dennis:");

        employeeController.login("dennis@gmail.com", "Gnasher");

        System.out.println(employee.getEmail());
        System.out.println(employee.getName());
        System.out.println(employee.getAdvisor().getAdvisorID());
        System.out.println(employee.getTypeOfEmployee());

        System.out.println("\n");
    }

}
