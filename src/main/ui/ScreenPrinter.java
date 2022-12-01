package ui;

import model.Purchase;
import model.PurchaseTracker;

import javax.swing.*;
import java.awt.*;

public class ScreenPrinter extends JInternalFrame {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private JTextArea textArea;

    /**
     * Constructor sets up window in which log will be printed on screen
     * @param parent  the parent component
     */
    public ScreenPrinter(Component parent) {
        super("Purchase log", false, true, false, false);
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        setSize(WIDTH, HEIGHT);
        setPosition(parent);
        setVisible(true);
    }

    public void printPurchases(PurchaseTracker pt) {
        for (Purchase purchase : pt.getPurchases()) {
            textArea.setText(textArea.getText() + "$" + purchase.getValue() + "\n\n");
        }

        repaint();
    }

    /**
     * Sets the position of window in which log will be printed relative to
     * parent
     * @param parent  the parent component
     */
    private void setPosition(Component parent) {
        setLocation(parent.getWidth() - getWidth() - 20,
                parent.getHeight() - getHeight() - 20);
    }
}
