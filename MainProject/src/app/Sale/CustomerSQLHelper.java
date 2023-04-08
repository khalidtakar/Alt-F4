package app.Sale;

import app.Account.Manager;
import app.JDBC;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerSQLHelper extends JDBC {

    private String email;
    private String name;
    private boolean isValued;
    private double spentThisMonth;
    private double discountToRefundOrReturn;
    private double fixedDiscountRate;

    public CustomerSQLHelper() {}

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

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            // execute query and retrieve results
            resultSet = preparedStatement.executeQuery(sql);

            // check if customer was found
            if(resultSet.next()){
                // create new customer object and populate with data from result set
                customer = new Customer(resultSet.getString("email"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("isValued"),
                        resultSet.getDouble("spentThisMonth"),
                        resultSet.getDouble("discountToRefundOrReturn"),
                        resultSet.getDouble("fixedDiscountRate"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return customer;
    }

    public ArrayList<Customer> getAllCustomers(){
        ArrayList<Customer> customers = new ArrayList<>();

        sql = "SELECT email, " +
                "name, " +
                "isValued, " +
                "spentThisMonth, " +
                "discountOrRefundToReturn, " +
                "fixedDiscountRate " +
                "FROM RegisteredCustomer";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            //for each row found, initialise a new Ticket and add to arraylist
            while (resultSet.next()) {
                customers.add(new Customer(resultSet.getString("email")
                        , resultSet.getString("name")
                        , resultSet.getBoolean("isValued")
                        , resultSet.getDouble("spentThisMonth")
                        , resultSet.getDouble("discountOrRefundToReturn")
                        , resultSet.getDouble("fixedDiscountRate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return customers;
    }

    /** Add new customer row in DB using instance of customer provided
     *
     * @param customer new customer to be added to DB
     */
    public void addNewCustomer(Customer customer){


        String sql = "INSERT INTO customers (email, name, is_valued, spent_this_month, discount_to_refund_or_return, fixed_discount_rate) VALUES (?, ?, ?, ?, ?, ?)";

        try {

            // create SQL query to insert new customer
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getEmail());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setBoolean(3, customer.isValued());
            preparedStatement.setDouble(4, customer.getSpentThisMonth());
            preparedStatement.setDouble(5, customer.getDiscountToRefundOrReturn());
            preparedStatement.setDouble(6, customer.getFixedDiscountRate());

            // execute query and insert new customer
            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println(rowsInserted + " row(s) inserted.");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
            try {
                if(statement != null){
                    statement.close();
                }
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    /** Update all collumns of customer in DB to be the same as attributes
     * of provided customer instance
     * @param customer customer to be updated in DB
     */
    public void updateCustomer(Customer customer){


        String sql = "UPDATE customers SET name = ?, is_valued = ?, spent_this_month = ?, discount_to_refund_or_return = ?, fixed_discount_rate = ? WHERE email = ?";

        try {

            // create SQL query to update customer
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setBoolean(2, customer.isValued());
            preparedStatement.setDouble(3, customer.getSpentThisMonth());
            preparedStatement.setDouble(4, customer.getDiscountToRefundOrReturn());
            preparedStatement.setDouble(5, customer.getFixedDiscountRate());
            preparedStatement.setString(6, customer.getEmail());

            // execute query and update customer
            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println(rowsUpdated + " row(s) updated.");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // close database resources
            try {
                if(statement != null){
                    statement.close();
                }
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
