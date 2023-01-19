package chapter27;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KnightTourApp extends JApplet {
  private KnightTourModel model = new KnightTourModel();
  private PaintKnightTour paintKnightTour = new PaintKnightTour();
  private JTextField jtfRow = new JTextField(2);
  private JTextField jtfColumn = new JTextField(2);
  private JButton jbtSearch = new JButton("Search");
  
  public KnightTourApp() {    
    JPanel panel = new JPanel();
    panel.add(new JLabel("Specify a starting position, row: "));
    panel.add(jtfRow);
    panel.add(new JLabel("column: "));
    panel.add(jtfColumn);
    panel.add(jbtSearch);
    add(paintKnightTour, BorderLayout.CENTER);
    add(panel, BorderLayout.SOUTH);
    
    jbtSearch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {      
        int position = Integer.parseInt(jtfRow.getText()) * 8 +
          Integer.parseInt(jtfColumn.getText());
        paintKnightTour.displayPath(
          model.getHamiltonianPath(position));  
      }
    });
  }

  /** A panel to paint the chessboard and the knight tour */
  private static class PaintKnightTour extends JPanel {
    private List<Integer> path; // A Knight tour path

    public PaintKnightTour() {
      setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }
    
    public void displayPath(List<Integer> path) {
      this.path = path;
      repaint();
    }
    
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      // Display horizontal lines
      for (int i = 0; i < 8; i++) 
        g.drawLine(0, i * getHeight() / 8, 
          getWidth(), i * getHeight() / 8);
      
      // Display vertical lines
      for (int i = 0; i < 8; i++) 
        g.drawLine(i * getWidth() / 8, 0, 
          (int)i * getWidth() / 8, getHeight());

      if (path == null) return; // No path to be displayed yet

      for (int i = 0; i < path.size() - 1; i++) {
        int u = path.get(i);
        int v = path.get(i + 1);
        
        // Knight moves from u and v. Draw a line to connect u and v
        g.drawLine((u % 8) * getWidth() / 8 + getWidth() / 16,
          (u / 8) * getHeight() / 8 + getHeight() / 16,
          (v % 8) * getWidth() / 8 + getWidth() / 16,
          (v / 8) * getHeight() / 8 + getHeight() / 16);
      }
    }
  }
  
  public static void main(String[] args) {   
    // Create a frame
    JFrame frame = new JFrame("Knight's Tour");

    // Create an instance of the applet
    KnightTourApp applet = new KnightTourApp();

    // Add the applet instance to the frame
    frame.add(applet, BorderLayout.CENTER);

    // Display the frame
    frame.setSize(400, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);    
  }
}
