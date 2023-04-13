package app;

import app.Account.*;
import app.GUI.*;
import app.Sale.TicketController;
import app.System.System;
import app.System.SystemController;
import com.formdev.flatlaf.FlatLightLaf;

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
    private Setup setupPage;
    private JPanel loginPanel, mainPageAdminPanel, mainPageManagerPanel, mainPageAdvisorPanel, salesPageAdvisorPanel, setupPagePanel;
    private Dimension maxSize;

    private Employee employee = null;
    private System system;

    private EmployeeController employeeController = null;
    private SystemController systemController;
    private TicketController ticketController;

    /**
     * Main app entry point
     */
    public Main(){
        //Initialise controllers
        employee = new Employee();

        //main frame
        maxSize = new Dimension(1000, 600);

        frame = new JFrame("ATS");
        frame.setSize(maxSize);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(maxSize);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPane = new JPanel(cardLayout);
        cardPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 20, 20));

        //display card stack
        frame.setContentPane(cardPane);
        frame.setVisible(true);

        goToLoginPage();
    }

    public void goToLoginPage(){
        //create panels

        employee = new Employee();
        login = new Login(this, employee);
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


        //initialise controllers
        systemController = new SystemController();
        system = systemController.getLoad();
        systemController = new SystemController(system);
        ticketController = new TicketController();


        //create admin page panel
        mainPageAdmin = new MainPageAdmin(this, system, systemController, ticketController, employee);
        mainPageAdmin.updateTable();
        mainPageAdminPanel = mainPageAdmin.getPanel();

        //create new admin page card
        cardPane.add("mainPageAdmin", mainPageAdminPanel);
        cardLayout.show(cardPane, "mainPageAdmin");
        frame.pack();
        frame.setSize(maxSize);
        frame.repaint();
    }

    public void goToMainPageManager(Employee employee){
        this.employee = employee;

        //initialise controllers
        systemController = new SystemController();
        system = systemController.getLoad();
        systemController = new SystemController(system);
        ticketController = new TicketController();

        //create admin page panel
        mainPageManager = new MainPageManager(this,
                system,
                systemController,
                ticketController,
                new EmployeeSQLHelper(),
                employee);
        mainPageManager.updateAdvisorsTable();
        mainPageManagerPanel = mainPageManager.getPanel();

        //create new admin page card
        cardPane.add("mainPageManager", mainPageManagerPanel);
        cardLayout.show(cardPane, "mainPageManager");
        frame.pack();
        frame.setSize(maxSize);
        frame.repaint();
    }

    public void goToMainPageAdvisor(Employee employee){
        this.employee = employee;

        //initialise controllers
        systemController = new SystemController();
        system = systemController.getLoad();
        systemController = new SystemController(system);
        ticketController = new TicketController();

        //create admin page panel
        mainPageAdvisor = new MainPageAdvisor(this, system, systemController, ticketController, employee);

        mainPageAdvisorPanel = mainPageAdvisor.getPanel();

        cardPane.removeAll();

        //create new admin page card
        cardPane.add("mainPageAdvisor", mainPageAdvisorPanel);
        cardLayout.show(cardPane, "mainPageAdvisor");
        frame.pack();
        frame.setSize(maxSize);
        frame.repaint();
    }

    public void goToSalesPageAdvisor(){
        salesPageAdvisor = new SalesAdvisor(this, employee);
        salesPageAdvisorPanel = salesPageAdvisor.getPanel();

        cardPane.add("salesPageAdvisor", salesPageAdvisorPanel);
        cardLayout.show(cardPane, "salesPageAdvisor");
        frame.pack();
        frame.setSize(maxSize);
        frame.repaint();
    }

    public void goToSetupPage(){
        setupPage = new Setup(this);
        setupPagePanel = setupPage.getPanel();

        cardPane.add("setupPage", setupPagePanel);
        cardLayout.show(cardPane, "setupPage");
        frame.pack();
        frame.setSize(maxSize);
        frame.repaint();
    }

    public static void main(String[] args){
        FlatLightLaf.setup();

        new Main();
    }
}
