package app.Sale;

import app.JDBC;

import java.sql.SQLException;
import java.util.ArrayList;

public class FlexibleDiscountSQLHelper extends JDBC {

    public FlexibleDiscountSQLHelper() {
    }

    /**
     * Get and arraylist of flexible discount instances for a given customer
     * @param customerEmail customer email
     * @return ArrayList of flexibles discount instances
     */
    public ArrayList<FlexibleDiscount> getFlexibleDiscountsForCustomer(String customerEmail){
        ArrayList<FlexibleDiscount> flexibleDiscounts = new ArrayList<>();

        sql = "SELECT flexDiscID, " +
                "email, " +
                "discountRate, " +
                "lowerBoundary, " +
                "upperBoundary " +
                "FROM FlexibleDiscount" +
                "WHERE email = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerEmail);

            //for each row found, initialise a new Ticket and add to arraylist
            while (resultSet.next()) {
                flexibleDiscounts.add(new FlexibleDiscount(resultSet.getInt("flexDiscID"),
                        resultSet.getString("email"),
                        resultSet.getDouble("discountRate"),
                        resultSet.getDouble("lowerBoundary"),
                        resultSet.getDouble("uppedBoundary")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return flexibleDiscounts;
    }

    /**
     * Create new row in DB for a flexible discount using instance provided
     * Note flexible discount ID in DB uses auto increment, so it does not need to be defined here
     * @param flexibleDiscount flexible discount
     */
    public void addNewFlexibleDiscount(FlexibleDiscount flexibleDiscount){
        sql = "INSERT INTO FlexibleDiscount (email, " +
                "discountRate, " +
                "lowerBoundary " +
                "upperBoundary " +
                "VALUES (?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, flexibleDiscount.getEmail());
            preparedStatement.setDouble(2, flexibleDiscount.getDiscountRate());
            preparedStatement.setDouble(3, flexibleDiscount.getLowerBoundary());
            preparedStatement.setDouble(4, flexibleDiscount.getUpperBoundary());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes a flexible discount from the DB using id provided
     * @param flexDiscID flexible discount ID
     */
    public void removeFlexibleDiscount(int flexDiscID){
        sql = "DELETE FROM FlexibleDiscount " +
                "WHERE flexDiscID= ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, flexDiscID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
