package app.Sale;

import app.Account.Manager;
import app.JDBC;

import java.sql.SQLException;

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

        sql = "SELECT * FROM customers WHERE email = ?";

        try {
            // create SQL query to retrieve customer by email

            statement = connection.prepareStatement(sql);
            statement.setString(1, email);

            // execute query and retrieve results
            resultSet = statement.executeQuery(sql);

            // check if customer was found
            if(resultSet.next()){
                // create new customer object and populate with data from result set
                customer = new Customer(resultSet.getString("email"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
