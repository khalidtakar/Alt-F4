package app.GUI;

import app.Account.Advisor;
import app.Account.Employee;
import app.Main;
import app.Sale.Customer;
import app.Sale.CustomerController;
import app.Sale.Ticket;
import app.Sale.TicketController;
import app.System.System;
import app.System.SystemController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainPageAdvisor {
    private JButton logOutButton;

    private JTable customersTable;
    private DefaultTableModel customersTableModel;
    private TableRowSorter<DefaultTableModel> customersSorter;

    private JButton addNewCustomerButton;

    private JTable ticketsTable;
    private DefaultTableModel ticketsTableModel;
    private TableRowSorter<DefaultTableModel> ticketsSorter;

    private JTextField searchCustomersTextField;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton viewSalesButton;
    private JPanel mainPageAdvisorPanel;

    private Main main;
    private System system;
    private Employee employee;
    private Advisor advisor;
    private SystemController systemController;
    private TicketController ticketController;
    private CustomerController customerController = new CustomerController();

    private ArrayList<Ticket> tickets;
    private ArrayList<Customer> customers;

    public MainPageAdvisor(Main main, System system, SystemController systemController, TicketController ticketController, Employee employee) {
        this.main = main;
        this.system = system;
        this.systemController = systemController;
        this.ticketController = ticketController;

        this.employee = employee;
        this.advisor = employee.getAdvisor();

        updateTicketsTable();
        updateCustomersTable();

        addNewCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // opens the customer creation window
                JDialog dialog = new AdvisorAddCustomer();
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setLocationRelativeTo(null);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        viewSalesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.goToSalesPageAdvisor();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.goToLoginPage();
            }
        });
        searchCustomersTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchCustomersTextField.getText();
                //TODO display all entries in customersTable with an instance of 'query'
            }
        });
        ListSelectionModel selectionModel = customersTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = customersTable.getSelectedRow();
                    String customerEmail = (String) customersTable.getValueAt(selectedRow, 0);
                    // opens the customer editing window for the selected customer
                    JDialog dialog = new AdvisorEditCustomer(customerEmail);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setLocationRelativeTo(null);
                    dialog.pack();
                    dialog.setVisible(true);
                }
            }
        });
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = ticketsTable.getSelectedRow();
                    int ticketNumber = (int) ticketsTable.getValueAt(selectedRow, 0);
                    // opens the ticket assignment window for the selected ticket
                    JDialog dialog = new AdvisorTicketAssign(ticketNumber);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setLocationRelativeTo(null);
                    dialog.pack();
                    dialog.setVisible(true);
                }
            }
        });
    }

    public void updateTicketsTable(){
        tickets = ticketController.getAdvisorsTickets(advisor.getAdvisorID());

        //get the table object from GUI
        ticketsTableModel = (DefaultTableModel) ticketsTable.getModel();

        //resets table if it is being redrawn
        ticketsTableModel.setColumnCount(0);
        ticketsTableModel.setRowCount(0);

        //set columns
        ticketsTableModel.addColumn("Ticket type");
        ticketsTableModel.addColumn("Ticket no");
        ticketsTableModel.addColumn("Advisor ID");
        ticketsTableModel.addColumn("Sale");
        ticketsTableModel.addColumn("Received");
        ticketsTableModel.addColumn("Assigned");

        //insert rows
        for (Ticket i : tickets) {
            ticketsTableModel.insertRow(0, new Object[]{
                    i.getTicketType(),
                    i.getTicketNumber(),
                    i.getAdvisorID(),
                    i.getSaleID(),
                    i.getDateReceived(),
                    i.getDateAssigned()});
        }

        ticketsSorter = new TableRowSorter<>(ticketsTableModel);
        ticketsTable.setRowSorter(ticketsSorter);
    }

    public void updateCustomersTable(){
        customers = customerController.getAllCustomers();

        //get the table object from GUI
        customersTableModel = (DefaultTableModel) customersTable.getModel();

        //resets table if it is being redrawn
        customersTableModel.setColumnCount(0);
        customersTableModel.setRowCount(0);

        //set columns
        customersTableModel.addColumn("Email");
        customersTableModel.addColumn("Name");
        customersTableModel.addColumn("Is Valued");
        customersTableModel.addColumn("Spent This Month");
        customersTableModel.addColumn("Amount To Return");
        customersTableModel.addColumn("Fixed Discount Rate");

        //insert rows
        for (Customer i : customers) {
            customersTableModel.insertRow(0, new Object[]{
                    i.getEmail(),
                    i.getName(),
                    i.isValued(),
                    i.getSpentThisMonth(),
                    i.getDiscountToRefundOrReturn(),
                    i.getFixedDiscountRate()});
        }

        ticketsSorter = new TableRowSorter<>(customersTableModel);
        customersTable.setRowSorter(customersSorter);
    }

    public JPanel getPanel(){
        return mainPageAdvisorPanel;
    }
}
