package chapter36;

import javax.swing.*;
import javax.swing.table.*;

public class TestTableModel extends JApplet {
  // Create table column names
  private String[] columnNames =
    {"Country", "Capital", "Population in Millions", "Democracy"};

  // Create table data
  private Object[][] data = {
    {"USA", "Washington DC", 280, true},
    {"Canada", "Ottawa", 32, true}
  };

  // Create a model
  private DefaultTableModel tableModel =
    new DefaultTableModel(data, columnNames);

  // Create a table
  private JTable jTable1 = new JTable(tableModel);

  public TestTableModel() {
    add(new JScrollPane(jTable1));

    tableModel.addRow(new Object[]{"France", "Paris", 60, true});
    tableModel.insertRow(0, new Object[]
      {"India", "New Delhi", 1046, true});
    tableModel.removeRow(1);
    tableModel.addColumn("Area");
    tableModel.setValueAt(10, 0, 4);
    tableModel.setValueAt(20, 1, 4);
    tableModel.setValueAt(30, 2, 4);
  }

  //Main method
  public static void main(String[] args) {
    TestTableModel applet = new TestTableModel();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("TestTableModel");
    frame.getContentPane().add(applet, java.awt.BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
