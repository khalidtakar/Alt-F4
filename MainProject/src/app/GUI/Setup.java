package app.GUI;

import app.Main;
import app.System.SetupController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Setup {
    private JTextField dbName;
    private JTextField server;
    private JTextField port;
    private JTextField url;

    private JButton saveButton;
    private JButton cancelButton;
    private JPanel setupPanel;

    private SetupController setupController = new SetupController();

    private Main main;

    public Setup(Main main) {
        this.main = main;

        dbName.setText(setupController.getDbName());
        server.setText(setupController.getServerName());
        port.setText(setupController.getPortNumber());
        url.setText(setupController.getUrl());

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.goToLoginPage();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupController.updateSetup(
                        server.getText(),
                        url.getText(),
                        port.getText(),
                        dbName.getText());
            }
        });
    }

    public JPanel getPanel(){
        return setupPanel;
    }
}
