package app.GUI;

import app.Sale.Customer;
import app.Sale.CustomerController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.util.ArrayList;

public class AdvisorAssignCustomer extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    private JTable customersTable;
    private DefaultTableModel customersTableModel;
    private TableRowSorter<DefaultTableModel> customersSorter;

    private JTextField searchCustomersTextField;

    private CustomerController customerController = new CustomerController();

    String email;
    private ArrayList<Customer> customers;


    public AdvisorAssignCustomer() {
        updateCustomersTable();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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

        searchCustomersTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchCustomersTextField.getText();
                //TODO display all entries in customersTable with an instance of 'query'
            }
        });

        ListSelectionModel selectionModel = customersTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    //sets email to selected customers
                    int selectedRow = customersTable.getSelectedRow();
                    email = (String) customersTable.getValueAt(selectedRow, 0);
                }
            }
        });
    }

    //TODO set table contents

    private void onCancel() {
        dispose();
    }

    public void updateCustomersTable(){
        customers = customerController.getAllCustomers();

        //get the table object from GUI
        customersTableModel = (DefaultTableModel) customersTable.getModel();

        //resets table if it is being redrawn
        customersTableModel.setColumnCount(0);
        customersTableModel.setRowCount(0);

        //set columns
        customersTableModel.addColumn("Email");
        customersTableModel.addColumn("Name");
        customersTableModel.addColumn("Is Valued");
        customersTableModel.addColumn("Spent This Month");
        customersTableModel.addColumn("Amount To Return");
        customersTableModel.addColumn("Fixed Discount Rate");

        //insert rows
        for (Customer i : customers) {
            customersTableModel.insertRow(0, new Object[]{
                    i.getEmail(),
                    i.getName(),
                    i.isValued(),
                    i.getSpentThisMonth(),
                    i.getDiscountToRefundOrReturn(),
                    i.getFixedDiscountRate()});
        }

        customersSorter = new TableRowSorter<>(customersTableModel);
        customersTable.setRowSorter(customersSorter);
    }

    public String getCustomerEmail() {
        return email;
    }
}
