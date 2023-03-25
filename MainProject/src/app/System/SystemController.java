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

    /**
     * Sets a new commission rate for the system in the DB
     * @param commissionRate double
     * @return updated system settings
     */
    public System setCommissionRate(double commissionRate){
        systemSQLHelper.setCommissionRate(commissionRate);
        return this.load();
    }

    /**
     * Sets a new tax rate for the system in the DB
     * @param taxRate double, in DB stored as decimal(5,2)
     * @return updated system settings
     */
    public System setTaxRate(double taxRate){
        systemSQLHelper.setTaxRate(taxRate);
        return this.load();
    }

    /**
     * Sets a new auto backup frequency for the system in the DB
     * @param autoBackupFreqDays int how often you want system to auto backup
     * @return updated system settings
     */
    public System setAutoBackupFreqDays(int autoBackupFreqDays){
        systemSQLHelper.setAutoBackupFreqDays(autoBackupFreqDays);
        return this.load();
    }


}
