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
        frame.setVisible(true);
        frame.repaint();

        JPanelToPDF jFrameToPDF = new JPanelToPDF();
        jFrameToPDF.makePDF(report.getPanel());
    }
}

