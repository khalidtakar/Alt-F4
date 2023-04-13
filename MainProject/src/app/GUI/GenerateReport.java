package app.GUI;
import app.Account.Employee;
import app.Reports.GlobalInterlineSalesReport;
import app.Reports.JPanelToPDF;
import app.Reports.StockTurnoverReport;
import com.toedter.calendar.JDateChooser;


import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;

public class GenerateReport extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox reportType;

    private JPanel reportDetailsPane;
    private JSpinner startDay;
    private JSpinner startMonth;
    private JSpinner startYear;
    private JSpinner endDay;
    private JSpinner endMonth;
    private JSpinner endYear;

    private JDateChooser reportStartDate;
    private JDateChooser reportEndDate;

    private Employee employee;

    public GenerateReport(Employee employee) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.employee = employee;

        SpinnerModel dayModel = new SpinnerNumberModel(1, 1, 31, 1);
        SpinnerModel monthModel = new SpinnerNumberModel(1, 1, 12, 1);
        SpinnerModel yearModel = new SpinnerNumberModel(1, 1, 2023, 1); //TODO set maximum to current year
        SpinnerModel endDayModel = new SpinnerNumberModel(1, 1, 31, 1);
        SpinnerModel endMonthModel = new SpinnerNumberModel(1, 1, 12, 1);
        SpinnerModel endYearModel = new SpinnerNumberModel(1, 1, 2023, 1); //TODO set maximum to current year

        startDay.setModel(dayModel);
        startMonth.setModel(monthModel);
        startYear.setModel(yearModel);
        endDay.setModel(endDayModel);
        endMonth.setModel(endMonthModel);
        endYear.setModel(endYearModel);

        //TODO edit combobox based on user
        /*
        if (employee is advisor) {
            reportType.removeItem("Stock");
        }
         */


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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
    }

    private void onOK() {
        // Create the start date string with the desired format (yyyy-MM-dd)
        String startDate = String.format("%04d-%02d-%02d", startYear.getValue(), startMonth.getValue(), startDay.getValue());

        // Create the end date string with the desired format (yyyy-MM-dd)
        String endDate = String.format("%04d-%02d-%02d", endYear.getValue(), endMonth.getValue(), endDay.getValue());

        java.sql.Date sqlStartDate = java.sql.Date.valueOf(startDate);
        java.sql.Date sqlEndDate = java.sql.Date.valueOf(endDate);

        if(reportType.getSelectedItem().toString().equals("Interline")){
            GlobalInterlineSalesReport report = new GlobalInterlineSalesReport(sqlStartDate,
                    sqlEndDate);
            JFrame frame = new JFrame();
            frame.add(report.getPanel());
            frame.pack();
            frame.setSize(1200,3000);
            frame.setVisible(true);
            frame.repaint();

            JPanelToPDF jFrameToPDF = new JPanelToPDF();
            jFrameToPDF.makePDF(report.getPanel());
        } else {
            StockTurnoverReport report = new StockTurnoverReport(sqlStartDate,
                    sqlEndDate);
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

    private void onCancel() {
        dispose();
    }
}
