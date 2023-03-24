package app;

import app.Account.*;
import app.System.SystemSettings;
import app.System.SystemSettingsController;

public class Main {
    Employee employee;
    Manager manager;
    Administrator administrator;
    Advisor advisor;
    SystemSettings systemSettings;

    /**
     * Main app entry point
     */
    public Main(){
        goToLoginPage();
    }

    public void goToLoginPage(){

        //after login
        SystemSettingsController systemSettingsController = new SystemSettingsController();
        systemSettings = systemSettingsController.load();

        //elif statements to follow and decide next page based on employee type
    };

    public void goToAdvisorPage(){

    };

    public void goToAdministratorPage(){

    };

    public void goToManagerPage(){

    }

    public static void main(String[] args){
        new Main();
    }
}
