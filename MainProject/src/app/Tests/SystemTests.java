package app.Tests;

import app.System.System;
import app.System.SystemController;

public class SystemTests {
    System system = new System();
    SystemController systemController;

    {
        systemController = new SystemController(system);
    }

    public SystemTests() {

    }

    /**
     * Prints all fields in system class after using load()
     */
    public void testLoad(){
        java.lang.System.out.println("Results for loading system settings: ");

        systemController.load();

        java.lang.System.out.println(system.getCommissionRate());
        java.lang.System.out.println(system.getTaxRate());
        java.lang.System.out.println(system.getAutoBackupFreqDays());
        java.lang.System.out.println(system.getLastBackup());
        java.lang.System.out.println();
    }

    /**
     * Takes new commission rate, updates db and returns old and updated state
     * @param commissionRate new commission rate stored as decimal(5,2) in db
     */
    public void testSetCommissionRate(double commissionRate){
        java.lang.System.out.println("Commission rate before: ");
        java.lang.System.out.println(system.getCommissionRate());

        systemController.setCommissionRate(commissionRate);

        java.lang.System.out.println("Commission rate after: ");
        java.lang.System.out.println(system.getCommissionRate() + "\n");
    }

    /**
     * Takes new tax rate, updates db and returns old and updated state
     * @param taxRate new tax rate stored as decimal(5,2) in db
     */
    public void testSetTaxRate(double taxRate){
        java.lang.System.out.println("Tax rate before: ");
        java.lang.System.out.println(system.getTaxRate());

        systemController.setTaxRate(taxRate);

        java.lang.System.out.println("Tax rate after: ");
        java.lang.System.out.println(system.getTaxRate() + "\n");
    }

    /**
     * Takes new auto backup frequency, updates db and returns old and updated state
     * @param autoBackupFreqDays new backup frequency in days
     */
    public void testSetAutoBackupFreqDays(int autoBackupFreqDays){
        java.lang.System.out.println("AutoBackupFreqDays before: ");
        java.lang.System.out.println(system.getAutoBackupFreqDays());

        systemController.setAutoBackupFreqDays(autoBackupFreqDays);

        java.lang.System.out.println("AutoBackupFreqDays after: ");
        java.lang.System.out.println(system.getAutoBackupFreqDays() + "\n");
    }

    public void testBackup(){
        systemController.backup();
    }

    public void testRestoreBackup(){
        systemController.restore("D:\\ComputerScience\\GitHub\\Alt-F4\\MainProject\\backup\\backup_26-03-2023.17.31.31.sql");
    }
}
