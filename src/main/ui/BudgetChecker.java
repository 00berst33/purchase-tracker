package ui;

import model.Purchase;
import model.PurchaseTracker;

import javax.swing.*;
import java.awt.*;

//represents a budget checker for printing budget and checking how close a user is to it
public class BudgetChecker extends JInternalFrame {
    private static final int WIDTH = 295;
    private static final int HEIGHT = 200;
    private JTextArea textArea;

    //EFFECTS: constructs window in which the user's budget will be printed
    public BudgetChecker(Component parent) {
        super("Budget Checker", false, true, false, false);
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        setSize(WIDTH, HEIGHT);
        setPosition(parent);
        setVisible(true);
    }

    //EFFECTS: prints budget and how close the user is to it to the screen
    public void print(PurchaseTracker pt) {
        String string = "Your current budget is $" + pt.getBudget() + ".\n";
        double difference = pt.getBudget() - pt.getMoneySpent();
        difference = Math.round(difference * 100.0) / 100.0;
        if (difference > 0) {
            string += "You are $" + difference + " away from hitting it.";
        } else if (difference < 0) {
            string += "You are $" + (difference * - 1) + " over it.";
        } else {
            string += "You have hit it exactly.";
        }
        textArea.setText(string);

        repaint();
    }

    //EFFECTS: sets position of the window the purchases will be printed to
    private void setPosition(Component parent) {
        setLocation(parent.getWidth() - getWidth() - 305,
                parent.getHeight() - getHeight() - 35);
    }
}
