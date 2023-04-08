package app.Sale;

import java.util.ArrayList;

public class CustomerController {
    CustomerSQLHelper customerSQLHelper = new CustomerSQLHelper();
    Customer customer;

    public CustomerController(){}

    public void addNewCustomer(Customer customer){
        customerSQLHelper.addNewCustomer(customer);
    }

    public ArrayList<Customer> getAllCustomers(){
        return customerSQLHelper.getAllCustomers();
    }
}
