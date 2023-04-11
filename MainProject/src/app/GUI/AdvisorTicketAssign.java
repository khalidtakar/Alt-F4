package app.GUI;

import app.Sale.Ticket;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Window;

public class AdvisorTicketAssign extends JDialog {
    private JPanel contentPane;
    private JButton latePaymentButton;
    private JButton buttonCancel;
    private JButton confirmSaleButton;
    private JButton assignTicketToRegisteredButton;
    private JComboBox comboBox1;
    private JTextField enterPriceTextField;
    private JComboBox comboBox2;
    private JLabel assignedLabel;
    private JLabel ticketType;
    private JLabel ticketNumber;

    private Ticket ticket;
    private MainPageAdvisor mainPageAdvisor;

    public AdvisorTicketAssign(Ticket ticket, MainPageAdvisor mainPageAdvisor) {
        this.ticket = ticket;
        this.mainPageAdvisor = mainPageAdvisor;

        ticketType.setText(String.valueOf(ticket.getTicketType()));
        ticketNumber.setText(String.valueOf(ticket.getTicketNumber()));


        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(latePaymentButton);

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

        assignTicketToRegisteredButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //opens customer assignment window, sets assigned customer
                AdvisorAssignCustomer dialog = new AdvisorAssignCustomer();
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setLocationRelativeTo(null);
                dialog.pack();
                dialog.setVisible(true);
                String email = dialog.getCustomerEmail();
                assignedLabel.setText("This ticket is assigned to " + email);
                //TODO assign ticket to this email
            }
        });
        confirmSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(null, "Confirm this sale?", "Confirm Sale", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    //TODO confirm this sale then dispose()
                } else {
                    //closes the option pane
                    Window option = SwingUtilities.getWindowAncestor(confirmSaleButton);
                    option.dispose();
                }
            }
        });
        latePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(null, "Set this as a late payment?", "Confirm Sale", JOptionPane.YES_NO_OPTION);
//                if () { //TODO confirm that this ticket has an assigned customer
//                    //customer is registered
//                    if (confirmation == JOptionPane.YES_OPTION) {
//                        // TODO confirm this sale then dispose()
//                    } else {
//                        //closes the option pane
//                        Window option = SwingUtilities.getWindowAncestor(latePaymentButton);
//                        option.dispose();
//                    }
//                } else {
//                    //customer is not registered warning
//                    JOptionPane.showMessageDialog(null, "Customer is not registered!", "Warning", JOptionPane.WARNING_MESSAGE);
//                }
            }
        });
    }

    private void onCancel() {
        dispose();
    }
}
