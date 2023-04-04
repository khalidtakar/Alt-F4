package app.GUI;

import app.Main;
import app.Sale.Ticket;
import app.Sale.TicketController;
import app.Sale.TicketSQLHelper;
import app.System.SystemController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainPageAdmin{
    private JTextField blanksSearchField;
    private JComboBox blanksSort;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton addBlanksButton;
    private JButton deleteBlanksButton;
    private JTextField textField3;
    private JTextField textField4;
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
                tickets = ticketController.addTickets((Long.parseLong(textField1.getText()))
                        , Long.parseLong(textField1.getText()));
            }
        });
        deleteBlanksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //delete blanks from textField3 to textField4

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

        table1 = new JTable(tableModel);
        table1.setSize(500,200);
        table1.add(new JScrollPane());
    }
}
