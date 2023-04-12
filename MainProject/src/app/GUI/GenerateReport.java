package app.GUI;
import app.Account.Employee;
import com.toedter.calendar.JDateChooser;


import javax.swing.*;
import java.awt.event.*;

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
        startDay.setModel(dayModel);
        startMonth.setModel(monthModel);
        startYear.setModel(yearModel);
        endDay.setModel(dayModel);
        endMonth.setModel(monthModel);
        endYear.setModel(yearModel);

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
        String startDate = String.format("%d/%d/%d/", startDay.getValue(), startMonth.getValue(), startYear.getValue());
        String endDate = String.format("%d/%d/%d/", endDay.getValue(), endMonth.getValue(), endYear.getValue());
        //TODO generate report using startDate and endDate
        /*
        if (employee is manager) {
            generate manager report of reportType
        } else {
            generate an advisor report (we can assume it is an advisor, since there is no alternative)
        }
        dispose();

         */
    }

    private void onCancel() {
        dispose();
    }
}
