package app.Sale;

import app.JDBC;

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

        return flexibleDiscounts;
    }

    /**
     * Create new row in DB for a flexible discount using instance provided
     * Note flexible discount ID in DB uses auto increment, so it does not need to be defined here
     * @param flexibleDiscount flexible discount
     */
    public void addNewFlexibleDiscount(FlexibleDiscount flexibleDiscount){

    }

    /**
     * Deletes a flexible discount from the DB using id provided
     * @param flexDiscID flexible discount ID
     */
    public void removeFlexibleDiscount(int flexDiscID){

    }
}
