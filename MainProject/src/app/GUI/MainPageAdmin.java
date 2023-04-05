package app.GUI;

import app.Main;
import app.Sale.Ticket;
import app.Sale.TicketController;
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
    private SystemController systemController;
    private TicketController ticketController;

    private ArrayList<Ticket> tickets;

    public MainPageAdmin(Main main, SystemController systemController, TicketController ticketController){
        this.main = main;
        this.systemController = systemController;
        this.ticketController = ticketController;

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
                //delete blanks from deleteBlanksStartVal to deleteBlanksEndVal

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

    public void updateTable(){
        //this code does not work
        /// TODO: 04/04/2023 implement ticket table 
        tickets = ticketController.getAllTickets();

        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.addColumn("Ticket type");
        tableModel.addColumn("Ticket no");
        tableModel.addColumn("Assigned");
        tableModel.addColumn("Sale");

        for(Ticket i : tickets){
            tableModel.insertRow(0, new Object[]{
                    i.getTicketType(),
                    i.getTicketNumber(),
                    i.getAdvisorID(),
                    i.getSaleID()});
        }

        blanksTable = new JTable(tableModel);
        blanksTable.setSize(500,200);
        blanksTable.add(new JScrollPane());
    }
}
