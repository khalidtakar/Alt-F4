package app.System;

public class SystemSettingsController {
    private SystemSettings systemSettings;
    private SystemSettingsSQLHelper systemSettingSQLHelper = new SystemSettingsSQLHelper();

    public SystemSettingsController() {
    }

    public SystemSettings load(){
        systemSettings = SystemSettingsSQLHelper.load();
        return systemSettings;
    }
}
