package app.System;

public class SystemController {
    private SystemSQLHelper systemSQLHelper = new SystemSQLHelper();

    public SystemController() {}

    /**
     * Loads all system settings from the database
     * @return instance of SystemSettings
     */
    public System load(){
        return systemSQLHelper.load();
    }

    public System setCommissionRate(double commissionRate){
        systemSQLHelper.setCommissionRate(commissionRate);
        return this.load();
    }

    public System setTaxRate(double taxRate){
        systemSQLHelper.setTaxRate(taxRate);
        return this.load();
    }

    public System setAutoBackupFreqDays(int autoBackupFreqDays){
        systemSQLHelper.setAutoBackupFreqDays(autoBackupFreqDays);
        return this.load();
    }

//    public System backup(){
//
//        return this.load();
//    }
}
