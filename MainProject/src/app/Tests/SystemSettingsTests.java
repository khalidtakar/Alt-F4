package app.Tests;

import app.System.SystemSettings;
import app.System.SystemSettingsController;

public class SystemSettingsTests {
    SystemSettingsController systemSettingsController = new SystemSettingsController();
    SystemSettings systemSettings;

    public SystemSettingsTests() {}

    public void testLoad(){
        System.out.println("Results for loading system settings: ");

        systemSettings = systemSettingsController.load();

        System.out.println(systemSettings.getLocalCurrency());
        System.out.println(systemSettings.getCommisionRate());
        System.out.println(systemSettings.getTaxRate());
        System.out.println(systemSettings.getAutoBackupFreqDays());
        System.out.println(systemSettings.getLastBackup());
        System.out.println(systemSettings.getExchangeRate());
    }
}
