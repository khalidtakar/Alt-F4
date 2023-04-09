package app.Sale;

import app.JDBC;

import java.sql.SQLException;
import java.util.ArrayList;

public class SaleSQLHelper extends JDBC {

    public SaleSQLHelper() {
    }

    /**
     * Gets all sales
     * @return instance of sale associated to ticket ID
     */
    public ArrayList<Sale> getAllSales(){
        ArrayList<Sale> sales = new ArrayList<>();

        sql = "SELECT saleID, " +
                "advisorID, " +
                "customerEmail, " +
                "dateSold, " +
                "paymentType, " +
                "cardNo, " +
                "paymentProvider, " +
                "localCurrency, " +
                "exchangeRate, " +
                "priceLocal, " +
                "priceUSD, " +
                "saleDiscountAmount, " +
                "taxAmount, " +
                "saleCommissionAmount, " +
                "isDomestic, " +
                "isPaid," +
                "isRefunded " +
                "FROM Sale";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            //for each row found, initialise a new Ticket and add to arraylist
            while (resultSet.next()) {
                sales.add(new Sale(
                        resultSet.getInt("saleID"),
                        resultSet.getInt("advisorID"),
                        resultSet.getString("customerEmail"),
                        resultSet.getDate("dateSold"),
                        resultSet.getString("paymentType"),
                        resultSet.getInt("cardNo"),
                        resultSet.getString("paymentProvider"),
                        resultSet.getString("localCurrency"),
                        resultSet.getDouble("exchangeRate"),
                        resultSet.getDouble("priceLocal"),
                        resultSet.getDouble("priceUSD"),
                        resultSet.getDouble("saleDiscountAmount"),
                        resultSet.getDouble("taxAmount"),
                        resultSet.getDouble("saleCommissionAmount"),
                        resultSet.getBoolean("isDomestic"),
                        resultSet.getBoolean("isPaid"),
                        resultSet.getBoolean("isRefunded")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return sales;
    }

    /**
     * Once an advisor has created and clicked to confirm creating new sale,
     * this will upload a new row in the DB for that Sale
     * @param sale instance of sale to be added to DB
     */
    public void addNewSale(Sale sale){

    }


    /**
     * This will be used to update all columns for a Sale.
     * For example a refund is issued, the Sale class is update to include that
     * and now the Sale needs to be updated in the database
     * @param sale instance of sale to be updated in DB
     */
    public void updateSale(Sale sale){

    }
}
