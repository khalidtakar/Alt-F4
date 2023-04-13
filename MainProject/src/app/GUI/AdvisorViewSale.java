package app.GUI;

import app.Sale.Sale;
import app.Sale.SaleController;
import app.Sale.Ticket;
import app.Sale.TicketController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AdvisorViewSale extends JDialog {
    private JPanel contentPane;
    private JButton refundButton;
    private JButton buttonCancel;
    private JLabel priceLabel;
    private JLabel discountLabel;
    private JLabel dateLabel;
    private JLabel ticketType;
    private JLabel ticketNumber;
    private JLabel assignLabel;
    private JLabel finalLabel;
    private Ticket ticket;

    public AdvisorViewSale(Sale sale) {
        setContentPane(contentPane);
        setModal(true);

        SaleController saleController = new SaleController();
        if (sale.isRefunded()) {
            refundButton.setVisible(false);
        }
        TicketController ticketController = new TicketController();

        //display sale details
        ArrayList<Ticket> tickets = ticketController.getAllTickets();
        for (Ticket i : tickets) {
            if (i.getSaleID() == sale.getSaleID()) {
                this.ticket = i;
                ticketType.setText(String.valueOf(ticket.getTicketType()));
                ticketNumber.setText("Ticket No." + ticket.getTicketNumber());
            }
        }
        if (sale.getCustomerEmail() != null) {
            assignLabel.setText("This sale was assigned to " + sale.getCustomerEmail());
        }

        priceLabel.setText(String.format("Initial price is %.2f %s via %s %s",
                sale.getPriceLocal(), sale.getLocalCurrency(), sale.getPaymentProvider(), sale.getPaymentType()));
        discountLabel.setText(String.format("-%.2f%s discount and +%.2f%s tax applied",
                sale.getSaleDiscountAmount(), sale.getLocalCurrency(), sale.getTaxAmount(), sale.getLocalCurrency()));
        finalLabel.setText(String.format("Final price: %.2f USD",
                sale.getPriceUSD() + sale.getTaxAmount() - sale.getSaleDiscountAmount()));
        if (!sale.isPaid()) {
            //sale was not late payment
            dateLabel.setText("Sale made on " + sale.getDateSold());
        } else {
            //sale was late payment
            dateLabel.setText("Sale made on " + sale.getDateSold() + " and paid late on " + sale.getDatePaid());
        }

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
