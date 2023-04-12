package app.GUI;

import app.Sale.Sale;
import app.Sale.SaleController;

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

        //TODO set customer details and payment amount
        customerEmail.setText(sale.getCustomerEmail());
        paymentAmount.setText("Amount: " + sale.getPriceUSD());
        currency.setText(sale.getLocalCurrency());


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
                saleController.cancelLatePayment(sale);
                salesAdvisor.update();
                //TODO cancel this payment
            }
        });
        confirmPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int cardNo = 0;
                if(!cardNoTextField.getText().isEmpty()){
                    cardNo = Integer.parseInt(cardNoTextField.getText());
                }
                saleController.makeLatePayment(sale,cardNo,providerTextField.getText(), paymentMethod.getSelectedItem().toString());
                salesAdvisor.update();
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
