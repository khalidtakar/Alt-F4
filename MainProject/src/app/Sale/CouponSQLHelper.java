package app.Sale;

import app.JDBC;

import java.util.ArrayList;

public class CouponSQLHelper extends JDBC {

    public CouponSQLHelper() {}

    /**
     * Gets all coupons for a ticket ID
     * @param ticketType ticket type e.g. 444
     * @param ticketNo  ticket no, 6-8 digits
     * @return array list of Coupon instances
     */
    public ArrayList<Coupon> getCouponsForTicket(int ticketType, int ticketNo){
        ArrayList<Coupon> coupons = new ArrayList<>();

        return coupons;
    }

    /**
     * When an advisor prepares to sell a ticket, they have to make coupons for a journey
     * This operation will add an advisor generated coupon to the database
     * @param coupon advisor made coupon
     */
    public void addCoupon(Coupon coupon){

    }
}
