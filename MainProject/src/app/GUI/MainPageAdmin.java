package app.GUI;

import app.Main;
import app.System.SystemController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPageAdmin{
    private JTextField blanksSearchField;
    private JComboBox blanksSort;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton addBlanksButton;
    private JButton deleteBlanksButton;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField backupFrequency;
    private JButton changeFrequencyButton;
    private JButton createBackupNowButton;
    private JLabel timeSinceBackup;
    private JButton logOutButton;
    private JLabel welcomeText;
    private JLabel userInfo;
    private JLabel logo;
    private JPanel mainPageAdminPanel;

    private Main main;
    private SystemController systemController;

    public MainPageAdmin(Main main, SystemController systemController){
        this.main = main;
        this.systemController = systemController;

        addBlanksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //create new blanks from textField1 to textField2
            }
        });
        deleteBlanksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //delete blanks from textField3 to textField4

            }
        });
        changeFrequencyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //change the frequency of database updates
                systemController.setAutoBackupFreqDays(Integer.parseInt(backupFrequency.getText()));
            }
        });
        createBackupNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //create a backup immediately and download it to the user's pc
                systemController.backup();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.goToLoginPage();
            }
        });
    }

    public JPanel getPanel(){
        return mainPageAdminPanel;
    }
}
