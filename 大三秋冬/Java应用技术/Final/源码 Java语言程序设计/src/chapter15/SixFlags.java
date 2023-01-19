package chapter15;

import javax.swing.*;
import java.awt.*;

public class SixFlags extends JFrame {
  public SixFlags() {
    Image image1 = new ImageIcon("image/us.gif").getImage();
    Image image2 = new ImageIcon("image/ca.gif").getImage();
    Image image3 = new ImageIcon("image/india.gif").getImage();
    Image image4 = new ImageIcon("image/uk.gif").getImage();
    Image image5 = new ImageIcon("image/china.gif").getImage();
    Image image6 = new ImageIcon("image/norway.gif").getImage();

    setLayout(new GridLayout(2, 0, 5, 5));
    add(new ImageViewer(image1));
    add(new ImageViewer(image2));
    add(new ImageViewer(image3));
    add(new ImageViewer(image4));
    add(new ImageViewer(image5));
    add(new ImageViewer(image6));
  }

  public static void main(String[] args) {
    SixFlags frame = new SixFlags();
    frame.setTitle("SixFlags");
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 320);
    frame.setVisible(true);
  }
}
