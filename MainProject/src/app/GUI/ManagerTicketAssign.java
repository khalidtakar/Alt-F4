package app.GUI;

import javax.swing.*;
import java.awt.event.*;

public class ManagerTicketAssign extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable ticketTable;
    private JTextField assignTicketsStartVal;
    private JTextField assignTicketsEndVal;

    private JLabel advisorInfo;
    private int advisorID;

    public ManagerTicketAssign(int advisorID) {
        this.advisorID = advisorID;

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

        advisorInfo.setText(advisorID + ": "); // make sure to also display the email
    }

    private void onOK() {
        // assign tickets from assignTicketsStartVal to assignTicketsEndVal to advisorID
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        ManagerTicketAssign dialog = new ManagerTicketAssign(1);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
