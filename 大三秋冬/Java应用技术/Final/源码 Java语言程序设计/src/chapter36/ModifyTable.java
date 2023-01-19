package chapter36;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.util.Vector;

public class ModifyTable extends JApplet {
  // Create table column names
  private String[] columnNames =
    {"Country", "Capital", "Population in Millions", "Democracy"};

  // Create table data
  private Object[][] rowData = {
    {"USA", "Washington DC", 280, true},
    {"Canada", "Ottawa", 32, true},
    {"United Kingdom", "London", 60, true},
    {"Germany", "Berlin", 83, true},
    {"France", "Paris", 60, true},
    {"Norway", "Oslo", 4.5, true},
    {"India", "New Delhi", 1046, true}
  };

  // Create a table model
  private DefaultTableModel tableModel = new DefaultTableModel(
    rowData, columnNames);

  // Create a table
  private JTable jTable1 = new JTable(tableModel);

  // Create buttons
  private JButton jbtAddRow = new JButton("Add New Row");
  private JButton jbtAddColumn = new JButton("Add New Column");
  private JButton jbtDeleteRow = new JButton("Delete Selected Row");
  private JButton jbtDeleteColumn = new JButton(
    "Delete Selected Column");
  private JButton jbtSave = new JButton("Save");
  private JButton jbtClear = new JButton("Clear");
  private JButton jbtRestore = new JButton("Restore");

  // Create a combo box for selection modes
  private JComboBox jcboSelectionMode =
    new JComboBox(new String[] {"SINGLE_SELECTION",
      "SINGLE_INTERVAL_SELECTION", "MULTIPLE_INTERVAL_SELECTION"});

  // Create check boxes
  private JCheckBox jchkRowSelectionAllowed =
    new JCheckBox("RowSelectionAllowed", true);
  private JCheckBox jchkColumnSelectionAllowed =
    new JCheckBox("ColumnSelectionAllowed", false);

  public ModifyTable() {
    JPanel panel1 = new JPanel();
    panel1.setLayout(new GridLayout(2, 2));
    panel1.add(jbtAddRow);
    panel1.add(jbtAddColumn);
    panel1.add(jbtDeleteRow);
    panel1.add(jbtDeleteColumn);

    JPanel panel2 = new JPanel();
    panel2.add(jbtSave);
    panel2.add(jbtClear);
    panel2.add(jbtRestore);

    JPanel panel3 = new JPanel();
    panel3.setLayout(new BorderLayout(5, 0));
    panel3.add(new JLabel("Selection Mode"), BorderLayout.WEST);
    panel3.add(jcboSelectionMode, BorderLayout.CENTER);

    JPanel panel4 = new JPanel();
    panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
    panel4.add(jchkRowSelectionAllowed);
    panel4.add(jchkColumnSelectionAllowed);

    JPanel panel5 = new JPanel();
    panel5.setLayout(new GridLayout(2, 1));
    panel5.add(panel3);
    panel5.add(panel4);

    JPanel panel6 = new JPanel();
    panel6.setLayout(new BorderLayout());
    panel6.add(panel1, BorderLayout.SOUTH);
    panel6.add(panel2, BorderLayout.CENTER);

    add(panel5, BorderLayout.NORTH);
    add(new JScrollPane(jTable1),
      BorderLayout.CENTER);
    add(panel6, BorderLayout.SOUTH);

    // Initialize table selection mode
    jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    jbtAddRow.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (jTable1.getSelectedRow() >= 0)
          tableModel.insertRow(jTable1.getSelectedRow(),
            new java.util.Vector());
        else
          tableModel.addRow(new java.util.Vector());
      }
    });

    jbtAddColumn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String name = JOptionPane.showInputDialog("New Column Name");
        tableModel.addColumn(name, new java.util.Vector());
      }
    });

    jbtDeleteRow.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (jTable1.getSelectedRow() >= 0)
          tableModel.removeRow(jTable1.getSelectedRow());
      }
    });

    jbtDeleteColumn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (jTable1.getSelectedColumn() >= 0) {
          TableColumnModel columnModel = jTable1.getColumnModel();
          TableColumn tableColumn =
              columnModel.getColumn(jTable1.getSelectedColumn());
          columnModel.removeColumn(tableColumn);
        }
      }
    });

    jbtSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream("tablemodel.dat"));
          out.writeObject(tableModel.getDataVector());
          out.writeObject(getColumnNames());
          out.close();
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });

    jbtClear.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        tableModel.setRowCount(0);
      }
    });

    jbtRestore.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          ObjectInputStream in = new ObjectInputStream(
            new FileInputStream("tablemodel.dat"));
          Vector rowData = (Vector)in.readObject();
          Vector columnNames = (Vector)in.readObject();
          tableModel.setDataVector(rowData, columnNames);
          in.close();
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });

    jchkRowSelectionAllowed.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jTable1.setRowSelectionAllowed(
          jchkRowSelectionAllowed.isSelected());
      }
    });

    jchkColumnSelectionAllowed.addActionListener(
      new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jTable1.setColumnSelectionAllowed(
          jchkColumnSelectionAllowed.isSelected());
      }
    });

    jcboSelectionMode.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String selectedItem =
          (String) jcboSelectionMode.getSelectedItem();

        if (selectedItem.equals("SINGLE_SELECTION"))
          jTable1.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION);
        else if (selectedItem.equals("SINGLE_INTERVAL_SELECTION"))
          jTable1.setSelectionMode(
            ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        else if (selectedItem.equals("MULTIPLE_INTERVAL_SELECTION"))
          jTable1.setSelectionMode(
            ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      }
    });
  }

  private Vector getColumnNames() {
    Vector<String> columnNames = new Vector<String>();

    for (int i = 0; i < jTable1.getColumnCount(); i++)
      columnNames.add(jTable1.getColumnName(i));

    return columnNames;
  }

  //Main method
  public static void main(String[] args) {
    ModifyTable applet = new ModifyTable();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("ModifyTable");
    frame.getContentPane().add(applet, java.awt.BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
