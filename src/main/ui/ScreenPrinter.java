package ui;

import model.Purchase;
import model.PurchaseTracker;

import javax.swing.*;
import java.awt.*;

//represents a screen printer for printing all purchases to screen
public class ScreenPrinter extends JInternalFrame {
    private static final int WIDTH = 295;
    private static final int HEIGHT = 200;
    private JTextArea textArea;

    //EFFECTS: constructs window in which log of purchases will be printed
    public ScreenPrinter(Component parent) {
        super("Purchase Log", false, true, false, false);
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        setSize(WIDTH, HEIGHT);
        setPosition(parent);
        setVisible(true);
    }

    //EFFECTS: prints purchases in workroom to screen
    public void printPurchases(PurchaseTracker pt) {
        for (Purchase purchase : pt.getPurchases()) {
            textArea.setText(textArea.getText() + "$" + purchase.getValue() + "\n" + purchase.getCategory() + "\n\n");
        }

        repaint();
    }

    //EFFECTS: sets position of the window the purchases will be printed to
    private void setPosition(Component parent) {
        setLocation(parent.getWidth() - getWidth() - 10,
                parent.getHeight() - getHeight() - 35);
    }
}
