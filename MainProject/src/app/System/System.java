package app.System;

import java.sql.Date;

public class System {
    private double commisionRate;
    private double taxRate;
    private int autoBackupFreqDays;
    private Date lastBackup;

    public System(double commisionRate, double taxRate, int autoBackupFreqDays, Date lastBackup) {
        this.commisionRate = commisionRate;
        this.taxRate = taxRate;
        this.autoBackupFreqDays = autoBackupFreqDays;
        this.lastBackup = lastBackup;
    }

    public System(){}

    public void setCommissionRate(double commissionRate) {
        this.commisionRate = commissionRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public void setAutoBackupFreqDays(int autoBackupFreqDays) {
        this.autoBackupFreqDays = autoBackupFreqDays;
    }

    public void setLastBackup(Date lastBackup) {
        this.lastBackup = lastBackup;
    }

    public double getCommissionRate() {
        return commisionRate;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public Date getLastBackup() {
        return lastBackup;
    }

    public int getAutoBackupFreqDays() {
        return autoBackupFreqDays;
    }
}
