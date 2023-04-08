package app.GUI;

import app.Account.Advisor;
import app.Account.Employee;
import app.Main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalesAdvisor {
    private JButton logOutButton;
    private JTable latePaymentsTable;
    private JTable salesTable;
    private JButton returnButton;
    private JButton generateReportPDFButton;
    private JPanel salesPageAdvisorPanel;

    private Main main;
    private Employee employee;
    private Advisor advisor;

    public SalesAdvisor(Main main, Employee employee) {
        this.main = main;
        this.employee = employee;
        this.advisor = employee.getAdvisor();
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.goToMainPageAdvisor(employee);
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO log out
            }
        });
        generateReportPDFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO generate a pdf report of all sales made by this advisor
            }
        });

        ListSelectionModel selectionModel = latePaymentsTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = latePaymentsTable.getSelectedRow();
                    int saleID = (int) latePaymentsTable.getValueAt(selectedRow, 0);
                    // opens the late payment editor for selected sale
                    JDialog dialog = new AdvisorLatePayment(saleID);
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
                    int selectedRow = salesTable.getSelectedRow();
                    int saleID = (int) salesTable.getValueAt(selectedRow, 0);
                    // opens the sale viewer for selected sale
                    JDialog dialog = new AdvisorViewSale(saleID);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setLocationRelativeTo(null);
                    dialog.pack();
                    dialog.setVisible(true);
                }
            }
        });
    }

    public JPanel getPanel(){
        return salesPageAdvisorPanel;
    }
}
