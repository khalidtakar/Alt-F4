package app.Sale;

public class Customer {
    private String email;
    private String name;
    private boolean isValued;
    private double spentThisMonth;
    private double discountToRefundOrReturn;
    private double fixedDiscountRate;

    public Customer(String email, String name, boolean isValued, double spentThisMonth, double discountToRefundOrReturn, double fixedDiscountRate) {
        this.email = email;
        this.name = name;
        this.isValued = isValued;
        this.spentThisMonth = spentThisMonth;
        this.discountToRefundOrReturn = discountToRefundOrReturn;
        this.fixedDiscountRate = fixedDiscountRate;
    }

    public Customer(String email, String name, boolean isValued) {
        this.email = email;
        this.name = name;
        this.isValued = isValued;
    }

    public Customer() {
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public boolean isValued() {
        return isValued;
    }

    public double getSpentThisMonth() {
        return spentThisMonth;
    }

    public double getDiscountToRefundOrReturn() {
        return discountToRefundOrReturn;
    }

    public double getFixedDiscountRate() {
        return fixedDiscountRate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValued(boolean valued) {
        isValued = valued;
    }

    public void setSpentThisMonth(double spentThisMonth) {
        this.spentThisMonth = spentThisMonth;
    }

    public void setDiscountToRefundOrReturn(double discountToRefundOrReturn) {
        this.discountToRefundOrReturn = discountToRefundOrReturn;
    }

    public void setFixedDiscountRate(double fixedDiscountRate) {
        this.fixedDiscountRate = fixedDiscountRate;
    }
}
