package app.Sale;

import app.Account.Advisor;

import java.sql.Date;

public class Sale {
    private int saleID;
    private Date dateSold;
    private String paymentType;
    private int cardNo;
    private String paymentProvider;
    private String localCurrency;
    private double priceLocal;
    private double exchangeRate;
    private double priceUSD;
    private double saleDiscount;
    private double saleTax;
    private double saleCommissionAmount;
    private boolean isDomestic;
    private boolean isPaid;
    private boolean refundRequested;
    private boolean isRefunded;

    private String customerEmail;
    private int advisorID;

    private Customer customer;
    private Advisor advisor;

    public Sale(int saleID, Date dateSold, String paymentType, int cardNo, String paymentProvider, String localCurrency, double priceLocal, double exchangeRate, double priceUSD, double saleDiscount, double saleTax, double saleCommissionAmount, boolean isDomestic, boolean isPaid, boolean refundRequested, boolean isRefunded, String customerEmail, int advisorID) {
        this.saleID = saleID;
        this.dateSold = dateSold;
        this.paymentType = paymentType;
        this.cardNo = cardNo;
        this.paymentProvider = paymentProvider;
        this.localCurrency = localCurrency;
        this.priceLocal = priceLocal;
        this.exchangeRate = exchangeRate;
        this.priceUSD = priceUSD;
        this.saleDiscount = saleDiscount;
        this.saleTax = saleTax;
        this.saleCommissionAmount = saleCommissionAmount;
        this.isDomestic = isDomestic;
        this.isPaid = isPaid;
        this.refundRequested = refundRequested;
        this.isRefunded = isRefunded;
        this.customerEmail = customerEmail;
        this.advisorID = advisorID;
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

    public double getPriceUSD() {
        return priceUSD;
    }

    public double getSaleDiscount() {
        return saleDiscount;
    }

    public double getSaleTax() {
        return saleTax;
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

    public void setSaleDiscount(double saleDiscount) {
        this.saleDiscount = saleDiscount;
    }

    public void setSaleTax(double saleTax) {
        this.saleTax = saleTax;
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
