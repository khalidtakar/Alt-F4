package app.GUI;

import javax.swing.*;
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

    public AdvisorEditCustomer(String email) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        //sets the labels to display current information
        customerEmail.setText("Current email: " + email);
        customerName.setText("Current name: " + ""); //add the customers name here

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
                //delete this customer
            }
        });
    }

    private void onOK() {
        //save new customer details using newEmail.getText(), newName.getText() and valuedCheckBox.isSelected()
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
