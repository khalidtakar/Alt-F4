package app;

import app.Account.*;
import app.System.System;
import app.System.SystemController;

public class Main {
    Employee employee;
    Manager manager;
    Administrator administrator;
    Advisor advisor;
    System system;

    /**
     * Main app entry point
     */
    public Main(){
        goToLoginPage();
    }

    public void goToLoginPage(){

        //after login
        SystemController systemController = new SystemController();
        system = systemController.load();

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
