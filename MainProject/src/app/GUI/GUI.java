package app.GUI;
import app.Tests.RunTests;

import javax.swing.*;
import java.awt.*;

public class GUI {

    public GUI() {

        //define basic GUI frame parameters
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new GridLayout(0, 1));

        frame.add(panel, BorderLayout.WEST);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("AirVia");
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args){
        new GUI();
    }
}

