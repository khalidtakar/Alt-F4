package app.GUI;

import app.Main;
import app.Sale.Ticket;
import app.Sale.TicketController;
import app.System.System;
import app.System.SystemController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainPageAdmin{
    private JTextField blanksSearchField;
    private JComboBox blanksSort;
    private JTable blanksTable;
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
    private JLabel userInfo;
    private JLabel logo;
    private JPanel mainPageAdminPanel;

    private Main main;
    private System system;
    private SystemController systemController;
    private TicketController ticketController;

    private ArrayList<Ticket> tickets;

    public MainPageAdmin(Main main, System system, SystemController systemController, TicketController ticketController){
        this.main = main;
        this.system = system;
        this.systemController = systemController;
        this.ticketController = ticketController;

        backupFrequency.setText(String.valueOf(system.getAutoBackupFreqDays()));


        addBlanksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //create new blanks from textField1 to textField2
                tickets = ticketController.addTickets((Long.parseLong(addBlanksStartVal.getText()))
                        , Long.parseLong(addBlanksEndVal.getText()));
            }
        });
        deleteBlanksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO delete blanks from deleteBlanksStartVal to deleteBlanksEndVal
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
    }

    public JPanel getPanel(){
        return mainPageAdminPanel;
    }

    //updates blanks table with the latest state of ticket stocks
    public void updateTable(){
        tickets = ticketController.getAllTickets();

        //get the table object from GUI
        DefaultTableModel tableModel = (DefaultTableModel)blanksTable.getModel();

        //set columns
        tableModel.addColumn("Ticket type");
        tableModel.addColumn("Ticket no");
        tableModel.addColumn("Advisor ID");
        tableModel.addColumn("Sale");

        //insert rows
        for(Ticket i : tickets){
            tableModel.insertRow(0, new Object[]{
                    i.getTicketType(),
                    i.getTicketNumber(),
                    i.getAdvisorID(),
                    i.getSaleID()});
        }
    }
}
