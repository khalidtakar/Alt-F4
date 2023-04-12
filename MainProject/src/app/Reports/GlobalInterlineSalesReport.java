package app.Reports;

import app.Sale.Sale;
import app.Sale.SaleController;
import app.Sale.Ticket;
import app.Sale.TicketController;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GlobalInterlineSalesReport {
    private JTable saleTable;
    private JLabel dateFrom;
    private JLabel dateTo;
    private JPanel globalInterlineSalesReport;
    private JLabel totalCommissionLabel;
    private JLabel totalAgentsDebitLabel;
    private JLabel totalCommissionAfterTaxLabel;

    private Date dateStart;
    private Date dateEnd;

    public GlobalInterlineSalesReport(Date dateFrom, Date dateTo){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        this.dateStart = dateFrom;
        this.dateEnd = dateTo;

        this.dateFrom.setText("Period from: " + dateFormat.format(dateFrom));
        this.dateTo.setText("to: " + dateFormat.format(dateTo));

        updateTable();
    }

    public void updateTable(){
        //TABLE 1 : Received Stock
        TicketController ticketController = new TicketController();
        ArrayList<Ticket> tickets = ticketController.getAllTickets();

        SaleController saleController = new SaleController();
        ArrayList<Sale> sales = saleController.getAllSales();

        DefaultTableModel saleTableModel = (DefaultTableModel) saleTable.getModel();
        saleTableModel.setColumnCount(0);
        saleTableModel.setRowCount(0);

        saleTableModel.addColumn("Ticket Type");
        saleTableModel.addColumn("Ticket No");
        saleTableModel.addColumn("Price USD");
        saleTableModel.addColumn("Discount");
        saleTableModel.addColumn("Exchange Rate");
        saleTableModel.addColumn("Currency");
        saleTableModel.addColumn("Local Price");
        saleTableModel.addColumn("Taxes");
        saleTableModel.addColumn("+Tax-DiscPriceUSD");
        saleTableModel.addColumn("Payment");
        saleTableModel.addColumn("Card No");
        saleTableModel.addColumn("Provider");
        saleTableModel.addColumn("Is Paid");
        saleTableModel.addColumn("Commission Amt.");


        double totalCommission = 0;
        double commissionAfterTax = 0;
        double agentsDebit = 0;

        for (Ticket t :tickets){
            Sale s = null;
            for(Sale sale : sales){
                if(t.getSaleID() == sale.getSaleID()){
                    s = sale;
                    break;
                }
            }

            if(t.getDateReceived().before(dateEnd) && t.getDateReceived().after(dateStart)){
                saleTableModel.insertRow(0, new Object[]{
                        t.getTicketType(),
                        t.getTicketNumber(),
                        s.getPriceUSD(),
                        s.getSaleDiscountAmount(),
                        s.getExchangeRate(),
                        s.getLocalCurrency(),
                        s.getPriceLocal(),
                        s.getTaxAmount(),
                        s.getPriceUSD()+s.getTaxAmount()-s.getSaleDiscountAmount(),
                        s.getPaymentType(),
                        s.getCardNo(),
                        s.getPaymentProvider(),
                        s.isPaid(),
                        s.getSaleCommissionAmount()});

                totalCommission += s.getSaleCommissionAmount();
                commissionAfterTax += (s.getSaleCommissionAmount() - s.getTaxAmount());
                agentsDebit += (s.getPriceUSD() - s.getSaleDiscountAmount() - s.getSaleCommissionAmount());
            }
        }

        totalCommissionLabel.setText("Total Commission: " + totalCommission);
        totalCommissionAfterTaxLabel.setText("Commission After Tax: " + commissionAfterTax);
        totalAgentsDebitLabel.setText("Total Agent's Debit: " + agentsDebit);

    }

    public JPanel getPanel(){
        return globalInterlineSalesReport;
    }

    public static void main(String[] args){
        FlatLightLaf.setup();

        GlobalInterlineSalesReport report = new GlobalInterlineSalesReport(Date.valueOf("2023-01-01"),
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
