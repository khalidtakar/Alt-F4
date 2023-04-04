package app.Sale;

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
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM customers WHERE email = ?";

        try {
            // establish connection to database
            connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g11"
                    ,"in2018g11_d","qj3GNH0I");

            // create SQL query to retrieve customer by email

            statement = connection.prepareStatement(sql);
            statement.setString(1, email);

            // execute query and retrieve results
            resultSet = statement.executeQuery();

            // check if customer was found
            if(resultSet.next()){
                // create new customer object and populate with data from result set
                customer = new Customer(resultSet.getString("email"),resultSet.getString("name"));


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
