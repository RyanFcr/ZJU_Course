package chapter18;

import javax.swing.Timer;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Ball extends JPanel {
  private int delay = 10;

  // Create a timer with delay 1000 ms
  private Timer timer = new Timer(delay, new TimerListener());

  private int x = 0; private int y = 0; // Current ball position
  private int radius = 5; // Ball radius
  private int dx = 2; // Increment on ball's x-coordinate
  private int dy = 2; // Increment on ball's y-coordinate

  public Ball() {
    timer.start();
  }

  private class TimerListener implements ActionListener {
    /** Handle the action event */
    public void actionPerformed(ActionEvent e) {
      repaint();
    }
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setColor(Color.red);

    // Check boundaries
    if (x < radius) dx = Math.abs(dx);
    if (x > getWidth() - radius) dx = -Math.abs(dx);
    if (y < radius) dy = Math.abs(dy);
    if (y > getHeight() - radius) dy = -Math.abs(dy);

    // Adjust ball position
    x += dx;
    y += dy;
    g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
  }

  public void suspend() {
    timer.stop(); // Suspend timer
  }

  public void resume() {
    timer.start(); // Resume timer
  }

  public void setDelay(int delay) {
    this.delay = delay;
    timer.setDelay(delay);
  }
}
