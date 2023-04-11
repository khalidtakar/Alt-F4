package app.GUI;

import app.Account.Employee;
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

    public Login(Main main, Employee employee) {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((tfEmail.getText().equals("system")) && (tfPassword.getText().equals("setup"))){
                    main.goToSetupPage();
                }else{
                    employeeController = new EmployeeController(main, employee);
                    employeeController.login(tfEmail.getText(), tfPassword.getText());
                }
            }
        });
        //TODO consider also logging in with enter
    }

    public JPanel getPanel(){
        return loginPanel;
    }

}
