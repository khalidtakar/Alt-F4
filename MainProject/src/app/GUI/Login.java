package app.GUI;

import app.Account.EmployeeController;
import app.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login{
    private JTextField tfEmail;
    private JButton loginButton;
    private JPasswordField tfPassword;
    private JPanel loginPanel;

    private EmployeeController employeeController;

    public Login(EmployeeController employeeController) {
        this.employeeController = employeeController;

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeeController.login(tfEmail.getText(), tfPassword.getText());
            }
        });
    }

    public JPanel getPanel(){
        return loginPanel;
    }

}
