package app.System;

import java.sql.Date;

public class SystemSettings {
    private String localCurrency;
    private double commisionRate;
    private double taxRate;
    private double exchangeRate;
    private Date lastBackup;

    public SystemSettings(String localCurrency, double commisionRate, double taxRate, double exchangeRate, Date lastBackup) {
        this.localCurrency = localCurrency;
        this.commisionRate = commisionRate;
        this.taxRate = taxRate;
        this.exchangeRate = exchangeRate;
        this.lastBackup = lastBackup;
    }

    public String getLocalCurrency() {
        return localCurrency;
    }

    public double getCommisionRate() {
        return commisionRate;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public Date getLastBackup() {
        return lastBackup;
    }

    public void setLocalCurrency(String localCurrency) {
        this.localCurrency = localCurrency;
    }

    public void setCommisionRate(double commisionRate) {
        this.commisionRate = commisionRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public void setLastBackup(Date lastBackup) {
        this.lastBackup = lastBackup;
    }
}
