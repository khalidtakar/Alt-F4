package app.GUI;

import app.Sale.*;
import app.System.System;
import app.System.SystemController;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Window;
import java.util.*;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AdvisorAssignTicket extends JDialog {
    private JPanel contentPane;
    private JButton latePaymentButton;
    private JButton buttonCancel;
    private JButton confirmSaleButton;
    private JButton assignTicketToRegisteredButton;
    private JComboBox payTypeBox;
    private JTextField enterPriceTextField;
    private JComboBox currencyBox;
    private JLabel assignedLabel;
    private JLabel ticketType;
    private JLabel ticketNumber;
    private JTextField cardNoTextField;
    private JTextField paymentProviderTextField;
    private JLabel priceDisplay;
    private JLabel finalPriceDisplay;

    private String email;
    private int advID;

    private Ticket ticket;
    private MainPageAdvisor mainPageAdvisor;

    private System system;
    private SystemController systemController;

    public AdvisorAssignTicket(Ticket ticket, MainPageAdvisor mainPageAdvisor, int advID) {
        this.ticket = ticket;
        this.mainPageAdvisor = mainPageAdvisor;
        this.advID = advID;

        SaleController saleController = new SaleController();
        FlexibleDiscountController flexibleDiscountController = new FlexibleDiscountController();
        systemController = new SystemController();
        system = systemController.getLoad();

        ticketType.setText(String.valueOf(ticket.getTicketType()));
        ticketNumber.setText("Ticket No." + String.valueOf(ticket.getTicketNumber()));

        SortedSet<String> currencyCodes = new TreeSet<String>();
        for (Currency currency : Currency.getAvailableCurrencies()) {
            currencyCodes.add(currency.getCurrencyCode());
        }

        currencyBox.addItem("USD");
        for (String currencyCode : currencyCodes) {
            if (!currencyCode.equals("USD")) {
                currencyBox.addItem(currencyCode);
            }
        }

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
                email = dialog.getCustomerEmail();
                assignedLabel.setText("This ticket is assigned to " + email);
            }
        });
        confirmSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(null, "Confirm this sale?", "Confirm Sale", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    //confirm this sale

                    SaleController saleController = new SaleController();

                    int cardNo = 0;
                    if(!cardNoTextField.getText().isEmpty()){
                        cardNo = Integer.parseInt(cardNoTextField.getText());
                    }

                    saleController.newSale(advID,
                            email,
                            payTypeBox.getModel().getSelectedItem().toString(),
                            Double.parseDouble(enterPriceTextField.getText()),
                            cardNo,
                            paymentProviderTextField.getText(),
                            currencyBox.getSelectedItem().toString(),
                            true, ticket);

                    dispose();
                } else {
                    //closes the option pane
                    Window option = SwingUtilities.getWindowAncestor(confirmSaleButton);
                    mainPageAdvisor.updateTicketsTable();
                    option.dispose();
                }
            }
        });
        latePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerController customerController = new CustomerController();


                int confirmation = JOptionPane.showConfirmDialog(null, "Set this as a late payment?", "Confirm Sale", JOptionPane.YES_NO_OPTION);
                if (!(assignedLabel.getText().equals("This ticket is unassigned.")) && (customerController.getCustomerByEmail(email)).isValued()) {
                    //customer is registered
                    if (confirmation == JOptionPane.YES_OPTION) {

                        int cardNo = 0;
                        if(!cardNoTextField.getText().isEmpty()){
                            cardNo = Integer.parseInt(cardNoTextField.getText());
                        }

                        saleController.newSale(advID,
                                email,
                                payTypeBox.getModel().getSelectedItem().toString(),
                                Double.parseDouble(enterPriceTextField.getText()),
                                cardNo,
                                paymentProviderTextField.getText(),
                                currencyBox.getSelectedItem().toString(),
                                false, ticket);
                        mainPageAdvisor.update();
                        dispose();
                    } else {
                        //closes the option pane
                        Window option = SwingUtilities.getWindowAncestor(latePaymentButton);
                        option.dispose();
                    }
                } else {
                    //customer is not registered warning
                    JOptionPane.showMessageDialog(null, "Customer is not selected OR is not valued!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        enterPriceTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {updateLabel();}

            @Override
            public void removeUpdate(DocumentEvent e) {updateLabel();}

            @Override
            public void changedUpdate(DocumentEvent e) {updateLabel();}

            private void updateLabel() {
                String priceInput = enterPriceTextField.getText();
                try {
                    int price = Integer.parseInt(priceInput);
                    double convertedPrice = price / saleController.getExchangeRate((String) currencyBox.getSelectedItem());

                    if (email != null) {
                        //displays discount if user is registered
                        ArrayList<FlexibleDiscount> discounts = flexibleDiscountController.getFlexibleDiscountsForCustomer(email);
                        FlexibleDiscount greatest = new FlexibleDiscount("",0,0,0);

                        try {
                            double discount = saleController.calculateDiscounts(email, discounts, convertedPrice);
                            double discountedPrice = convertedPrice - (convertedPrice * 0.01 * discount);

                            priceDisplay.setText("Initial price: " + convertedPrice + " USD");
                            finalPriceDisplay.setText("Final price: " + (discountedPrice * (1 + system.getTaxRate())) + " USD");
                        }catch (Exception e){
                            priceDisplay.setText("Initial price: " + convertedPrice + " USD");
                            finalPriceDisplay.setText("Final price: " + (convertedPrice * (1 + system.getTaxRate())) + " USD");
                        }
                    } else {
                        //user is not registered - no discount
                        priceDisplay.setText("Initial price: " + convertedPrice + " USD");
                        finalPriceDisplay.setText("Final price: " + (convertedPrice * (1 + system.getTaxRate())) + " USD");
                    }
                } catch (NumberFormatException e) {
                    if (Objects.equals(priceInput, "")) {
                        //price is empty
                        priceDisplay.setText("Initial price:");
                        finalPriceDisplay.setText("Final price:");
                    } else {
                        //price error (invalid input)
                        priceDisplay.setText("PRICE ERROR!");
                        finalPriceDisplay.setText("");
                    }
                }
            }
        });
    }

    private void onCancel() {
        dispose();
    }
}
