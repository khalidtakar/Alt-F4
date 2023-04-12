package app.GUI;

import app.Account.Employee;
import app.Sale.Ticket;
import app.Sale.TicketController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.util.ArrayList;

public class ManagerAssignTickets extends JDialog{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable ticketTable;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private JTextField assignTicketsStartVal;
    private JTextField assignTicketsEndVal;

    private JLabel advisorInfo;
    private Employee advisor;

    private TicketController ticketController = new TicketController();

    private ArrayList<Ticket> tickets;

    public ManagerAssignTickets(Employee advisor) {
        this.advisor = advisor;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        updateTable();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tickets = ticketController.assignTickets((Long.parseLong(assignTicketsStartVal.getText()))
                        , Long.parseLong(assignTicketsEndVal.getText()), advisor.getAdvisor().getAdvisorID());
                updateTable();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        advisorInfo.setText(advisor.getAdvisor().getAdvisorID() + ": " + advisor.getName());
    }

    public void updateTable() {
        tickets = ticketController.getAllTickets();

        //get the table object from GUI
        tableModel = (DefaultTableModel) ticketTable.getModel();

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
        for (Ticket i : tickets) {
            tableModel.insertRow(0, new Object[]{
                    i.getTicketType(),
                    i.getTicketNumber(),
                    i.getAdvisorID(),
                    i.getSaleID(),
                    i.getDateReceived(),
                    i.getDateAssigned()});
        }

        sorter = new TableRowSorter<>(tableModel);
        ticketTable.setRowSorter(sorter);
    }

    private void onCancel() {
        dispose();
    }
}
