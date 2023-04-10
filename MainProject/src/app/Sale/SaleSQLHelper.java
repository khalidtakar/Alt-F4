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
        sql = "INSERT INTO Sale " +
                "(advisorID, " +
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
                "isRefunded)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {

            // create SQL query to insert new customer
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, sale.getAdvisorID());
            preparedStatement.setString(2,sale.getCustomerEmail());
            preparedStatement.setDate(3,sale.getDateSold());
            preparedStatement.setString(4, sale.getPaymentType());
            preparedStatement.setInt(5, sale.getCardNo());
            preparedStatement.setString(6, sale.getPaymentProvider());
            preparedStatement.setString(7, sale.getLocalCurrency());
            preparedStatement.setDouble(8,sale.getExchangeRate());
            preparedStatement.setDouble(9,sale.getPriceLocal());
            preparedStatement.setDouble(10,sale.getPriceUSD());
            preparedStatement.setDouble(11, sale.getSaleDiscountAmount());
            preparedStatement.setDouble(12,sale.getTaxAmount());
            preparedStatement.setDouble(13,sale.getSaleCommissionAmount());
            preparedStatement.setBoolean(14,sale.isDomestic());
            preparedStatement.setBoolean(15,sale.isPaid());
            preparedStatement.setBoolean(16,sale.isRefunded());
            preparedStatement.executeUpdate();

            // execute query and insert new customer
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * This will be used to update all columns for a Sale.
     * For example a refund is issued, the Sale class is update to include that
     * and now the Sale needs to be updated in the database
     * @param sale instance of sale to be updated in DB
     */
    public void updateSale(Sale sale){
        sql = "UPDATE Sale SET " +
                "advisorID = ?, " +
                "customerEmail = ?, " +
                "dateSold = ?, " +
                "paymentType = ?, " +
                "cardNo = ?, " +
                "paymentProvider = ?, " +
                "localCurrency = ?, " +
                "exchangeRate = ?, " +
                "priceLocal = ?, " +
                "priceUSD = ?, " +
                "saleDiscountAmount = ?, " +
                "taxAmount = ?, " +
                "saleCommissionAmount = ?, " +
                "isDomestic = ?, " +
                "isPaid = ?," +
                "isRefunded = ?" +
                "WHERE saleID = ?";

        try {

            // create SQL query to insert new customer
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, sale.getAdvisorID());
            preparedStatement.setString(2,sale.getCustomerEmail());
            preparedStatement.setDate(3,sale.getDateSold());
            preparedStatement.setString(4, sale.getPaymentType());
            preparedStatement.setInt(5, sale.getCardNo());
            preparedStatement.setString(6, sale.getPaymentProvider());
            preparedStatement.setString(7, sale.getLocalCurrency());
            preparedStatement.setDouble(8,sale.getExchangeRate());
            preparedStatement.setDouble(9,sale.getPriceLocal());
            preparedStatement.setDouble(10,sale.getPriceUSD());
            preparedStatement.setDouble(11, sale.getSaleDiscountAmount());
            preparedStatement.setDouble(12,sale.getTaxAmount());
            preparedStatement.setDouble(13,sale.getSaleCommissionAmount());
            preparedStatement.setBoolean(14,sale.isDomestic());
            preparedStatement.setBoolean(15,sale.isPaid());
            preparedStatement.setBoolean(16,sale.isRefunded());
            preparedStatement.setInt(17, sale.getSaleID());
            preparedStatement.executeUpdate();

            // execute query and insert new customer
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
