package chapter20;

import java.awt.*;
import javax.swing.*;

public class EightQueens extends JApplet {
  public static final int SIZE = 8; // The size of the chess board
  private int[] queens = new int[SIZE]; // The queen positions 

  public EightQueens() {
    search(0); // Search for a solution from row 0
    add(new ChessBoard(), BorderLayout.CENTER);
  }

  /** Check if a queen can be placed at row i and column j */
  private boolean isValid(int row, int column) {
    for (int i = 1; i <= row; i++)
      if (queens[row - i] == column // Check column
        || queens[row - i] == column - i // Check upleft diagonal
        || queens[row - i] == column + i) // Check upright diagonal
        return false; // There is a conflict
    return true; // No conflict
  }

  /** Search for a solution starting from a specified row */
  private boolean search(int row) {
    if (row == SIZE) // Stopping condition
      return true; // A solution found to place 8 queens in 8 rows

    for (int column = 0; column < SIZE; column++) {
      queens[row] = column; // Place a queen at (row, column)
      if (isValid(row, column) && search(row + 1)) 
        return true; // Found, thus return true to exit for loop   
    }
      
    // No solution for a queen placed at any column of this row
    return false; 
  }

  class ChessBoard extends JPanel {
    private java.net.URL url 
      = getClass().getResource("image/queen.jpg");
    private Image queenImage = new ImageIcon(url).getImage();

    ChessBoard() {
      setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      // Paint the queens
      for (int i = 0; i < SIZE; i++) {
        int j = queens[i]; // The position of the queen in row i
        g.drawImage(queenImage, j * getWidth() / SIZE, 
          i * getHeight() / SIZE, getWidth() / SIZE, 
          getHeight() / SIZE, this);
      }

      // Draw the horizontal and vertical lines
      for (int i = 1; i < SIZE; i++) {
        g.drawLine(0, i * getHeight() / SIZE, 
          getWidth(), i * getHeight() / SIZE);
        g.drawLine(i * getWidth() / SIZE, 0, 
          i * getWidth() / SIZE, getHeight());
      }
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("EightQueens");
    EightQueens applet = new EightQueens();
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
