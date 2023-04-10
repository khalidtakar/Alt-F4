package app.GUI;

import app.Sale.Customer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;

public class AdvisorEditCustomer extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField newEmail;
    private JTextField newName;
    private JCheckBox valuedCheckBox;
    private JButton deleteCustomerButton;
    private JLabel customerEmail;
    private JLabel customerName;
    private JTable discountsTable;

    private Customer customer;

    public AdvisorEditCustomer(Customer customer) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        //sets the labels to display current information
        newEmail.setText("Customer email: " + customer.getEmail());
        customerName.setText("Customer name: " + customer.getName());

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

        deleteCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO delete this customer
            }
        });
        ListSelectionModel selectionModel = discountsTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    // TODO should open some kind of discount editor
                }
            }
        });
    }

    private void onOK() {
        //TODO save new customer details using newEmail.getText(), newName.getText() and valuedCheckBox.isSelected()
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
