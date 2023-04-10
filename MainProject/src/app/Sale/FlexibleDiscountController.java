package app.Sale;

import java.util.ArrayList;

public class FlexibleDiscountController {
    FlexibleDiscountSQLHelper flexibleDiscountSQLHelper = new FlexibleDiscountSQLHelper();

    public FlexibleDiscountController(){}

    public double calculateDiscount(Customer customer, Sale sale, ArrayList<FlexibleDiscount> discounts){
        double discount = 0;


        return discount;
    }

    public void addNewFlexibleDiscount(FlexibleDiscount flexibleDiscount){
        flexibleDiscountSQLHelper.addNewFlexibleDiscount(flexibleDiscount);
    }

    public ArrayList<FlexibleDiscount> getFlexibleDiscountsForCustomer(String customerEmail){
        return flexibleDiscountSQLHelper.getFlexibleDiscountsForCustomer(customerEmail);
    }

    public void removeFlexibleDiscount(int flexDiscID){
        flexibleDiscountSQLHelper.removeFlexibleDiscount(flexDiscID);
    }
}
