package app.GUI;

import javax.swing.*;
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

    public AdvisorViewSale(int saleID) {
        setContentPane(contentPane);
        setModal(true);

        //TODO display sale details
        priceLabel.setText("");
        discountLabel.setText("");
        dateLabel.setText("");
        ticketType.setText("");
        ticketNumber.setText("");

        refundButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO refund sale with this saleID
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
        // add your code here if necessary
        dispose();
    }
}
