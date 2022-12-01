package ui;

import model.exception.MultipleDecimalPointsException;
import model.exception.TooManySigFigsException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyPad extends JPanel implements KeyListener {
    private static final String CLR_STR = "CLR";
    private JButton[] keys;
    private JLabel label;
    private String purchase;
    private ClickHandler keyHandler;

    /**
     * Constructor creates keypad and code display area.
     */
    public KeyPad() {
        purchase = "";
        keyHandler = new ClickHandler();
        setLayout(new BorderLayout());
        JPanel keyPanel = new JPanel();
        keyPanel.setLayout(new GridLayout(4,3));
        addButtons(keyPanel);
        add(keyPanel, BorderLayout.CENTER);
        label = new JLabel(getLabel());
        Box hbox = Box.createHorizontalBox();
        hbox.add(Box.createHorizontalGlue());
        hbox.add(label);
        hbox.add(Box.createHorizontalGlue());
        add(hbox, BorderLayout.SOUTH);
    }

    /**
     * Gets code entered.
     * @return  code entered
     */
    public String getPurchase() throws TooManySigFigsException, MultipleDecimalPointsException {
        int decimalPoints = purchase.length() - purchase.replace(".","").length();
        if (decimalPoints > 1) {
            throw new MultipleDecimalPointsException("multiple decimal points");
        }

        String substring = purchase.substring(purchase.indexOf(".") + 1);
        int sigFigs = substring.length();
        for (int i = substring.length() - 1; i >= 0; i--) {
            if (substring.charAt(i) == '0') {
                sigFigs--;
            } else {
                break;
            }
        }
        if (sigFigs > 2) {
            throw new TooManySigFigsException("too many digits after decimal point");
        }
        return purchase;
    }


    /**
     * Clears the code entered on the keypad
     */
    public void clearCode() {
        purchase = "";
        label.setText(getLabel());
        label.repaint();
    }

    /**
     * Adds buttons to button panel
     * @param p  the button panel
     */
    private void addButtons(JPanel p) {
        keys = new JButton[12];

        for (int i = 0; i < 9; i++) {
            keys[i] = new JButton(Integer.toString(i + 1));
            keys[i].addActionListener(keyHandler);
            p.add(keys[i]);
        }

        keys[9] = new JButton(CLR_STR);
        keys[9].addActionListener(keyHandler);
        p.add(keys[9]);
        keys[10] = new JButton("0");
        keys[10].addActionListener(keyHandler);
        p.add(keys[10]);
        keys[11] = new JButton(".");
        keys[11].addActionListener(keyHandler);
        p.add(keys[11]);
    }

    /**
     * Gets label for code display area
     * @return  label for code display area
     */
    private String getLabel() {
        return purchase;
    }

    /**
     * A listener for key events.
     */
    private class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();

            if (src.getText().equals(CLR_STR)) {
                purchase = "";
            } else {
                purchase = purchase + src.getText();
            }

            label.setText(getLabel());
            label.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

    @Override
    public void keyTyped(KeyEvent ke) {
        char key = ke.getKeyChar();

        if (key == '0') {
            keys[10].doClick();
        } else if (key > '0' && key <= '9') {
            keys[ke.getKeyChar() - '1'].doClick();
        }
    }
}
