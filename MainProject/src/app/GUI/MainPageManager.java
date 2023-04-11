package app.GUI;

import app.Account.Employee;
import app.Account.EmployeeSQLHelper;
import app.Main;
import app.Sale.TicketController;
import app.System.System;
import app.System.SystemController;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
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
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private JTextField advisorTableSearch;
    private JPanel mainPageManagerPanel;
    private JComboBox reportType;
    private JButton changePasswordButton;

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
                //TODO generate a pdf report, self-explanatory
            }
        });
        changeCommissionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //change commission rate
                systemController.setCommissionRate(Double.parseDouble(commissionRate.getText()));
                commissionRateDisplay.setText("Commission rate: " + commissionRate + "%");
            }
        });
        changeTaxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //change tax rate to taxRate
                systemController.setTaxRate(Double.parseDouble(taxRate.getText()));
                taxRateDisplay.setText("Tax rate: " + taxRate + "%");
            }
        });
        changeDiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*TODO change discount rate to discountRate
                    change discountRateDisplay to display "Discount: " + discountRate + "%"
                    change flexible discount rate to flexDiscountRate at flexDiscountAmount USD
                    change flexDiscountRateDisplay to display "Flexible discount: " + discountRate "% at" + flexDiscountAmount + "USD"
                */
            }
        });
        ListSelectionModel selectionModel = advisorTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = advisorTable.getSelectedRow();
                    int advisorID = (int) advisorTable.getValueAt(selectedRow, 0);

                    //find the advisor instance associated with ID
                    Employee advisor = null;
                    for(Employee i : advisors){
                        if (i.getAdvisor().getAdvisorID() == advisorID) {
                            advisor = i;
                        }
                    }

                    //open pop up window for manager to assign tickets to an advisor
                    JDialog dialog = new ManagerTicketAssign(advisor);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setLocationRelativeTo(null);
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
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO change password
            }
        });
    }

    public void updateAdvisorsTable(){
         advisors = employeeSQLHelper.getAdvisors();

        //get the table object from GUI
        DefaultTableModel tableModel = (DefaultTableModel)advisorTable.getModel();

        //resets table if it is being redrawn
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);

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

        sorter = new TableRowSorter<>(tableModel);
        advisorTable.setRowSorter(sorter);
    }

    public JPanel getPanel(){
        return mainPageManagerPanel;
    }
}
