package app.GUI;

import app.Account.Advisor;
import app.Account.Employee;
import app.Main;
import app.Sale.Sale;
import app.Sale.SaleController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class SalesAdvisor {
    private JButton logOutButton;

    private JTable latePaymentsTable;
    private DefaultTableModel latePaymentsTableModel;
    private TableRowSorter<DefaultTableModel> latePaymentsSorter;

    private JTable salesTable;
    private DefaultTableModel salesTableModel;
    private TableRowSorter<DefaultTableModel> salesSorter;

    private JButton returnButton;
    private JButton generateReportPDFButton;
    private JPanel salesPageAdvisorPanel;

    private Main main;
    private Employee employee;
    private Advisor advisor;

    private ArrayList<Sale> completedSales;
    private ArrayList<Sale> lateSales;

    private SaleController saleController = new SaleController();

    public SalesAdvisor(Main main, Employee employee) {
        this.main = main;
        this.employee = employee;
        this.advisor = employee.getAdvisor();

        updateSalesTable();
        updateLatePaymentsTable();

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

        ListSelectionModel lateSelectionModel = latePaymentsTable.getSelectionModel();
        lateSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel completedSelectionModel = salesTable.getSelectionModel();
        completedSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        lateSelectionModel.addListSelectionListener(new ListSelectionListener() {
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
        completedSelectionModel.addListSelectionListener(new ListSelectionListener() {
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

    public void updateSalesTable(){
        completedSales = saleController.getAdvisorsCompletedSales(advisor.getAdvisorID());

        //get the table object from GUI
        salesTableModel = (DefaultTableModel) salesTable.getModel();

        //resets table if it is being redrawn
        salesTableModel.setColumnCount(0);
        salesTableModel.setRowCount(0);

        //set columns
        salesTableModel.addColumn("Sale ID");
        salesTableModel.addColumn("Customer Email");
        salesTableModel.addColumn("Price USD");
        salesTableModel.addColumn("Currency");
        salesTableModel.addColumn("Domestic");
        salesTableModel.addColumn("Refunded");

        //insert rows
        for (Sale i : completedSales) {
            salesTableModel.insertRow(0, new Object[]{
                    i.getSaleID(),
            i.getCustomerEmail(),
            i.getPriceUSD(),
            i.getLocalCurrency(),
            i.isDomestic(),
            i.isRefunded()});
        }

        salesSorter = new TableRowSorter<>(salesTableModel);
        salesTable.setRowSorter(salesSorter);
    }


    public void updateLatePaymentsTable(){
        lateSales = saleController.getAdvisorsLateSales(advisor.getAdvisorID());

        //get the table object from GUI
        latePaymentsTableModel = (DefaultTableModel) latePaymentsTable.getModel();

        //resets table if it is being redrawn
        latePaymentsTableModel.setColumnCount(0);
        latePaymentsTableModel.setRowCount(0);

        //set columns
        latePaymentsTableModel.addColumn("Sale ID");
        latePaymentsTableModel.addColumn("Days not paid");
        latePaymentsTableModel.addColumn("Customer Email");
        latePaymentsTableModel.addColumn("Price USD");
        latePaymentsTableModel.addColumn("Currency");
        latePaymentsTableModel.addColumn("Domestic");
        latePaymentsTableModel.addColumn("Refunded");

        //insert rows
        for (Sale i : lateSales) {
            LocalDate dateSold = i.getDateSold().toLocalDate();
            LocalDate today = LocalDate.now();
            long daysNotPaid = ChronoUnit.DAYS.between(dateSold, today);

            latePaymentsTableModel.insertRow(0, new Object[]{
                    i.getSaleID(),
                    daysNotPaid,
                    i.getCustomerEmail(),
                    i.getPriceUSD(),
                    i.getLocalCurrency(),
                    i.isDomestic(),
                    i.isRefunded()});
        }

        latePaymentsSorter = new TableRowSorter<>(latePaymentsTableModel);
        latePaymentsTable.setRowSorter(latePaymentsSorter);
    }

    public JPanel getPanel(){
        return salesPageAdvisorPanel;
    }
}
