package app.GUI;

import app.Sale.Customer;
import app.Sale.CustomerController;
import app.Sale.FlexibleDiscount;
import app.Sale.FlexibleDiscountController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.util.ArrayList;

public class AdvisorEditCustomer extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField newEmail;
    private JTextField newName;
    private JCheckBox valuedCheckBox;
    private JButton updateButton;
    private JLabel customerEmail;
    private JLabel customerName;

    private JTable discountsTable;
    private DefaultTableModel discountsTableModel;
    private TableRowSorter<DefaultTableModel> discountsSorter;

    private JTextField discountRate;
    private JTextField discountLowerBoundary;
    private JTextField discountUpperBoundary;
    private JButton addNewDiscountButton;
    private JButton deleteSelectedDiscountButton;

    private CustomerController customerController = new CustomerController();
    private FlexibleDiscountController flexibleDiscountController = new FlexibleDiscountController();

    private ArrayList<FlexibleDiscount> discounts;
    private Customer customer;

    public AdvisorEditCustomer(Customer customer) {
        this.customer = customer;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        updateTable();

        //sets the labels to display current information
        newEmail.setText(customer.getEmail());
        newName.setText(customer.getName());

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

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO update details

                
            }
        });
        ListSelectionModel selectionModel = discountsTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    //displays delete button only if discount is selected
                    deleteSelectedDiscountButton.setEnabled(discountsTable.getSelectedRow() != -1);
                }
            }
        });
        addNewDiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flexibleDiscountController.addNewFlexibleDiscount(customer.getEmail(), Double.parseDouble(discountRate.getText()),
                        Integer.parseInt(discountLowerBoundary.getText()),
                        Integer.parseInt(discountUpperBoundary.getText()));
                updateTable();
            }
        });
        deleteSelectedDiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO delete discountsTable.getSelectedRow() from discounts
                int selectedRow = discountsTable.getSelectedRow();
                int discountID = (int) discountsTable.getValueAt(selectedRow, 0);
                flexibleDiscountController.removeFlexibleDiscount(discountID);
                updateTable();
            }
        });
    }

    private void onOK() {
        //TODO save new customer details using newEmail.getText(), newName.getText() and valuedCheckBox.isSelected()
        dispose();
    }

    public void updateTable(){
        discounts = flexibleDiscountController.getFlexibleDiscountsForCustomer(customer.getEmail());

        //get the table object from GUI
        discountsTableModel = (DefaultTableModel) discountsTable.getModel();

        //resets table if it is being redrawn
        discountsTableModel.setColumnCount(0);
        discountsTableModel.setRowCount(0);

        //set columns
        discountsTableModel.addColumn("ID");
        discountsTableModel.addColumn("Discount Rate");
        discountsTableModel.addColumn("Lower Bound");
        discountsTableModel.addColumn("Upper Bound");

        //insert rows
        for (FlexibleDiscount i : discounts) {
            discountsTableModel.insertRow(0, new Object[]{
                    i.getFlexDiscID(),
                    i.getDiscountRate() + "%",
                    i.getLowerBoundary(),
                    i.getUpperBoundary()});
        }

        discountsSorter = new TableRowSorter<>(discountsTableModel);
        discountsTable.setRowSorter(discountsSorter);
    }

    private void onCancel() {
        dispose();
    }
}
