package chapter18;

import javax.swing.*;

public class DisplayLabel extends JApplet {
  public DisplayLabel() {
    add(new JLabel("Great!", JLabel.CENTER));
  }
  
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("Applet is in the frame");

    // Create an instance of the applet
    DisplayLabel applet = new DisplayLabel();

    // Add the applet to the frame
    frame.add(applet);

    // Display the frame
    frame.setSize(300, 100);
    frame.setLocationRelativeTo(null); // Center the frame   
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
