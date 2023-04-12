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

    private JButton viewSalesButton;
    private JPanel mainPageAdvisorPanel;
    private JButton changePasswordButton;

    private Main main;
    private System system;
    private Employee employee;
    private Advisor advisor;
    private SystemController systemController;
    private TicketController ticketController;
    private CustomerController customerController = new CustomerController();

    private ArrayList<Ticket> tickets;
    private ArrayList<Customer> customers;
    private MainPageAdvisor mainPageAdvisor;

    public MainPageAdvisor(Main main, System system, SystemController systemController, TicketController ticketController, Employee employee) {
        this.main = main;
        this.system = system;
        this.systemController = systemController;
        this.ticketController = ticketController;

        this.employee = employee;
        this.advisor = employee.getAdvisor();

        this.mainPageAdvisor = this;

        updateTicketsTable();
        updateCustomersTable();

        addNewCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // opens the customer creation window
                JDialog dialog = new AdvisorAddCustomer(mainPageAdvisor);
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
        ListSelectionModel selectionModelCustomers = customersTable.getSelectionModel();
        ListSelectionModel selectionModelTickets = ticketsTable.getSelectionModel();
        selectionModelCustomers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModelTickets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModelCustomers.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = customersTable.getSelectedRow();
                    String customerEmail = (String) customersTable.getValueAt(selectedRow, 0);

                    // opens the customer editing window for the selected customer
                    JDialog dialog = new AdvisorEditCustomer(customerController.getCustomerByEmail(customerEmail));
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setLocationRelativeTo(null);
                    dialog.pack();
                    dialog.setVisible(true);
                }
            }
        });

        selectionModelTickets.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = ticketsTable.getSelectedRow();
                    int ticketType = (int) ticketsTable.getValueAt(selectedRow, 0);
                    int ticketNumber = (int) ticketsTable.getValueAt(selectedRow, 1);

                    Ticket ticket = null;
                    for(Ticket i : tickets){
                        if ((i.getTicketType() == ticketType) && (i.getTicketNumber() == ticketNumber)) {
                            ticket = i;
                        }
                    }
                    // opens the ticket assignment window for the selected ticket
                    JDialog dialog = new AdvisorTicketAssign(ticket, mainPageAdvisor, advisor.getAdvisorID());
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setLocationRelativeTo(null);
                    dialog.pack();
                    dialog.setVisible(true);
                }
            }
        });
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                JLabel label = new JLabel("New password:");
                JPasswordField passwordField = new JPasswordField();
                panel.add(label, passwordField);

                int option = JOptionPane.showConfirmDialog(null, passwordField, "Change password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String password = new String(passwordField.getPassword());
                    //TODO set the new password
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
            if(i.getSaleID() == 0) {
                ticketsTableModel.insertRow(0, new Object[]{
                        i.getTicketType(),
                        i.getTicketNumber(),
                        i.getAdvisorID(),
                        i.getSaleID(),
                        i.getDateReceived(),
                        i.getDateAssigned()});
            }
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

        customersSorter = new TableRowSorter<>(customersTableModel);
        customersTable.setRowSorter(customersSorter);
    }

    public JPanel getPanel(){
        return mainPageAdvisorPanel;
    }
}
