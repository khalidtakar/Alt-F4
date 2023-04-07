package app.GUI;

import app.Account.Advisor;
import app.Account.Employee;
import app.Account.EmployeeSQLHelper;
import app.Main;
import app.Sale.Ticket;
import app.Sale.TicketController;
import app.System.System;
import app.System.SystemController;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class MainPageManager {
    private JLabel logo;

    private JButton logOutButton;

    private JButton generateReportPDFButton;

    private JButton changeCommissionButton;
    private JTextField commissionRate;
    private JLabel commissionRateDisplay;

    private JButton changeTaxButton;
    private JTextField taxRate;
    private JLabel taxRateDisplay;

    private JButton changeDiscountButton;
    private JTextField discountRate;
    private JTextField flexDiscountRate;
    private JTextField flexDiscountAmount;
    private JLabel discountRateDisplay;
    private JLabel flexDiscountRateDisplay;

    private JTable advisorTable;
    private JTextField advisorTableSearch;
    private JComboBox advisorTableSort;
    private JPanel mainPageManagerPanel;

    private ArrayList<Employee> advisors;

    private Main main;
    private System system;
    private SystemController systemController;
    private TicketController ticketController;
    private EmployeeSQLHelper employeeSQLHelper;

    public MainPageManager(Main main,
                           System system,
                           SystemController systemController,
                           TicketController ticketController,
                           EmployeeSQLHelper employeeSQLHelper) {
        this.main = main;
        this.system = system;
        this.systemController = systemController;
        this.ticketController = ticketController;
        this.employeeSQLHelper = employeeSQLHelper;

        taxRate.setText(String.valueOf(system.getTaxRate()));
        commissionRate.setText(String.valueOf(system.getCommissionRate()));

        generateReportPDFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //generate a pdf report, self-explanatory
            }
        });
        changeCommissionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //change commission rate to commissionRate
                systemController.setCommissionRate(Double.parseDouble(commissionRate.getText()));

                //change commissionRateDisplay to display "Commission: " + commissionRate + "%"
            }
        });
        changeTaxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //change tax rate to taxRate
                systemController.setTaxRate(Double.parseDouble(taxRate.getText()));

                //change taxRateDisplay to display "Tax: " + taxRate + "%"
            }
        });
        changeDiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //change discount rate to discountRate
                //change discountRateDisplay to display "Discount: " + discountRate + "%"
                //change flexible discount rate to flexDiscountRate at flexDiscountAmount USD
                //change flexDiscountRateDisplay to display "Flexible discount: " + discountRate "% at" + flexDiscountAmount + "USD"
            }
        });
        ListSelectionModel selectionModel = advisorTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // allows only one row to be selected at a time

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = advisorTable.getSelectedRow();
                    int advisorID = (int) advisorTable.getValueAt(selectedRow, 0); //i am assuming advisorID is on column 0 here
                    // opens the ticket assignment window for the selected advisor
                    JDialog dialog = new ManagerTicketAssign(advisorID);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setLocationRelativeTo(null); //i hope this centers the dialog in the middle, idk didn't check
                    dialog.pack();
                    dialog.setVisible(true);
                }
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.goToLoginPage();
            }
        });
    }

    public void updateAdvisorsTable(){
         advisors = employeeSQLHelper.getAdvisors();

        //get the table object from GUI
        DefaultTableModel tableModel = (DefaultTableModel)advisorTable.getModel();

        //set columns
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Email");

        //insert rows
        for(Employee i : advisors){
            tableModel.insertRow(0, new Object[]{
                    i.getAdvisor().getAdvisorID(),
                    i.getName(),
                    i.getEmail()});
        }
    }

    public JPanel getPanel(){
        return mainPageManagerPanel;
    }
}
