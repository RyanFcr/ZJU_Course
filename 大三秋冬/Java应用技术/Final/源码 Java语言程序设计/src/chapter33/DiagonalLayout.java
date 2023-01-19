package chapter33;

import java.awt.*;

public class DiagonalLayout implements LayoutManager,
    java.io.Serializable {
  /** Vertical gap between the components */
  private int gap = 10;

  /** True if components are placed along the major diagonal */
  private boolean majorDiagonal = true;

  /*True if the last component is stretched to fill the space */
  private boolean lastFill = false;

  /** Constructor */
  public DiagonalLayout() {
  }

  public void addLayoutComponent(String name, Component comp) {
    //TODO: implement this java.awt.LayoutManager method;
  }

  public void removeLayoutComponent(Component comp) {
    //TODO: implement this java.awt.LayoutManager method;
  }

  public Dimension preferredLayoutSize(Container parent) {
    //TODO: implement this java.awt.LayoutManager method;
    return minimumLayoutSize(parent);
  }

  public Dimension minimumLayoutSize(Container parent) {
    //TODO: implement this java.awt.LayoutManager method;
    return new Dimension(0, 0);
  }

  public void layoutContainer(Container parent) {
    //TODO: implement this java.awt.LayoutManager method;
    int numberOfComponents = parent.getComponentCount();

    Insets insets = parent.getInsets();
    int w = parent.getSize().width - insets.left - insets.right;
    int h = parent.getSize().height - insets.bottom - insets.top;

    if (majorDiagonal) {
      int x = 10, y = 10;

      for (int j = 0; j < numberOfComponents; j++) {
        Component c = parent.getComponent(j);
        Dimension d = c.getPreferredSize();

        if (c.isVisible())
          if (lastFill && (j == numberOfComponents - 1))
            c.setBounds(x, y, w - x, h - y);
          else
            c.setBounds(x, y, d.width, d.height);
        x += d.height + gap;
        y += d.height + gap;
      }
    }
    else { // It is subdiagonal
      int x = w - 10, y = 10;

      for (int j = 0; j < numberOfComponents; j++) {
        Component c = parent.getComponent(j);
        Dimension d = c.getPreferredSize();

        if (c.isVisible())
          if (lastFill & (j == numberOfComponents - 1))
            c.setBounds(0, y, x, h - y);
          else
            c.setBounds(x, d.width, y, d.height);

        x -= (d.height + gap);
        y += d.height + gap;
      }
    }
  }

  public int getGap() {
    return gap;
  }

  public void setGap(int gap) {
    this.gap = gap;
  }

  public void setMajorDiagonal(boolean newMajorDiagonal) {
    majorDiagonal = newMajorDiagonal;
  }

  public boolean isMajorDiagonal() {
    return majorDiagonal;
  }

  public void setLastFill(boolean newLastFill) {
    lastFill = newLastFill;
  }

  public boolean isLastFill() {
    return lastFill;
  }
}
