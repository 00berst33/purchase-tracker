package ui;

import javax.swing.*;
import java.awt.*;

//represents an icon panel that changes depending on how much money the user had spent
public class IconPanel extends JPanel {
    private JLabel label;
    private ImageIcon icon1;
    private ImageIcon icon2;
    private boolean smiley = true;

    //EFFECTS: initializes panel that contains the icon with a smiley face
    public IconPanel() {
        setLayout(new FlowLayout());
        this.icon1 = createImageIcon("/ui/smiley.jpg","smiley guy");
        this.icon2 = createImageIcon("/ui/sad.jpg","sad guy :(");

        Image image = icon1.getImage();
        Image newImg = image.getScaledInstance(120,120, Image.SCALE_SMOOTH);
        icon1 = new ImageIcon(newImg);

        Image image2 = icon2.getImage();
        Image newImg2 = image2.getScaledInstance(120,120, Image.SCALE_SMOOTH);
        icon2 = new ImageIcon(newImg2);

        this.label = new JLabel(icon1);
        add(label);
    }

    //EFFECTS: helper for creating icon and checking that the file path exists
    private static ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = GUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    //EFFECTS: changes icon to a sad face if the user's money spent is greater than their budget
    public void updateIcon(boolean over) {
        if (over && smiley) {
            this.label.setIcon(icon2);
            smiley = false;
        } else if (!over && !smiley) {
            this.label.setIcon(icon1);
        }
    }
}
