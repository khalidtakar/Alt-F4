package app.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Setup {
    private JTextField dbName;
    private JTextField server;
    private JTextField port;
    private JTextField userUsername;
    private JPasswordField userPassword;
    private JTextField adminUsername;
    private JPasswordField adminPassword;

    private JButton saveButton;
    private JButton cancelButton;

    public Setup() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO log out
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO save new details
            }
        });
    }
}
