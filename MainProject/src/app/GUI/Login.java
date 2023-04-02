package app.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends GUI{
    private JTextField tfEmail;
    private JButton loginButton;
    private JPasswordField tfPassword;

    public Login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //login
            }
        });
    }
}
