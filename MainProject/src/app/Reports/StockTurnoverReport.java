package app.Reports;

import javax.swing.*;

public class StockTurnoverReport {
    private JTable receivedStock;
    private JTable oldAssignedUnusedStock;
    private JTable usedStock;
    private JTable unusedAgencyStock;
    private JTable receivedAndAssignedStockByAdv;
    private JTable unusedStockPerAdv;
    private JLabel totalUnusedStockPerAdv;
    private JLabel totaluUnusedAgencyStock;
    private JLabel totalUsedStock;
    private JLabel totalOldAssignedUnusedStock;
    private JLabel totalReceivedAndAssignedStockByAdv;
    private JLabel totalReceivedStock;
    private JLabel dateFrom;
    private JLabel dateTo;
    private JPanel ticketStockTurnoverReport;
}
