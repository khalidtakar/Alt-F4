package app.GUI;

import javax.swing.*;
import java.awt.event.*;

public class AdvisorTicketAssign extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton confirmSaleButton;
    private JCheckBox latePaymentCheckBox;
    private JButton assignTicketToRegisteredButton;
    private JComboBox comboBox1;
    private JTextField enterPriceTextField;
    private JComboBox comboBox2;
    private JTable table1;
    private JButton addNewCouponButton;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JComboBox comboBox6;

    public AdvisorTicketAssign(int ticketNumber) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
        // add your code here
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        AdvisorTicketAssign dialog = new AdvisorTicketAssign();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
