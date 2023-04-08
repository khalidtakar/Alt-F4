package app.GUI;

import app.Main;
import app.Sale.TicketController;
import app.System.System;
import app.System.SystemController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPageAdvisor {
    private JButton logOutButton;
    private JTable customersTable;
    private JButton addNewCustomerButton;
    private JTable ticketsTable;
    private JTextField searchCustomersTextField;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton viewSalesButton;
    private JPanel mainPageAdvisorPanel;

    private Main main;
    private System system;
    private SystemController systemController;
    private TicketController ticketController;

    public MainPageAdvisor(Main main, System system, SystemController systemController, TicketController ticketController) {
        this.main = main;
        this.system = system;
        this.systemController = systemController;
        this.ticketController = ticketController;


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

    public JPanel getPanel(){
        return mainPageAdvisorPanel;
    }
}
