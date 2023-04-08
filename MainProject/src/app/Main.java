package app;

import app.Account.*;
import app.GUI.*;
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
    private MainPageManager mainPageManager;
    private MainPageAdvisor mainPageAdvisor;
    private SalesAdvisor salesPageAdvisor;
    private JPanel loginPanel, mainPageAdminPanel, mainPageManagerPanel, mainPageAdvisorPanel, salesPageAdvisorPanel;

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

    public void goToMainPageAdmin(Employee employee){
        this.employee = employee;
        this.administrator = employee.getAdministrator();

        //initialise controllers
        systemController = new SystemController();
        system = systemController.getLoad();
        systemController = new SystemController(system);
        ticketController = new TicketController();


        administrator = employee.getAdministrator();


        //create admin page panel
        mainPageAdmin = new MainPageAdmin(this, system, systemController, ticketController);
        mainPageAdmin.updateTable();
        mainPageAdminPanel = mainPageAdmin.getPanel();

        //create new admin page card
        cardPane.add("mainPageAdmin", mainPageAdminPanel);
        cardLayout.show(cardPane, "mainPageAdmin");
        frame.pack();
        frame.repaint();
    }

    public void goToMainPageManager(Employee employee){
        this.employee = employee;
        this.manager = employee.getManager();

        //initialise controllers
        systemController = new SystemController();
        system = systemController.getLoad();
        systemController = new SystemController(system);
        ticketController = new TicketController();

        //create admin page panel
        mainPageManager = new MainPageManager(this, system, systemController, ticketController, new EmployeeSQLHelper());
        mainPageManager.updateAdvisorsTable();
        mainPageManagerPanel = mainPageManager.getPanel();

        //create new admin page card
        cardPane.add("mainPageManager", mainPageManagerPanel);
        cardLayout.show(cardPane, "mainPageManager");
        frame.pack();
        frame.repaint();
    }

    public void goToMainPageAdvisor(Employee employee){
        this.employee = employee;
        this.advisor = advisor;

        //initialise controllers
        systemController = new SystemController();
        system = systemController.getLoad();
        systemController = new SystemController(system);
        ticketController = new TicketController();

        //create admin page panel
        mainPageAdvisor = new MainPageAdvisor(this, system, systemController, ticketController);

        mainPageAdvisorPanel = mainPageAdvisor.getPanel();

        cardPane.removeAll();

        //create new admin page card
        cardPane.add("mainPageAdvisor", mainPageAdvisorPanel);
        cardLayout.show(cardPane, "mainPageAdvisor");
        frame.pack();
        frame.repaint();
    }

    public void goToSalesPageAdvisor(){
        salesPageAdvisor = new SalesAdvisor(this, employee);
        salesPageAdvisorPanel = salesPageAdvisor.getPanel();

        cardPane.add("salesPageAdvisor", salesPageAdvisorPanel);
        cardLayout.show(cardPane, "salesPageAdvisor");
        frame.pack();
        frame.repaint();
    }

    public static void main(String[] args){
        new Main();
    }
}
