package app.Sale;

import java.util.ArrayList;

public class FlexibleDiscountController {
    FlexibleDiscountSQLHelper flexibleDiscountSQLHelper = new FlexibleDiscountSQLHelper();

    public FlexibleDiscountController(){}

    /**
     * add new flexible discount for a customer
     * @param email customer email
     * @param discountRate discount rate
     * @param lowerBoundary low discount threshold
     * @param upperBoundary upper discount threshold
     */
    public void addNewFlexibleDiscount(String email, double discountRate, int lowerBoundary, int upperBoundary){
        flexibleDiscountSQLHelper.addNewFlexibleDiscount(new FlexibleDiscount(email, discountRate, lowerBoundary, upperBoundary));
    }

    /**
     * get all discounts associated with customer
     * @param customerEmail customer email
     * @return Arraylist of FlexibleDiscount instances for customer
     */
    public ArrayList<FlexibleDiscount> getFlexibleDiscountsForCustomer(String customerEmail){
        return flexibleDiscountSQLHelper.getFlexibleDiscountsForCustomer(customerEmail);
    }

    /**
     * remove flexible discount from database
     * @param flexDiscID flexible discount ID
     */
    public void removeFlexibleDiscount(int flexDiscID){
        flexibleDiscountSQLHelper.removeFlexibleDiscount(flexDiscID);
    }

}
