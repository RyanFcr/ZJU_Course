package chapter20;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SierpinskiTriangle extends JApplet {
  private JTextField jtfOrder = new JTextField("0", 5); // Order
  private SierpinskiTrianglePanel trianglePanel = 
    new SierpinskiTrianglePanel(); // To display the pattern
  
  public SierpinskiTriangle() {
    // Panel to hold label, text field, and a button
    JPanel panel = new JPanel();
    panel.add(new JLabel("Enter an order: "));
    panel.add(jtfOrder);
    jtfOrder.setHorizontalAlignment(SwingConstants.RIGHT);
    
    // Add a Sierpinski Triangle panel to the applet
    add(trianglePanel);
    add(panel, BorderLayout.SOUTH);
    
    // Register a listener
    jtfOrder.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        trianglePanel.setOrder(Integer.parseInt(jtfOrder.getText()));
      }
    });
  }
  
  static class SierpinskiTrianglePanel extends JPanel {
    private int order = 0;
    
    /** Set a new order */
    public void setOrder(int order) {
      this.order = order;
      repaint();
    }
    
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      // Select three points in proportion to the panel size
      Point p1 = new Point(getWidth() / 2, 10);
      Point p2 = new Point(10, getHeight() - 10);
      Point p3 = new Point(getWidth() - 10, getHeight() - 10);
      
      displayTriangles(g, order, p1, p2, p3);
    }
    
    private static void displayTriangles(Graphics g, int order,
        Point p1, Point p2, Point p3) {
      if (order >= 0) {
        // Draw a triangle to connect three points
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
        g.drawLine(p1.x, p1.y, p3.x, p3.y);
        g.drawLine(p2.x, p2.y, p3.x, p3.y);
        
        // Get the midpoint on each edge in the triangle
        Point p12 = midpoint(p1, p2);
        Point p23 = midpoint(p2, p3);
        Point p31 = midpoint(p3, p1);

        // Recursively display three triangles
        displayTriangles(g, order - 1, p1, p12, p31);
        displayTriangles(g, order - 1, 
          p12, p2, p23);
        displayTriangles(g, order - 1, 
          p31, p23, p3);
      }
    }
    
    private static Point midpoint(Point p1, Point p2) {
      return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("SierpinskiTriangle");
    SierpinskiTriangle applet = new SierpinskiTriangle();
    frame.add(applet);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setVisible(true);
  }
}
