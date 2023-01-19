package chapter29;

import javax.swing.*;

public class EventDispatcherThreadDemo extends JApplet {
  public EventDispatcherThreadDemo() {
    add(new JLabel("Hi, it runs from an event dispatcher thread"));
  }

  /** Main method */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new JFrame("EventDispatcherThreadDemo");
        frame.add(new EventDispatcherThreadDemo());
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setVisible(true);
      }
    });
  }
}
