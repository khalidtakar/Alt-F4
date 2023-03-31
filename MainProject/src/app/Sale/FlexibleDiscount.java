package app.Sale;

public class FlexibleDiscount {
    private int flexDiscID;
    private double discountRate;
    private double lowerBoundary;
    private double upperBoundary;

    private Customer customer;

    public FlexibleDiscount(int flexDiscID, double discountRate, double lowerBoundary, double upperBoundary) {
        this.flexDiscID = flexDiscID;
        this.discountRate = discountRate;
        this.lowerBoundary = lowerBoundary;
        this.upperBoundary = upperBoundary;
    }

    public FlexibleDiscount() {
    }

    public int getFlexDiscID() {
        return flexDiscID;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public double getLowerBoundary() {
        return lowerBoundary;
    }

    public double getUpperBoundary() {
        return upperBoundary;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setFlexDiscID(int flexDiscID) {
        this.flexDiscID = flexDiscID;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public void setLowerBoundary(double lowerBoundary) {
        this.lowerBoundary = lowerBoundary;
    }

    public void setUpperBoundary(double upperBoundary) {
        this.upperBoundary = upperBoundary;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
