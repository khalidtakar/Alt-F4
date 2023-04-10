package app.Sale;

public class FlexibleDiscount {
    private int flexDiscID;
    private String email;
    private double discountRate;
    private double lowerBoundary;
    private double upperBoundary;

    private Customer customer;

    public FlexibleDiscount(int flexDiscID, String email, double discountRate, double lowerBoundary, double upperBoundary) {
        this.flexDiscID = flexDiscID;
        this.email = email;
        this.discountRate = discountRate;
        this.lowerBoundary = lowerBoundary;
        this.upperBoundary = upperBoundary;
    }

    public FlexibleDiscount() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
