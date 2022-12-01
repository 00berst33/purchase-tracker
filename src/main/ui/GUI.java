package ui;

import model.Purchase;
import model.PurchaseTracker;
import model.exception.MultipleDecimalPointsException;
import model.exception.TooManySigFigsException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

//represents the main window frame
public class GUI extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private PurchaseTracker pt;
    private KeyPad kp;
    private IconPanel ip;
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
        addIcon();

        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centerOnScreen();
        setVisible(true);
    }

    //EFFECTS: helper for adding buttons
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,2));
        buttonPanel.add(new JButton(new AddPurchaseAction()));
        buttonPanel.add(new JButton(new DisplayPurchasesAction()));
        buttonPanel.add(new JButton(new SetBudgetAction()));
        buttonPanel.add(new JButton(new CheckBudgetAction()));
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

    //EFFECTS: helper for setting up icon
    private void addIcon() {
        ip = new IconPanel();
        controlPanel.add(ip, BorderLayout.EAST);
    }

    //EFFECTS: centers application on screen
    private void centerOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    //MODIFIES: workroom
    //EFFECTS: represents action taken when a user wants to add a purchase to system
    private class AddPurchaseAction extends AbstractAction {
        AddPurchaseAction() {
            super("Add Purchase");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                double value = Double.parseDouble(kp.getNumber());
                Object[] possibleValues = { "Entertainment", "Shopping", "Dining", "Groceries", "Travel", "Other" };
                Object category = JOptionPane.showInputDialog(null,
                        "Choose the category of your purchase:", "Input",
                        JOptionPane.INFORMATION_MESSAGE, null,
                        possibleValues, possibleValues[0]);
                if (category != null) {
                    Purchase purchase = new Purchase((String) category, value);
                    pt.addPurchase(purchase);
                }
            } catch (MultipleDecimalPointsException | TooManySigFigsException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            kp.clearNumber();
            boolean over = pt.getBudget() - pt.getMoneySpent() < 0;
            ip.updateIcon(over);
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

    //EFFECTS: represents action taken when a user wants to set a budget
    private class SetBudgetAction extends AbstractAction {
        SetBudgetAction() {
            super("Set Budget");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                double budget = Double.parseDouble(kp.getNumber());
                pt.changeBudget(budget);
            } catch (MultipleDecimalPointsException | TooManySigFigsException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            kp.clearNumber();
        }
    }

    //EFFECTS: represents action taken when a user wants to check their budget and how close to it they are
    private class CheckBudgetAction extends AbstractAction {
        CheckBudgetAction() {
            super("Check Budget");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            BudgetChecker bc = new BudgetChecker(GUI.this);
            desktop.add(bc);
            bc.print(pt);
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
            JOptionPane.showMessageDialog(null, "Saved to File Successfully");
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
            JOptionPane.showMessageDialog(null, "Loaded from File Successfully");
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}
