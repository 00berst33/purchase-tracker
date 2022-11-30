package ui;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;
    private KeyPad kp;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;

    public GUI() {
        desktop = new JDesktopPane();
        controlPanel = new JInternalFrame("Control Panel", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());

        setContentPane(desktop);
        setTitle("CPSC 210: Purchase Tracker");
        setSize(WIDTH, HEIGHT);

        addButtonPanel();
        addKeyPad();

        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5,1));
        buttonPanel.add(new JButton("Add Purchase"));
        buttonPanel.add(new JButton("Display Purchases"));
        buttonPanel.add(new JButton("Get Stats"));
        buttonPanel.add(new JButton("Save to File"));
        buttonPanel.add(new JButton("Load from File"));

        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

    private void addKeyPad() {
        kp = new KeyPad();
        addKeyListener(kp);
        controlPanel.add(kp, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
