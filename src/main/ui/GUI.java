package ui;

import model.Purchase;
import model.PurchaseTracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

//represents the main window frame
public class GUI extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private PurchaseTracker pt;
    private KeyPad kp;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;

    //EFFECTS: sets up buttons, key pad, and window
    public GUI() {
        pt = new PurchaseTracker();

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

    //EFFECTS: helper for adding buttons
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5,1));
        buttonPanel.add(new JButton(new AddPurchaseAction()));
        buttonPanel.add(new JButton(new DisplayPurchasesAction()));
        buttonPanel.add(new JButton("Get Stats")); //TODO
        buttonPanel.add(new JButton(new SaveToFileAction()));
        buttonPanel.add(new JButton(new LoadFromFileAction()));

        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

    //EFFECTS: helper for setting up key pad
    private void addKeyPad() {
        kp = new KeyPad();
        addKeyListener(kp);
        controlPanel.add(kp, BorderLayout.CENTER);
    }

    //MODIFIES: workroom
    //EFFECTS: represents action taken when a user wants to add a purchase to system
    private class AddPurchaseAction extends AbstractAction {
        AddPurchaseAction() {
            super("Add Purchase");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            Purchase purchase = new Purchase("entertainment",Double.parseDouble(kp.getPurchase()));
            kp.clearCode();

            pt.addPurchase(purchase);
        }
    }

    //EFFECTS: represents action taken when a user wants to display current purchases
    private class DisplayPurchasesAction extends AbstractAction {
        DisplayPurchasesAction() {
            super("Display Purchases");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            ScreenPrinter sp = new ScreenPrinter(GUI.this);
            desktop.add(sp);
            sp.printPurchases(pt);
        }
    }

    //EFFECTS: represents action taken when a user wants to save to file
    private class SaveToFileAction extends AbstractAction {
        SaveToFileAction() {
            super("Save to File");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            pt.saveToFile();
        }
    }

    //EFFECTS: represents action taken when a user wants to load from file
    private class LoadFromFileAction extends AbstractAction {
        LoadFromFileAction() {
            super("Load from File");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            pt.loadFromFile();
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}
