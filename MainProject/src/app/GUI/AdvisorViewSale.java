package app.GUI;

import app.Sale.Sale;
import app.Sale.SaleController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdvisorViewSale extends JDialog {
    private JPanel contentPane;
    private JButton refundButton;
    private JButton buttonCancel;
    private JLabel priceLabel;
    private JLabel discountLabel;
    private JLabel dateLabel;
    private JLabel ticketType;
    private JLabel ticketNumber;

    public AdvisorViewSale(Sale sale) {
        setContentPane(contentPane);
        setModal(true);

        SaleController saleController = new SaleController();
        if (sale.isRefunded()) {
            refundButton.setVisible(false);
        }

        //TODO display sale details
        priceLabel.setText(String.format("Initial price is %.2f%s via %s %s",
                sale.getPriceUSD(), sale.getLocalCurrency(), sale.getPaymentProvider(),
                sale.getPaymentType()));
        discountLabel.setText("Final price: " );
        if (!sale.isPaid()) {
            //sale was not late payment
            dateLabel.setText("Sale made on " + sale.getDateSold());
        } else {
            //sale was late payment
            dateLabel.setText("Sale made on " + sale.getDateSold() + " and paid late on " + sale.getDatePaid());
        }
        ticketType.setText("");
        ticketNumber.setText("");

        refundButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //refund sale with this saleID
                int confirmation = JOptionPane.showConfirmDialog(null, "Confirm this refund?", "Confirm Refund", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    //confirm a refund
                    sale.setRefunded(true);
                    saleController.updateSale(sale);
                    dispose();
                } else {
                    //closes the option pane
                    Window option = SwingUtilities.getWindowAncestor(refundButton);
                    option.dispose();
                }
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

    private void onCancel() {
        dispose();
    }
}
