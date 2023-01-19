package chapter36;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class TableCellRendererEditorDemo extends JApplet {
  // Create table column names
  private String[] columnNames =
    {"Title", "Copies Needed", "Publisher", "Date Published",
     "In-stock", "Book Photo"};

  // Create image icons
  private ImageIcon intro1eImageIcon = new ImageIcon(
    getClass().getResource("/image/intro1e.gif"));
  private ImageIcon intro2eImageIcon = new ImageIcon(
    getClass().getResource("/image/intro2e.gif"));
  private ImageIcon intro3eImageIcon = new ImageIcon(
    getClass().getResource("/image/intro3e.jpg"));

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

  public TableCellRendererEditorDemo() {
    jTable1.setRowHeight(60);
    add(new JScrollPane(jTable1),
      BorderLayout.CENTER);
  }

  public static void main(String[] args) {
    TableCellRendererEditorDemo applet =
      new TableCellRendererEditorDemo();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Table Cell Renderer and Editor Demo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.pack();
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2,
                      (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }
}
