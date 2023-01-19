package chapter35;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class ListCellRendererDemo extends JApplet {
  private final static int NUMBER_OF_NATIONS = 7;
  private String[] nations = new String[]
    {"Denmark", "Germany", "China", "India", "Norway", "UK", "US"};
  private ImageIcon[] icons = new ImageIcon[NUMBER_OF_NATIONS];
  private ImageIcon[] bigIcons = new ImageIcon[NUMBER_OF_NATIONS];

  // Create a list model
  private DefaultListModel listModel = new DefaultListModel();

  // Create a list using the list model
  private JList jlstNations = new JList(listModel);

  // Create a list cell renderer
  private ListCellRenderer renderer = new MyListCellRenderer();

  // Create a split pane
  private JSplitPane jSplitPane1 = new JSplitPane();

  // Create a label for displaying iamge
  private JLabel jlblImage = new JLabel("", JLabel.CENTER);

  /** Construct ListCellRenderer */
  public ListCellRendererDemo() {
    // Load small and large image icons
    for (int i = 0; i < NUMBER_OF_NATIONS; i++) {
      icons[i] = new ImageIcon(getClass().getResource(
        "/image/flagIcon" + i + ".gif"));
      listModel.addElement(new Object[]{icons[i], nations[i]});

      bigIcons[i] = new ImageIcon(getClass().getResource(
        "/image/flag" + i + ".gif"));
    }

    // Set list cell renderer
    jlstNations.setCellRenderer(renderer);
    jlstNations.setPreferredSize(new Dimension(200, 200));
    jSplitPane1.setLeftComponent(new JScrollPane(jlstNations));
    jSplitPane1.setRightComponent(jlblImage);
    jlstNations.setSelectedIndex(0);
    jlblImage.setIcon(bigIcons[0]);
    add(jSplitPane1, BorderLayout.CENTER);

    // Register listener
    jlstNations.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        jlblImage.setIcon(bigIcons[jlstNations.getSelectedIndex()]);
      }
    });
  }

  public static void main(String[] args) {
    ListCellRendererDemo applet = new ListCellRendererDemo();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("ListCellRendererDemo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400, 320);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
