package app.System;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
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

    public double getCommisionRate() {
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
