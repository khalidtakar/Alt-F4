package app.GUI;
import com.toedter.calendar.JDateChooser;


import javax.swing.*;
import java.awt.event.*;

public class ManagerGenerateReport extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox reportType;
    private JPanel reportDetailsPane;
    private JDateChooser reportStartDate;
    private JDateChooser reportEndDate;

    public ManagerGenerateReport() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        /*add date selectors
        JDateChooser reportStartDate = new JDateChooser();
        JDateChooser reportEndDate = new JDateChooser();
        reportDetailsPane.add(reportStartDate, reportEndDate);
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
        //TODO generate report using reportStartDate.getDate() and reportEndDate.getDate() [ as soon as i get JDateChooser to work >:( ]
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        ManagerGenerateReport dialog = new ManagerGenerateReport();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        reportStartDate.setDateFormatString("dd/MM/yyyy");
        reportEndDate.setDateFormatString("dd/MM/yyyy");
    }
}
