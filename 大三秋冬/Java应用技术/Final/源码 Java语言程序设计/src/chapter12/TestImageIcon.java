package chapter12;

import javax.swing.*;
import java.awt.*;

public class TestImageIcon extends JFrame {
  private ImageIcon usIcon = new ImageIcon("image/us.gif");
  private ImageIcon myIcon = new ImageIcon("image/my.jpg");
  private ImageIcon frIcon = new ImageIcon("image/fr.gif");
  private ImageIcon ukIcon = new ImageIcon("image/uk.gif");

  public TestImageIcon() {
    setLayout(new GridLayout(1, 4, 5, 5));
    add(new JLabel(usIcon));
    add(new JLabel(myIcon));
    add(new JButton(frIcon));
    add(new JButton(ukIcon));
  }

  /** Main method */
  public static void main(String[] args) {
    TestImageIcon frame = new TestImageIcon();
    frame.setTitle("TestImageIcon");
    frame.setSize(500, 125);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
