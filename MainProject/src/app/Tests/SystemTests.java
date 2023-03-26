package app.Tests;

import app.System.System;
import app.System.SystemController;

public class SystemTests {
    System system = new System();
    SystemController systemController;

    {
        systemController = new SystemController();
    }

    public SystemTests() {}

    public void testLoad(){
        java.lang.System.out.println("Results for loading system settings: ");

        system = systemController.load();

        java.lang.System.out.println(system.getCommisionRate());
        java.lang.System.out.println(system.getTaxRate());
        java.lang.System.out.println(system.getAutoBackupFreqDays());
        java.lang.System.out.println(system.getLastBackup());
        java.lang.System.out.println();
    }

    public void testSetCommissionRate(double commissionRate){
        java.lang.System.out.println("Commission rate before: ");
        java.lang.System.out.println(system.getCommisionRate());

        system = systemController.setCommissionRate(commissionRate);

        java.lang.System.out.println("Commission rate after: ");
        java.lang.System.out.println(system.getCommisionRate() + "\n");
    }

    public void testSetTaxRate(double taxRate){
        java.lang.System.out.println("Tax rate before: ");
        java.lang.System.out.println(system.getTaxRate());

        system = systemController.setTaxRate(taxRate);

        java.lang.System.out.println("Tax rate after: ");
        java.lang.System.out.println(system.getTaxRate() + "\n");
    }

    public void testSetAutoBackupFreqDays(int autoBackupFreqDays){
        java.lang.System.out.println("AutoBackupFreqDays before: ");
        java.lang.System.out.println(system.getAutoBackupFreqDays());

        system = systemController.setAutoBackupFreqDays(autoBackupFreqDays);

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
