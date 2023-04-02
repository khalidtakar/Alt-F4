package app.Sale;

import app.JDBC;

public class SaleSQLHelper extends JDBC {

    public SaleSQLHelper() {
    }

    /**
     * Gets sale by ticket ID
     * @param ticketType ticket type
     * @param ticketNo ticket no
     * @return instance of sale associated to ticket ID
     */
    public Sale getSaleByTicket(int ticketType, int ticketNo){
        Sale sale = null;

        return sale;
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
