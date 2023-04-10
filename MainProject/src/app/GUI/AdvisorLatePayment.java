package app.GUI;

import javax.swing.*;
import java.awt.event.*;

public class AdvisorLatePayment extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox paymentMethod;
    private JButton cancelPaymentButton;
    private JButton confirmPaymentButton;
    private JLabel customerEmail;
    private JLabel customerName;
    private JLabel paymentAmount;

    public AdvisorLatePayment(int saleID) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        //TODO set customer details and payment amount
        customerName.setText("Name: ");
        customerEmail.setText("Email: ");
        paymentAmount.setText("Payment amount: ");


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
        cancelPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO cancel this payment
            }
        });
        confirmPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO confirm this payment using paymentMethod.getSelectedItem()
            }
        });
    }

    private void onCancel() {
        dispose();
    }

//    public static void main(String[] args) {
//        AdvisorLatePayment dialog = new AdvisorLatePayment();
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
//    }
}
