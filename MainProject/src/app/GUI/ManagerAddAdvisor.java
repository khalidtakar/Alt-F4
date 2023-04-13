package app.GUI;

import app.Account.Employee;
import app.Account.EmployeeController;

import javax.swing.*;
import java.awt.event.*;

public class ManagerAddAdvisor extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField advisorEmail;
    private JPasswordField advisorPassword;
    private JTextField advisorName;

    private MainPageManager mainPageManager;
    private Employee employee;

    public ManagerAddAdvisor(MainPageManager mainPageManager, Employee employee) {
        this.mainPageManager = mainPageManager;
        this.employee = employee;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        //TODO set advisor ID display to what their new ID will be
        //advisorID.setText("ID: ");

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
        //TODO add advisor with advisorName advisorEmail advisorPassword
        EmployeeController employeeController = new EmployeeController();
        employeeController.addAdvisor(advisorEmail.getText(), advisorName.getText(),new String(advisorPassword.getPassword()));
        mainPageManager.update();
        dispose();
    }

    private void onCancel() {
        dispose();
    }


//    public static void main(String[] args) {
//        ManagerAddAdvisor dialog = new ManagerAddAdvisor();
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
//    }
}
