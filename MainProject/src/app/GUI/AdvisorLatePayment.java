package app.GUI;

import app.Sale.Sale;
import app.Sale.SaleController;

import javax.swing.*;
import java.awt.*;
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
    private JTextField cardNoTextField;
    private JLabel currency;
    private JTextField providerTextField;
    private SalesAdvisor salesAdvisor;

    private Sale sale;

    public AdvisorLatePayment(int saleID, SalesAdvisor salesAdvisor) {
        this.salesAdvisor = salesAdvisor;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        SaleController saleController = new SaleController();
        sale = saleController.getSaleByID(saleID);

        //set customer details and payment amount
        customerEmail.setText(sale.getCustomerEmail());
        paymentAmount.setText("Amount: " + sale.getPriceUSD());
        currency.setText(sale.getLocalCurrency());

        /*TODO add cardNo and payment provider to inout text fields*/


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
                //cancel this payment
                saleController.cancelLatePayment(sale);
                salesAdvisor.update();
            }
        });
        confirmPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Long cardNo = 0l;
                if(!cardNoTextField.getText().isEmpty()){
                    cardNo = Long.parseLong(cardNoTextField.getText());
                }
                //confirm this payment
                int confirmation = JOptionPane.showConfirmDialog(null, "Confirm this sale?", "Confirm Sale", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    saleController.makeLatePayment(sale,cardNo,providerTextField.getText(), paymentMethod.getSelectedItem().toString());
                    salesAdvisor.update();
                } else {
                    Window option = SwingUtilities.getWindowAncestor(confirmPaymentButton);
                    option.dispose();
                }

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
