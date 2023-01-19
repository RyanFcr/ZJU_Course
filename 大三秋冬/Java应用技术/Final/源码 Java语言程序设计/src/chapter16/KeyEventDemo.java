package chapter16;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyEventDemo extends JFrame {
  private KeyboardPanel keyboardPanel = new KeyboardPanel();

  /** Initialize UI */
  public KeyEventDemo() {
    // Add the keyboard panel to accept and display user input
    add(keyboardPanel);

    // Set focus
    keyboardPanel.setFocusable(true);
  }

  /** Main method */
  public static void main(String[] args) {
    KeyEventDemo frame = new KeyEventDemo();
    frame.setTitle("KeyEventDemo");
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 300);
    frame.setVisible(true);
  }

  // Inner class: KeyboardPanel for receiving key input
  static class KeyboardPanel extends JPanel {
    private int x = 100;
    private int y = 100;
    private char keyChar = 'A'; // Default key

    public KeyboardPanel() {
      addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
          switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN: y += 10; break;
            case KeyEvent.VK_UP: y -= 10; break;
            case KeyEvent.VK_LEFT: x -= 10; break;
            case KeyEvent.VK_RIGHT: x += 10; break;
            default: keyChar = e.getKeyChar();
          }

          repaint();
        }
      });
    }

    /** Draw the character */
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
      g.drawString(String.valueOf(keyChar), x, y);
    }
  }
}
