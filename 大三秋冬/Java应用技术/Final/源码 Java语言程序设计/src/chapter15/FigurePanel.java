package chapter15;

import java.awt.*;
import javax.swing.JPanel;

public class FigurePanel extends JPanel {
  // Define constants
  public static final int LINE = 1;
  public static final int RECTANGLE = 2;
  public static final int ROUND_RECTANGLE = 3;
  public static final int OVAL = 4;

  private int type = 1;
  private boolean filled;

  /** Construct a default FigurePanel */
  public FigurePanel() {
  }

  /** Construct a FigurePanel with the specified type */
  public FigurePanel(int type) {
    this.type = type;
  }

  /** Construct a FigurePanel with the specified type and filled */
  public FigurePanel(int type, boolean filled) {
    this.type = type;
    this.filled = filled;
  }

  /** Draw a figure on the panel */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Get the appropriate size for the figure
    int width = getWidth();
    int height = getHeight();

    switch (type) {
      case LINE: // Display two cross lines
        g.setColor(Color.BLACK);
        g.drawLine(10, 10, width - 10, height - 10);
        g.drawLine(width - 10, 10, 10, height - 10);
        break;
      case RECTANGLE: // Display a rectangle
        g.setColor(Color.BLUE);
        if (filled)
          g.fillRect((int)(0.1 * width), (int)(0.1 * height),
            (int)(0.8 * width), (int)(0.8 * height));
        else
          g.drawRect((int)(0.1 * width), (int)(0.1 * height),
            (int)(0.8 * width), (int)(0.8 * height));
        break;
      case ROUND_RECTANGLE: // Display a round-cornered rectangle
        g.setColor(Color.RED);
        if (filled)
          g.fillRoundRect((int)(0.1 * width), (int)(0.1 * height),
            (int)(0.8 * width), (int)(0.8 * height), 20, 20);
        else
          g.drawRoundRect((int)(0.1 * width), (int)(0.1 * height),
            (int)(0.8 * width), (int)(0.8 * height), 20, 20);
        break;
      case OVAL: // Display an oval
        g.setColor(Color.BLACK);
        if (filled)
          g.fillOval((int)(0.1 * width), (int)(0.1 * height),
            (int)(0.8 * width), (int)(0.8 * height));
        else
          g.drawOval((int)(0.1 * width), (int)(0.1 * height),
            (int)(0.8 * width), (int)(0.8 * height));
    }
  }

  /** Set a new figure type */
  public void setType(int type) {
    this.type = type;
    repaint();
  }

  /** Return figure type */
  public int getType() {
    return type;
  }

  /** Set a new filled property */
  public void setFilled(boolean filled) {
    this.filled = filled;
    repaint();
  }

  /** Check if the figure is filled */
  public boolean isFilled() {
    return filled;
  }

  /** Specify preferred size */
  public Dimension getPreferredSize() {
    return new Dimension(80, 80);
  }
}
