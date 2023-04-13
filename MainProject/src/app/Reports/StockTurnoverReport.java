package app.Reports;

import app.Sale.TicketController;
import app.Sale.Ticket;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class StockTurnoverReport {
    private JTable receivedStock;
    private JTable oldAssignedUnusedStock;
    private JTable usedStock;
    private JTable unusedAgencyStock;
    private JTable receivedAndAssignedStockByAdv;
    private JTable unusedStockPerAdv;
    private JLabel totalUnusedStockPerAdv;
    private JLabel totalUnusedAgencyStock;
    private JLabel totalUsedStock;
    private JLabel totalOldAssignedUnusedStock;
    private JLabel totalReceivedAndAssignedStockByAdv;
    private JLabel totalReceivedStock;
    private JLabel dateFrom;
    private JLabel dateTo;
    private JPanel ticketStockTurnoverReport;

    private Date dateStart;
    private Date dateEnd;

    public StockTurnoverReport(Date dateFrom, Date dateTo){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        this.dateStart = dateFrom;
        this.dateEnd = dateTo;

        this.dateFrom.setText("Period from: " + dateFormat.format(dateFrom));
        this.dateTo.setText("to: " + dateFormat.format(dateTo));

        updateTables();
    }

    public void updateTables(){

        //TABLE 1 : Received Stock
        TicketController ticketController = new TicketController();
        ArrayList<Ticket> tickets = ticketController.getAllTickets();

        DefaultTableModel receivedStockTableModel = (DefaultTableModel) receivedStock.getModel();
        receivedStockTableModel.setColumnCount(0);
        receivedStockTableModel.setRowCount(0);

        receivedStockTableModel.addColumn("Ticket Type");
        receivedStockTableModel.addColumn("Ticket No");

        int counter = 0;
        for (Ticket i :tickets){
            if(i.getDateReceived().before(dateEnd) && i.getDateReceived().after(dateStart)){
                receivedStockTableModel.insertRow(0, new Object[]{
                        i.getTicketType(),
                        i.getTicketNumber()});
                counter++;
            }
        }

        totalReceivedStock.setText("Total: " + counter);

        //TABLE 2 : receivedAndAssignedStock
        tickets = ticketController.getAllTickets();

        DefaultTableModel receivedAndAssignedStockByAdvModel = (DefaultTableModel) receivedAndAssignedStockByAdv.getModel();
        receivedAndAssignedStockByAdvModel.setColumnCount(0);
        receivedAndAssignedStockByAdvModel.setRowCount(0);

        receivedAndAssignedStockByAdvModel.addColumn("AdvID");
        receivedAndAssignedStockByAdvModel.addColumn("Ticket Type");
        receivedAndAssignedStockByAdvModel.addColumn("Ticket No");

        counter = 0;
        for (Ticket i :tickets){
            try {
                if (i.getDateAssigned().before(dateEnd) && i.getDateAssigned().after(dateStart)) {
                    receivedAndAssignedStockByAdvModel.insertRow(0, new Object[]{
                            i.getAdvisorID(),
                            i.getTicketType(),
                            i.getTicketNumber()});
                    counter++;
                }
            }catch (Exception e){}
        }

        totalReceivedAndAssignedStockByAdv.setText("Total: " + counter);

        //TABLE 3 : old unused stock by adv ID
        tickets = ticketController.getAllTickets();

        DefaultTableModel oldAssignedUnusedStockModel = (DefaultTableModel) oldAssignedUnusedStock.getModel();
        oldAssignedUnusedStockModel.setColumnCount(0);
        oldAssignedUnusedStockModel.setRowCount(0);

        oldAssignedUnusedStockModel.addColumn("AdvID");
        oldAssignedUnusedStockModel.addColumn("Ticket Type");
        oldAssignedUnusedStockModel.addColumn("Ticket No");

        counter = 0;
        for (Ticket i :tickets){
            try {
                if (i.getDateAssigned().before(dateStart)) {
                    oldAssignedUnusedStockModel.insertRow(0, new Object[]{
                            i.getAdvisorID(),
                            i.getTicketType(),
                            i.getTicketNumber()});
                    counter++;
                }
            }catch (Exception e){}
        }

        totalOldAssignedUnusedStock.setText("Total: " + counter);

        //TABLE 4 : used stock
        tickets = ticketController.getAllTickets();

        DefaultTableModel usedStockModel = (DefaultTableModel) usedStock.getModel();
        usedStockModel.setColumnCount(0);
        usedStockModel.setRowCount(0);

        usedStockModel.addColumn("Ticket Type");
        usedStockModel.addColumn("Ticket No");

        counter = 0;
        for (Ticket i :tickets){
            try {
                if (i.getDateAssigned().before(dateEnd) && i.getDateAssigned().after(dateStart) && (i.getSaleID() != 0)) {
                    usedStockModel.insertRow(0, new Object[]{
                            i.getTicketType(),
                            i.getTicketNumber()});
                    counter++;
                }
            }catch (Exception e){}
        }

        totalUsedStock.setText("Total: " + counter);

        //TABLE 5 : unused stock
        tickets = ticketController.getAllTickets();

        DefaultTableModel unusedAgencyStockModel = (DefaultTableModel) unusedAgencyStock.getModel();
        unusedAgencyStockModel.setColumnCount(0);
        unusedAgencyStockModel.setRowCount(0);

        unusedAgencyStockModel.addColumn("Ticket Type");
        unusedAgencyStockModel.addColumn("Ticket No");

        counter = 0;
        for (Ticket i :tickets){
            try {
                if (i.getDateAssigned().before(dateEnd) && i.getDateAssigned().after(dateStart) && (i.getSaleID() == 0)) {
                    unusedAgencyStockModel.insertRow(0, new Object[]{
                            i.getTicketType(),
                            i.getTicketNumber()});
                    counter++;
                }
            }catch (Exception e){}
        }

        totalUnusedAgencyStock.setText("Total: " + counter);

        //TABLE 6 : unused stock per adv
        tickets = ticketController.getAllTickets();

        DefaultTableModel unusedStockPerAdvModel = (DefaultTableModel) unusedStockPerAdv.getModel();
        unusedStockPerAdvModel.setColumnCount(0);
        unusedStockPerAdvModel.setRowCount(0);

        unusedStockPerAdvModel.addColumn("AdvID");
        unusedStockPerAdvModel.addColumn("Ticket Type");
        unusedStockPerAdvModel.addColumn("Ticket No");

        counter = 0;
        for (Ticket i :tickets){
            try {
                if (i.getDateAssigned().before(dateEnd) && i.getDateAssigned().after(dateStart) && (i.getSaleID() == 0)) {
                    unusedStockPerAdvModel.insertRow(0, new Object[]{
                            i.getAdvisorID(),
                            i.getTicketType(),
                            i.getTicketNumber()});
                    counter++;
                }
            }catch (Exception e){}
        }

        totalUnusedStockPerAdv.setText("Total: " + counter);
    }

    public JPanel getPanel(){
        return ticketStockTurnoverReport;
    }

    public static void main(String[] args){
        FlatLightLaf.setup();

        StockTurnoverReport report = new StockTurnoverReport(Date.valueOf("2023-01-01"),
                Date.valueOf("2023-05-01"));
        JFrame frame = new JFrame();
        frame.add(report.getPanel());
        frame.pack();
        frame.setSize(1200,3000);
        frame.setVisible(true);
        frame.repaint();

        JPanelToPDF jFrameToPDF = new JPanelToPDF();
        jFrameToPDF.makePDF(report.getPanel());
    }
}

