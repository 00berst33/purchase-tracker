package ui;

import model.exception.MultipleDecimalPointsException;
import model.exception.TooManySigFigsException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//represents a keypad that can be used to enter numbers
public class KeyPad extends JPanel implements KeyListener {
    private static final String CLR_STR = "CLR";
    private JButton[] keys;
    private JLabel label;
    private String number;
    private ClickHandler keyHandler;


    //EFFECTS: constructs a key pad and number display area
    public KeyPad() {
        number = "";
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


    //EFFECTS: gets the number entered into the keypad
    public String getNumber() throws TooManySigFigsException, MultipleDecimalPointsException {
        int decimalPoints = number.length() - number.replace(".","").length();
        if (decimalPoints > 1) {
            throw new MultipleDecimalPointsException("multiple decimal points");
        } else if (decimalPoints == 0) {
            return number;
        }

        String substring = number.substring(number.indexOf(".") + 1);
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
        return number;
    }



    //EFFECTS: clears the number entered on the keypad
    public void clearNumber() {
        number = "";
        label.setText(getLabel());
        label.repaint();
    }


    //EFFECTS: adds buttons to button panel
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


    //EFFECTS: gets label for purchase display area
    private String getLabel() {
        return number;
    }


    //represents a listener for key events
    private class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();

            if (src.getText().equals(CLR_STR)) {
                number = "";
            } else {
                number = number + src.getText();
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
