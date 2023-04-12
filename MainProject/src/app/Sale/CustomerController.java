package app.Sale;

import java.util.ArrayList;

public class CustomerController {
    CustomerSQLHelper customerSQLHelper = new CustomerSQLHelper();
    SaleSQLHelper saleSQLHelper = new SaleSQLHelper();
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

    public Customer getCustomerByEmail(String email) {
        return customerSQLHelper.getCustomerByEmail(email);
    }

    public void updateCustomerDetails(Customer customer, String email, String name, boolean isValued){
        customer.setName(name);
        customer.setValued(isValued);
        customerSQLHelper.updateCustomer(customer);

        if(customer.getEmail() != email){
            customerSQLHelper.updateCustomerEmail(customer.getEmail(), email);
        }
    }

    public void updateCustomer(Customer customer){
        customerSQLHelper.updateCustomer(customer);
    }
}
