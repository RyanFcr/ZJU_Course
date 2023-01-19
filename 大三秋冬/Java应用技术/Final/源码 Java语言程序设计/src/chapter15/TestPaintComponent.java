package chapter15;

import javax.swing.*;
import java.awt.Graphics;

public class TestPaintComponent extends JFrame {
  public TestPaintComponent() {
    add(new NewPanel());
  }

  public static void main(String[] args) {
    TestPaintComponent frame = new TestPaintComponent();
    frame.setTitle("TestPaintComponent");
    frame.setSize(200, 100);
    frame.setLocationRelativeTo(null); // Center the frame   
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);    
  }
}

class NewPanel extends JPanel { 
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawLine(0, 0, 50, 50);
    g.drawString("Banner", 0, 40);
  }
}
