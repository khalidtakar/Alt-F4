package app.Sale;

import app.Account.Advisor;

import java.sql.Date;

public class Sale {
    private int saleID;
    private int advisorID;
    private String customerEmail;
    private Date dateSold;
    private String paymentType;
    private int cardNo;
    private String paymentProvider;
    private String localCurrency;
    private double exchangeRate;
    private double priceLocal;
    private double priceUSD;
    private double saleDiscountAmount;
    private double taxAmount;
    private double saleCommissionAmount;
    private boolean isDomestic;
    private boolean isPaid;
    private Date datePaid;
    private boolean refundRequested;
    private boolean isRefunded;

    private Customer customer;
    private Advisor advisor;

    public Sale(int saleID, int advisorID, String customerEmail, Date dateSold, String paymentType, int cardNo, String paymentProvider, String localCurrency, double exchangeRate, double priceLocal, double priceUSD, double saleDiscountAmount, double taxAmount, double saleCommissionAmount, boolean isDomestic, boolean isPaid, Date datePaid, boolean isRefunded) {
        this.saleID = saleID;
        this.advisorID = advisorID;
        this.customerEmail = customerEmail;
        this.dateSold = dateSold;
        this.paymentType = paymentType;
        this.cardNo = cardNo;
        this.paymentProvider = paymentProvider;
        this.localCurrency = localCurrency;
        this.exchangeRate = exchangeRate;
        this.priceLocal = priceLocal;
        this.priceUSD = priceUSD;
        this.saleDiscountAmount = saleDiscountAmount;
        this.taxAmount = taxAmount;
        this.saleCommissionAmount = saleCommissionAmount;
        this.isDomestic = isDomestic;
        this.isPaid = isPaid;
        this.datePaid = datePaid;
        this.isRefunded = isRefunded;


        CustomerController customerController = new CustomerController();

        if(customerEmail != null){
            this.customer = customerController.getCustomerByEmail(customerEmail);
        }

    }

    public Sale() {
    }

    public int getSaleID() {
        return saleID;
    }

    public Date getDateSold() {
        return dateSold;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public int getCardNo() {
        return cardNo;
    }

    public String getPaymentProvider() {
        return paymentProvider;
    }

    public String getLocalCurrency() {
        return localCurrency;
    }

    public double getPriceLocal() {
        return priceLocal;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public double getPriceUSD() {
        return priceUSD;
    }

    public double getSaleDiscountAmount() {
        return saleDiscountAmount;
    }



    public double getTaxAmount() {
        return taxAmount;
    }

    public double getSaleCommissionAmount() {
        return saleCommissionAmount;
    }

    public boolean isDomestic() {
        return isDomestic;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public boolean isRefundRequested() {
        return refundRequested;
    }

    public boolean isRefunded() {
        return isRefunded;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public int getAdvisorID() {
        return advisorID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public void setDateSold(Date dateSold) {
        this.dateSold = dateSold;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public void setPaymentProvider(String paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    public void setLocalCurrency(String localCurrency) {
        this.localCurrency = localCurrency;
    }

    public void setPriceLocal(double priceLocal) {
        this.priceLocal = priceLocal;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public void setPriceUSD(double priceUSD) {
        this.priceUSD = priceUSD;
    }

    public void setSaleDiscountAmount(double saleDiscountAmount) {
        this.saleDiscountAmount = saleDiscountAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public void setSaleCommissionAmount(double saleCommissionAmount) {
        this.saleCommissionAmount = saleCommissionAmount;
    }

    public void setDomestic(boolean domestic) {
        isDomestic = domestic;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public void setRefundRequested(boolean refundRequested) {
        this.refundRequested = refundRequested;
    }

    public void setRefunded(boolean refunded) {
        isRefunded = refunded;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setAdvisorID(int advisorID) {
        this.advisorID = advisorID;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }
}
