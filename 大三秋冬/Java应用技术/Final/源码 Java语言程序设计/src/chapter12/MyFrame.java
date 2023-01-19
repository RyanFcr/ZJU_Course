package chapter12;

import javax.swing.*;

public class MyFrame {
  public static void main(String[] args) {
    JFrame frame = new JFrame("MyFrame"); // Create a frame
    frame.setSize(400, 300); // Set the frame size
    frame.setLocationRelativeTo(null); // New since JDK 1.4
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true); // Display the frame
  }
}
