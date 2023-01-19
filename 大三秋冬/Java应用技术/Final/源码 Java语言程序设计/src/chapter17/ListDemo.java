package chapter17;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class ListDemo extends JFrame {
  final int NUMBER_OF_FLAGS = 9;

  // Declare an array of Strings for flag titles
  private String[] flagTitles = {"Canada", "China", "Denmark",
    "France", "Germany", "India", "Norway", "United Kingdom",
    "United States of America"};

  // The list for selecting countries
  private JList jlst = new JList(flagTitles);

  // Declare an ImageIcon array for the national flags of 9 countries
  private ImageIcon[] imageIcons = {
    new ImageIcon("image/ca.gif"),
    new ImageIcon("image/china.gif"),
    new ImageIcon("image/denmark.gif"),
    new ImageIcon("image/fr.gif"),
    new ImageIcon("image/germany.gif"),
    new ImageIcon("image/india.gif"),
    new ImageIcon("image/norway.gif"),
    new ImageIcon("image/uk.gif"),
    new ImageIcon("image/us.gif")
  };

  // Arrays of labels for displaying images
  private JLabel[] jlblImageViewer = new JLabel[NUMBER_OF_FLAGS];

  public static void main(String[] args) {
    ListDemo frame = new ListDemo();
    frame.setSize(650, 500);
    frame.setTitle("ListDemo");
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public ListDemo() {
    // Create a panel to hold nine labels
    JPanel p = new JPanel(new GridLayout(3, 3, 5, 5));

    for (int i = 0; i < NUMBER_OF_FLAGS; i++) {
      p.add(jlblImageViewer[i] = new JLabel());
      jlblImageViewer[i].setHorizontalAlignment
        (SwingConstants.CENTER);
    }

    // Add p and the list to the frame
    add(p, BorderLayout.CENTER);
    add(new JScrollPane(jlst), BorderLayout.WEST);

    // Register listeners
    jlst.addListSelectionListener(new ListSelectionListener() {
      /** Handle list selection */
      public void valueChanged(ListSelectionEvent e) {
        // Get selected indices
        int[] indices = jlst.getSelectedIndices();

        int i;
        // Set icons in the labels
        for (i = 0; i < indices.length; i++) {
          jlblImageViewer[i].setIcon(imageIcons[indices[i]]);
        }

        // Remove icons from the rest of the labels
        for (; i < NUMBER_OF_FLAGS; i++) {
          jlblImageViewer[i].setIcon(null);
        }
      }
    });
  }
}
