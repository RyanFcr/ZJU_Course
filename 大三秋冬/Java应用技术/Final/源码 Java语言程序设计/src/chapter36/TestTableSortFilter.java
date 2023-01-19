package chapter36;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.BorderLayout;

public class TestTableSortFilter extends JApplet {
  // Create table column names
  private String[] columnNames =
    {"Country", "Capital", "Population in Millions", "Democracy"};

  // Create table data
  private Object[][] data = {
    {"USA", "Washington DC", 280, true},
    {"Canada", "Ottawa", 32, true},
    {"United Kingdom", "London", 60, true},
    {"Germany", "Berlin", 83, true},
    {"France", "Paris", 60, true},
    {"Norway", "Oslo", 4.5, true},
    {"India", "New Delhi", 1046, true}
  };

  // Create a table
  private JTable jTable1 = new JTable(data, columnNames);

  // Create a TableRowSorter
  private TableRowSorter<TableModel> sorter =
      new TableRowSorter<TableModel>(jTable1.getModel());

  private JTextField jtfFilter = new JTextField();
  private JButton btFilter = new JButton("Filter");

  public TestTableSortFilter() {
    // Enable auto sorter
    jTable1.setRowSorter(sorter);

    JPanel panel = new JPanel(new java.awt.BorderLayout());
    panel.add(new JLabel("Specify a word to match:"),
      BorderLayout.WEST);
    panel.add(jtfFilter, BorderLayout.CENTER);
    panel.add(btFilter, BorderLayout.EAST);

    add(panel, BorderLayout.SOUTH);
    add(new JScrollPane(jTable1), BorderLayout.CENTER);

    btFilter.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent e) {
        String text = jtfFilter.getText();
        if (text.trim().length() == 0)
          sorter.setRowFilter(null);
        else
          sorter.setRowFilter(RowFilter.regexFilter(text));
      }
    });
  }

  //Main method
  public static void main(String[] args) {
    TestTableSortFilter applet = new TestTableSortFilter();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("TestTableSortFilterSortFilter");
    frame.getContentPane().add(applet, java.awt.BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
