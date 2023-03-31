package app.Sale;

import app.JDBC;

public class CustomerSQLHelper extends JDBC {

    public CustomerSQLHelper() {

    }

    /**
     * find customer in DB using email
     * @param email customer email
     * @return instance of Customer
     */
    public Customer getCustomerByEmail(String email){
        Customer customer = null;

        return customer;
    }

    /** Add new customer row in DB using instance of customer provided
     *
     * @param customer new customer to be added to DB
     */
    public void addNewCustomer(Customer customer){

    }

    /** Update all collumns of customer in DB to be the same as attributes
     * of provided customer instance
     * @param customer customer to be updated in DB
     */
    public void updateCustomer(Customer customer){

    }
}
