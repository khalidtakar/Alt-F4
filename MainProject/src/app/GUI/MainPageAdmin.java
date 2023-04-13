package app.GUI;

import app.Account.Employee;
import app.Account.EmployeeController;
import app.Main;
import app.Sale.Ticket;
import app.Sale.TicketController;
import app.System.System;
import app.System.SystemController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class MainPageAdmin{
    private JTable blanksTable;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private JTextField addBlanksStartVal;
    private JTextField addBlanksEndVal;
    private JButton addBlanksButton;
    private JTextField deleteBlanksStartVal;
    private JTextField deleteBlanksEndVal;
    private JButton deleteBlanksButton;

    private JTextField backupFrequency;
    private JButton changeFrequencyButton;
    private JButton createBackupNowButton;
    private JLabel timeSinceBackup;

    private JButton logOutButton;
    private JLabel welcomeText;
    private JLabel logo;
    private JPanel mainPageAdminPanel;
    private JLabel daysDisplay;
    private JButton restoreFromBackupButton;
    private JButton changePasswordButton;
    private JLabel adminName;
    private JLabel adminEmail;
    private JComboBox addBlanksType;
    private JComboBox deleteBlanksType;
    private JLabel frequencyDisplay;

    private Main main;
    private System system;
    private SystemController systemController;
    private TicketController ticketController;
    private Employee employee;

    private ArrayList<Ticket> tickets;

    public MainPageAdmin(Main main, System system, SystemController systemController, TicketController ticketController, Employee employee){
        this.main = main;
        this.system = system;
        this.systemController = systemController;
        this.ticketController = ticketController;
        this.employee = employee;

        adminName.setText(employee.getName());
        adminEmail.setText(employee.getEmail());

        backupFrequency.setText(String.valueOf(system.getAutoBackupFreqDays()));
        daysDisplay.setText(String.valueOf(systemController.checkLastBackup(system)) + " days since last backup");
        //frequencyDisplay.setText("Backup frequency: " + Integer.parseInt(backupFrequency.getText() + " days"));

        addBlanksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //create new blanks from textField1 to textField2
                tickets = ticketController.addTickets(Integer.parseInt(addBlanksType.getSelectedItem().toString()) ,(Integer.parseInt(addBlanksStartVal.getText()))
                        , Integer.parseInt(addBlanksEndVal.getText()));
                updateTable();
            }
        });
        deleteBlanksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tickets = ticketController.removeTickets(Integer.parseInt(deleteBlanksType.getSelectedItem().toString()) ,(Integer.parseInt(deleteBlanksStartVal.getText()))
                        , Integer.parseInt(deleteBlanksEndVal.getText()));
                update();

            }
        });
        changeFrequencyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //change the frequency of database updates
                systemController.setAutoBackupFreqDays(Integer.parseInt(backupFrequency.getText()));
            }
        });
        createBackupNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //create a backup immediately and download it to the user's pc
                systemController.backup();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.goToLoginPage();
            }
        });
        restoreFromBackupButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  JFileChooser fileChooser = new JFileChooser();
                  int result = fileChooser.showOpenDialog(null);
                  if (result == JFileChooser.APPROVE_OPTION) {
                      File selectedFile = fileChooser.getSelectedFile();
                      systemController.restore(selectedFile.getPath());
                  }
                  updateTable();
              }
        });
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                JLabel label = new JLabel("New password:");
                JPasswordField passwordField = new JPasswordField();
                panel.add(label, passwordField);
                JButton showPasswordButton = new JButton();
                showPasswordButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (passwordField.getEchoChar() == '•') {
                            passwordField.setEchoChar((char) 0);
                        } else {
                            passwordField.setEchoChar('•');
                        }
                    }
                });

                int option = JOptionPane.showConfirmDialog(null, passwordField, "Change password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String password = new String(passwordField.getPassword());
                    EmployeeController employeeController = new EmployeeController(employee);
                    employeeController.changePassword(password);
                }
            }
        });
    }

    public JPanel getPanel(){
        return mainPageAdminPanel;
    }

    public void update(){
        main.goToMainPageAdmin(employee);
    }

    //updates blanks table with the latest state of ticket stocks
    public void updateTable(){
        tickets = ticketController.getAllTickets();

        //get the table object from GUI
        tableModel = (DefaultTableModel)blanksTable.getModel();

        //resets table if it is being redrawn
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);

        //set columns
        tableModel.addColumn("Ticket type");
        tableModel.addColumn("Ticket no");
        tableModel.addColumn("Advisor ID");
        tableModel.addColumn("Sale");
        tableModel.addColumn("Received");
        tableModel.addColumn("Assigned");

        //insert rows
        for(Ticket i : tickets){
            tableModel.insertRow(0, new Object[]{
                    i.getTicketType(),
                    i.getTicketNumber(),
                    i.getAdvisorID(),
                    i.getSaleID(),
                    i.getDateReceived(),
                    i.getDateAssigned()});
        }

        sorter = new TableRowSorter<>(tableModel);
        blanksTable.setRowSorter(sorter);
    }
}
