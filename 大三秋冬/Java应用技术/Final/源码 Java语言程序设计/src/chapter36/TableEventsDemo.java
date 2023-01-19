package chapter36;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;

public class TableEventsDemo extends JApplet {
  // Create table column names
  private String[] columnNames =
    {"Title", "Copies Needed", "Publisher", "Date Published",
     "In-stock", "Book Photo"};

  // Create image icons
  private ImageIcon intro1eImageIcon =
    new ImageIcon(getClass().getResource("/image/intro1e.gif"));
  private ImageIcon intro2eImageIcon =
    new ImageIcon(getClass().getResource("/image/intro2e.gif"));
  private ImageIcon intro3eImageIcon =
    new ImageIcon(getClass().getResource("/image/intro3e.jpg"));

  // Create table data
  private Object[][] rowData = {
    {"Introduction to Java Programming", 120,
     "Que Education & Training",
      new GregorianCalendar(1998, 1-1, 6).getTime(),
      false, intro1eImageIcon},
    {"Introduction to Java Programming, 2E", 220,
     "Que Education & Training",
      new GregorianCalendar(1999, 1-1, 6).getTime(),
      false, intro2eImageIcon},
    {"Introduction to Java Programming, 3E", 220,
      "Prentice Hall",
      new GregorianCalendar(2000, 12-1, 0).getTime(),
      true, intro3eImageIcon},
  };

  // Create a table model
  private MyTableModel tableModel = new MyTableModel(
    rowData, columnNames);

  // Create a table
  private JTable jTable1 = new JTable(tableModel);

  // Get table column model
  private TableColumnModel tableColumnModel =
    jTable1.getColumnModel();

  // Get table selection model
  private ListSelectionModel selectionModel =
    jTable1.getSelectionModel();

  // Create a text area
  private JTextArea jtaMessage = new JTextArea();

  // Create a button
  private JButton jbtDeleteColumn =
    new JButton("Delete Selected Column");

  public TableEventsDemo() {
    // Set custom renderer for displaying images
    TableColumn bookCover = jTable1.getColumn("Book Photo");
    bookCover.setCellRenderer(new MyImageCellRenderer());

    // Create a combo box for publishers
    JComboBox jcboPublishers = new JComboBox();
    jcboPublishers.addItem("Prentice Hall");
    jcboPublishers.addItem("Que Education & Training");
    jcboPublishers.addItem("McGraw-Hill");

    // Set combo box as the editor for the publisher column
    TableColumn publisherColumn = jTable1.getColumn("Publisher");
    publisherColumn.setCellEditor(
      new DefaultCellEditor(jcboPublishers));

    jTable1.setRowHeight(60);
    jTable1.setColumnSelectionAllowed(true);

    JSplitPane jSplitPane1 = new JSplitPane(
      JSplitPane.VERTICAL_SPLIT);
    jSplitPane1.add(new JScrollPane(jTable1), JSplitPane.LEFT);
    jSplitPane1.add(new JScrollPane(jtaMessage), JSplitPane.RIGHT);
    add(jbtDeleteColumn, BorderLayout.NORTH);
    add(jSplitPane1, BorderLayout.CENTER);

    tableModel.addTableModelListener(new TableModelListener() {
      public void tableChanged(TableModelEvent e) {
        jtaMessage.append("Table changed at row " +
          e.getFirstRow() + " and column " + e.getColumn() + "\n");
      }
    });

    tableColumnModel.addColumnModelListener(
      new TableColumnModelListener() {
      public void columnRemoved(TableColumnModelEvent e) {
        jtaMessage.append("Column indexed at " + e.getFromIndex() +
          " is deleted \n");
      }
      public void columnAdded(TableColumnModelEvent e) {
      }
      public void columnMoved(TableColumnModelEvent e) {
      }
      public void columnMarginChanged(ChangeEvent e) {
      }
      public void columnSelectionChanged(ListSelectionEvent e) {
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

    selectionModel.addListSelectionListener(
      new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        jtaMessage.append("Row " + jTable1.getSelectedRow() +
          " and column " + jTable1.getSelectedColumn() +
          " selected\n");
      }
    });
  }

  public static void main(String[] args) {
    TableEventsDemo applet = new TableEventsDemo();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("TableEventsDemo");
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
