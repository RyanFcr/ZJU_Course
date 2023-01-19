package chapter36;

import javax.swing.*;
import javax.swing.table.*;

public class TestTableColumnModel extends JApplet {
  // Create table column names
  private String[] columnNames =
    {"Country", "Capital", "Population in Millions", "Democracy"};

  // Create table data
  private Object[][] data = {
    {"USA", "Washington DC", 280, true},
    {"Canada", "Ottawa", 32, true}
  };

  // Create a table
  private JTable jTable1 = new JTable(data, columnNames);

  public TestTableColumnModel() {
    add(new JScrollPane(jTable1));

    TableColumnModel columnModel = jTable1.getColumnModel();
    columnModel.moveColumn(0, 1);
    columnModel.removeColumn(columnModel.getColumn(3));
  }

  //Main method
  public static void main(String[] args) {
    TestTableColumnModel applet = new TestTableColumnModel();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("TestTableColumnModel");
    frame.getContentPane().add(applet, java.awt.BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
