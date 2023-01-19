package chapter35;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ListPropertiesDemo extends JApplet {
  private JList jlst = new JList(new String[] {"Item1",
    "Item2", "Item3", "Item4", "Item5", "Item6"});
  private JSpinner jspVisibleRowCount =
    new JSpinner(new SpinnerNumberModel(8, -1, 20, 1));
  private JComboBox jcboLayoutOrientation =
    new JComboBox(new String[] {"VERTICAL (0)",
    "VERTICAL_WRAP (1)", "HORIZONTAL_WRAP (2)"});
  private JComboBox jcboSelectionMode =
    new JComboBox(new String[] {"SINGLE_SELECTION (0)",
    "SINGLE_INTERVAL_SELECTION (1)",
    "MULTIPLE_INTERVAL_SELECTION (2)"});
  private JLabel jlblStatus = new JLabel();

  /** Construct the applet */
  public ListPropertiesDemo() {
    // Place labels in a panel
    JPanel panel1 = new JPanel();
    panel1.setLayout(new GridLayout(3, 1));
    panel1.add(new JLabel("visibleRowCount"));
    panel1.add(new JLabel("layoutOrientation"));
    panel1.add(new JLabel("selectionMode"));

    // Place text fields in a panel
    JPanel panel2 = new JPanel();
    panel2.setLayout(new GridLayout(3, 1));
    panel2.add(jspVisibleRowCount);
    panel2.add(jcboLayoutOrientation);
    panel2.add(jcboSelectionMode);

    // Place panel1 and panel2
    JPanel panel3 = new JPanel();
    panel3.setLayout(new BorderLayout(5, 5));
    panel3.add(panel1, BorderLayout.WEST);
    panel3.add(panel2, BorderLayout.CENTER);

    // Place elements in the applet
    add(panel3, BorderLayout.NORTH);
    add(new JScrollPane(jlst), BorderLayout.CENTER);
    add(jlblStatus, BorderLayout.SOUTH);

    // Set initial property values
    jlst.setFixedCellWidth(50);
    jlst.setFixedCellHeight(20);
    jlst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    // Register listeners
    jspVisibleRowCount.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        jlst.setVisibleRowCount(
          ((Integer)jspVisibleRowCount.getValue()).intValue());
      }
    });

    jcboLayoutOrientation.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jlst.setLayoutOrientation(
          jcboLayoutOrientation.getSelectedIndex());
      }
    });

    jcboSelectionMode.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jlst.setSelectionMode(
          jcboSelectionMode.getSelectedIndex());
      }
    });

    jlst.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        Object[] values = jlst.getSelectedValues();
        String display = "";

        for (int i = 0; i < values.length; i++) {
          display += (String)values[i] + " ";
        }

        jlblStatus.setText(display);
      }
    });
  }

  public static void main(String[] args) {
    ListPropertiesDemo applet = new ListPropertiesDemo();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("ListPropertiesDemo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400, 320);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
