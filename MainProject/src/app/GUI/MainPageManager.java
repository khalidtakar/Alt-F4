package app.GUI;

import app.Account.Employee;
import app.Account.EmployeeController;
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

    private JButton addNewAdvisorButton;
    private JTextField advisorName;
    private JTextField advisorEmail;

    private JTable advisorTable;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private JPanel mainPageManagerPanel;
    private JButton changePasswordButton;
    private JLabel manName;
    private JLabel manEmail;

    private ArrayList<Employee> advisors;

    private Main main;
    private System system;
    private SystemController systemController;
    private TicketController ticketController;
    private EmployeeSQLHelper employeeSQLHelper;
    private Employee employee;

    private MainPageManager mainPageManager;


    public MainPageManager(Main main,
                           System system,
                           SystemController systemController,
                           TicketController ticketController,
                           EmployeeSQLHelper employeeSQLHelper,
                           Employee employee) {
        this.main = main;
        this.system = system;
        this.systemController = systemController;
        this.ticketController = ticketController;
        this.employeeSQLHelper = employeeSQLHelper;
        this.employee = employee;

        this.mainPageManager = this;

        manName.setText(employee.getName());
        manEmail.setText(employee.getEmail());

        taxRate.setText(String.valueOf(system.getTaxRate()));
        commissionRate.setText(String.valueOf(system.getCommissionRate()));

        generateReportPDFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //opens report generation window
                JDialog dialog = new GenerateReport(employee);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setLocationRelativeTo(null);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        changeCommissionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //change commission rate
                systemController.setCommissionRate(Double.parseDouble(commissionRate.getText()));
            }
        });
        changeTaxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //change tax rate to taxRate
                systemController.setTaxRate(Double.parseDouble(taxRate.getText()));
            }
        });
        changeDiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO change global discount to discountRate
            }
        });
        addNewAdvisorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // opens the advisor creation window
                JDialog dialog = new ManagerAddAdvisor(mainPageManager, employee);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setLocationRelativeTo(null);
                dialog.pack();
                dialog.setVisible(true);
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
                    if (advisor != null) {
                        JDialog dialog = new ManagerAssignTickets(advisor);
                        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog.setLocationRelativeTo(null);
                        dialog.pack();
                        dialog.setVisible(true);
                    }
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
                JPanel panel = new JPanel();
                JLabel label = new JLabel("New password:");
                JPasswordField passwordField = new JPasswordField();
                panel.add(label, passwordField);

                int option = JOptionPane.showConfirmDialog(null, passwordField, "Change password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String password = new String(passwordField.getPassword());
                    EmployeeController employeeController = new EmployeeController(employee);
                    employeeController.changePassword(password);
                }
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

    public void update(){
        main.goToMainPageAdvisor(employee);
    }

    public JPanel getPanel(){
        return mainPageManagerPanel;
    }
}
