package chapter33;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ScrollMap extends JApplet {
  // Create images in labels
  private JLabel lblIndianaMap = new JLabel(
    new ImageIcon(getClass().getResource("image/indianaMap.gif")));
  private JLabel lblOhioMap = new JLabel(
    new ImageIcon(getClass().getResource("/image/ohioMap.gif")));

  // Create a scroll pane to scroll map in the labels
  private JScrollPane jspMap = new JScrollPane(lblIndianaMap);

  public ScrollMap() {
    // Create a combo box for selecting maps
    JComboBox jcboMap = new JComboBox(new String[]{"Indiana",
      "Ohio"});

    // Panel p to hold combo box
    JPanel p = new JPanel();
    p.setLayout(new BorderLayout());
    p.add(jcboMap);
    p.setBorder(new TitledBorder("Select a map to display"));

    // Set row header, column header and corner header
    jspMap.setColumnHeaderView(new JLabel(new ImageIcon(getClass().
      getResource("/image/horizontalRuler.gif"))));
    jspMap.setRowHeaderView(new JLabel(new ImageIcon(getClass().
      getResource("/image/verticalRuler.gif"))));
    jspMap.setCorner(JScrollPane.UPPER_LEFT_CORNER,
      new CornerPanel(JScrollPane.UPPER_LEFT_CORNER));
    jspMap.setCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER,
      new CornerPanel(JScrollPane.UPPER_RIGHT_CORNER));
    jspMap.setCorner(JScrollPane.LOWER_RIGHT_CORNER,
      new CornerPanel(JScrollPane.LOWER_RIGHT_CORNER));
    jspMap.setCorner(JScrollPane.LOWER_LEFT_CORNER,
      new CornerPanel(JScrollPane.LOWER_LEFT_CORNER));

    // Add the scroll pane and combo box panel to the frame
    add(jspMap, BorderLayout.CENTER);
    add(p, BorderLayout.NORTH);

    // Register listener
    jcboMap.addItemListener(new ItemListener() {
      /** Show the selected map */
      public void itemStateChanged(ItemEvent e) {
        String selectedItem = (String)e.getItem();
        if (selectedItem.equals("Indiana")) {
          // Set a new view in the view port
          jspMap.setViewportView(lblIndianaMap);
        }
        else if (selectedItem.equals("Ohio")) {
          // Set a new view in the view port
          jspMap.setViewportView(lblOhioMap);
        }

        // Revalidate the scroll pane
        jspMap.revalidate();
      }
    });
  }

  public static void main(String[] args) {
    ScrollMap applet = new ScrollMap();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("ScrollMap");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2,
      (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }
}

// A panel displaying a line used for scroll pane corner
class CornerPanel extends JPanel {
  // Line location
  private String location;

  public CornerPanel(String location) {
    this.location = location;
  }

  /** Draw a line depending on the location */
  protected void paintComponent(Graphics g) {
    super.paintComponents(g);

    if (location == "UPPER_LEFT_CORNER")
      g.drawLine(0, getHeight(), getWidth(), 0);
    else if (location == "UPPER_RIGHT_CORNER")
      g.drawLine(0, 0, getWidth(), getHeight());
    else if (location == "LOWER_RIGHT_CORNER")
      g.drawLine(0, getHeight(), getWidth(), 0);
    else if (location == "LOWER_LEFT_CORNER")
      g.drawLine(0, 0, getWidth(), getHeight());
  }
}
