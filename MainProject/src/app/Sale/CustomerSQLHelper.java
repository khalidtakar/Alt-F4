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

        sql = "SELECT email, " +
                "name, " +
                "isValued, " +
                "spentThisMonth, " +
                "discountOrRefundToReturn, " +
                "fixedDiscountRate " +
                "FROM RegisteredCustomer " +
                "WHERE email = ?";

        try {
            // create SQL query to retrieve customer by email

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            // execute query and retrieve results
            resultSet = preparedStatement.executeQuery();

            // check if customer was found
            if(resultSet.next()){
                // create new customer object and populate with data from result set
                customer = new Customer(resultSet.getString("email"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("isValued"),
                        resultSet.getDouble("spentThisMonth"),
                        resultSet.getDouble("discountOrRefundToReturn"),
                        resultSet.getDouble("fixedDiscountRate"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return customer;
    }

    /**
     * Fetches all customers from the database
     * @return Arraylist of customer instances
     */
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


        sql = "INSERT INTO RegisteredCustomer (email, name, isValued, spentThisMonth, discountOrRefundToReturn, fixedDiscountRate) VALUES (?, ?, ?, ?, ?, ?)";

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


        String sql = "UPDATE RegisteredCustomer SET name = ?, " +
                "isValued = ?, " +
                "spentThisMonth = ?, " +
                "discountOrRefundToReturn = ? " +
                "WHERE email = ?";

        try {

            // create SQL query to update customer
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setBoolean(2, customer.isValued());
            preparedStatement.setDouble(3, customer.getSpentThisMonth());
            preparedStatement.setDouble(4, customer.getDiscountToRefundOrReturn());
            preparedStatement.setString(5, customer.getEmail());

            // execute query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * update customer email across entire database
     * @param oldEmail old customer email
     * @param newEmail new customer email
     */
    public void updateCustomerEmail(String oldEmail, String newEmail) {
        String sql1 = "UPDATE RegisteredCustomer " +
                "SET email = ? " +
                "WHERE email = ?";

        String sql2 = "UPDATE FlexibleDiscount " +
                "SET email = NULL " +
                "WHERE email = ?";

        String sql3 = "UPDATE Sale " +
                "SET customerEmail = NULL " +
                "WHERE customerEmail = ?";

        String sql4 = "UPDATE FlexibleDiscount " +
                "SET email = ? " +
                "WHERE email IS NULL";

        String sql5 = "UPDATE Sale " +
                "SET customerEmail = ? " +
                "WHERE customerEmail IS NULL";

        try {
            // Start transaction
            connection.setAutoCommit(false);

            // Update FlexibleDiscount table to set email to NULL for records that reference old email
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1, oldEmail);
            preparedStatement.execute();

            // Update Sale table to set customerEmail to NULL for records that reference old email
            preparedStatement = connection.prepareStatement(sql3);
            preparedStatement.setString(1, oldEmail);
            preparedStatement.execute();

            // Update RegisteredCustomer table to set new email address
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setString(1, newEmail);
            preparedStatement.setString(2, oldEmail);
            preparedStatement.execute();

            // Update FlexibleDiscount table to set email to new email address for updated records
            preparedStatement = connection.prepareStatement(sql4);
            preparedStatement.setString(1, newEmail);
            preparedStatement.execute();

            // Update Sale table to set customerEmail to new email address for updated records
            preparedStatement = connection.prepareStatement(sql5);
            preparedStatement.setString(1, newEmail);
            preparedStatement.execute();

            // Commit transaction
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
