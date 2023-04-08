package app.Sale;

import java.util.ArrayList;

public class CustomerController {
    CustomerSQLHelper customerSQLHelper = new CustomerSQLHelper();
    Customer customer;

    public CustomerController(){}

    /**
     * Add a new customer to the database
     * @param customer customer instance to add to database
     */
    public void addNewCustomer(Customer customer){
        customerSQLHelper.addNewCustomer(customer);
    }

    /**
     * Gets all customers from the database
     * @return Arraylist of customer instances
     */
    public ArrayList<Customer> getAllCustomers(){
        return customerSQLHelper.getAllCustomers();
    }
}
