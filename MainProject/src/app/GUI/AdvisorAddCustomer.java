package app.GUI;

import app.Sale.Customer;
import app.Sale.CustomerController;

import javax.swing.*;
import java.awt.event.*;

public class AdvisorAddCustomer extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField customerEmail;
    private JTextField customerName;
    private JCheckBox valuedCheckBox;

    private MainPageAdvisor mainPageAdvisor;

    public AdvisorAddCustomer(MainPageAdvisor mainPageAdvisor) {
        this.mainPageAdvisor = mainPageAdvisor;

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
        Customer customer;

        if(valuedCheckBox.isSelected()){
            customer = new Customer(customerEmail.getText(), customerName.getText(), true);
        }else{
            customer = new Customer(customerEmail.getText(), customerName.getText(), false);
        }

        CustomerController customerController = new CustomerController();
        customerController.addNewCustomer(customer);

        mainPageAdvisor.updateCustomersTable();


        //TODO add new registered customer using customerEmail.getText() and customerName.getText()
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
