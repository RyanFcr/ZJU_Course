package chapter16;

import java.awt.event.*;
import javax.swing.JFrame;

public class AdapterDemo extends JFrame {
  public static void main(String[] args) {
    AdapterDemo frame = new AdapterDemo();
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("AdapterDemo");
    frame.setSize(220, 80);
    frame.setVisible(true);
  }

  public AdapterDemo() {
    addWindowListener(new WindowAdapter() {
      public void windowActivated(WindowEvent event) {
        System.out.println("Window activated");
      }
    });
  }
}
