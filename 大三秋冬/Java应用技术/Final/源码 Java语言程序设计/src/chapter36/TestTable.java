package chapter36;

import javax.swing.*;

public class TestTable extends JApplet {
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

  public TestTable() {
    add(new JScrollPane(jTable1));
  }

  //Main method
  public static void main(String[] args) {
    TestTable applet = new TestTable();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("TestTable");
    frame.getContentPane().add(applet, java.awt.BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
