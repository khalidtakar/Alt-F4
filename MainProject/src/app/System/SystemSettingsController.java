package app.System;

public class SystemSettingsController {
    private SystemSettings systemSettings;
    private SystemSettingsSQLHelper systemSettingsSQLHelper = new SystemSettingsSQLHelper();

    public SystemSettingsController() {
    }

    /**
     * Loads all system settings from the database
     * @return instance of SystemSettings
     */
    public SystemSettings load(){
        systemSettings = systemSettingsSQLHelper.load();
        return systemSettings;
    }
}
