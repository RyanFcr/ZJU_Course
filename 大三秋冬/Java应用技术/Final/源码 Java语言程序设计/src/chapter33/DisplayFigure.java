package chapter33;

import java.awt.*;
import javax.swing.*;
import chapter15.FigurePanel;;

public class DisplayFigure extends JApplet {
  private JTabbedPane jtpFigures = new JTabbedPane();
  private FigurePanel squarePanel = new FigurePanel();
  private FigurePanel rectanglePanel = new FigurePanel();
  private FigurePanel circlePanel = new FigurePanel();
  private FigurePanel ovalPanel = new FigurePanel();

  public DisplayFigure() {
    squarePanel.setType(FigurePanel.LINE);
    rectanglePanel.setType(FigurePanel.RECTANGLE);
    circlePanel.setType(FigurePanel.ROUND_RECTANGLE);
    ovalPanel.setType(FigurePanel.OVAL);

    add(jtpFigures, BorderLayout.CENTER);
    jtpFigures.add(squarePanel, "Line");
    jtpFigures.add(rectanglePanel, "Rectangle");
    jtpFigures.add(circlePanel, "Round Rectangle");
    jtpFigures.add(ovalPanel, "Oval");

    jtpFigures.setToolTipTextAt(0, "Square");
    jtpFigures.setToolTipTextAt(1, "Rectangle");
    jtpFigures.setToolTipTextAt(2, "Circle");
    jtpFigures.setToolTipTextAt(3, "Oval");
  }

  /** Main method */
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("DisplayFigure");

    // Create an instance of the applet
    DisplayFigure applet = new DisplayFigure();
    applet.init();

    // Add the applet instance to the frame
    frame.add(applet, java.awt.BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);

    // Display the frame
    frame.setSize(400, 200);
    frame.setVisible(true);
  }
}
