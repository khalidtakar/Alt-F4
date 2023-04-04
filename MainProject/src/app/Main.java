package app;

import app.Account.*;
import app.GUI.Login;
import app.GUI.MainPageAdmin;
import app.Sale.TicketController;
import app.System.System;
import app.System.SystemController;

import javax.swing.*;
import java.awt.*;

public class Main{
    private JPanel cardPane;
    public JFrame frame;
    public CardLayout cardLayout;

    private Login login;
    private MainPageAdmin mainPageAdmin;
    private JPanel loginPanel, mainPageAdminPanel;

    private Employee employee;
    private Manager manager;
    private Administrator administrator;
    private Advisor advisor;
    private System system;

    private EmployeeController employeeController;
    private SystemController systemController;
    private TicketController ticketController;

    /**
     * Main app entry point
     */
    public Main(){
        //Initialise controllers
        employee = new Employee();
        employeeController = new EmployeeController(this, employee);

        //main frame
        frame = new JFrame("ATS");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        cardLayout = new CardLayout();
        cardPane = new JPanel(cardLayout);


        //display card stack
        frame.setContentPane(cardPane);
        frame.setVisible(true);

        goToLoginPage();
    }

    public void goToLoginPage(){
        //create panels
        login = new Login(employeeController);
        loginPanel = login.getPanel();

        //remove previous cards
        cardPane.removeAll();

        //create a new login page card
        cardPane.add("login", loginPanel);
        cardLayout.show(cardPane, "login");

        frame.repaint();
        frame.setVisible(true);

    }

    public void goToMainPageAdmin(){
        //initialise controllers
        system = new System();
        systemController = new SystemController(system);
        ticketController = new TicketController();


        administrator = employee.getAdministrator();


        //create admin page panel
        mainPageAdmin = new MainPageAdmin(this, systemController, ticketController);
        mainPageAdmin.updateTable();
        mainPageAdminPanel = mainPageAdmin.getPanel();

        //create new admin page card
        cardPane.add("mainPageAdmin", mainPageAdminPanel);
        cardLayout.show(cardPane, "mainPageAdmin");
        frame.pack();
        frame.repaint();
    }

    public void goToMainPageManager(){

    }

    public void goToMainPageAdvisor(){

    }

    public static void main(String[] args){
        new Main();
    }
}
