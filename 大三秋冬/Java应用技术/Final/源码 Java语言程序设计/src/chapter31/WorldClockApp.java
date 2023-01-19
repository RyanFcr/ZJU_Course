package chapter31;

import javax.swing.*;

public class WorldClockApp extends JApplet {
  /** Construct the applet */
  public WorldClockApp() {
    add(new WorldClockControl());
  }

  public static void main(String[] args) {
    WorldClockApp applet = new WorldClockApp();
    JFrame frame = new JFrame("WorldClockApp");
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(applet, java.awt.BorderLayout.CENTER);
    frame.setSize(400, 320);
    frame.setVisible(true);
  }
}
