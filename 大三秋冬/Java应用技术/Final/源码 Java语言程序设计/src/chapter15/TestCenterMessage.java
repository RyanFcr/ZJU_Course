package chapter15;

import javax.swing.*;
import java.awt.*;

public class TestCenterMessage extends JFrame {
  public TestCenterMessage() {
    CenterMessage messagePanel = new CenterMessage();
    add(messagePanel);
    messagePanel.setBackground(Color.WHITE);
    messagePanel.setFont(new Font("Californian FB", Font.BOLD, 30));
  }

  /** Main method */
  public static void main(String[] args) {
    TestCenterMessage frame = new TestCenterMessage();
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 150);
    frame.setVisible(true);
  }
}

class CenterMessage extends JPanel {
  /** Paint the message */
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Get font metrics for the current font
    FontMetrics fm = g.getFontMetrics();

    // Find the center location to display
    int stringWidth = fm.stringWidth("Welcome to Java");
    int stringAscent = fm.getAscent();

    // Get the position of the leftmost character in the baseline
    int xCoordinate = getWidth() / 2 - stringWidth / 2;
    int yCoordinate = getHeight() / 2 + stringAscent / 2;

    g.drawString("Welcome to Java", xCoordinate, yCoordinate);
  }
}
