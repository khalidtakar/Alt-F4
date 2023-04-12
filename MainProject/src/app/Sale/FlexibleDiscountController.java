package app.Sale;

import java.util.ArrayList;

public class FlexibleDiscountController {
    FlexibleDiscountSQLHelper flexibleDiscountSQLHelper = new FlexibleDiscountSQLHelper();

    public FlexibleDiscountController(){}

    public double calculateDiscount(Customer customer, Sale sale, ArrayList<FlexibleDiscount> discounts){
        double discount = 0;


        return discount;
    }

    public void addNewFlexibleDiscount(String email, double discountRate, int lowerBoundary, int upperBoundary){
        flexibleDiscountSQLHelper.addNewFlexibleDiscount(new FlexibleDiscount(email, discountRate, lowerBoundary, upperBoundary));
    }

    public ArrayList<FlexibleDiscount> getFlexibleDiscountsForCustomer(String customerEmail){
        return flexibleDiscountSQLHelper.getFlexibleDiscountsForCustomer(customerEmail);
    }

    public void removeFlexibleDiscount(int flexDiscID){
        flexibleDiscountSQLHelper.removeFlexibleDiscount(flexDiscID);
    }

}
