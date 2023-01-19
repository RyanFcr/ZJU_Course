package chapter38;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.sql.*;
import com.sun.rowset.CachedRowSetImpl;

public class TableEditor extends JPanel {
  private JButton jbtFirst = new JButton("First");
  private JButton jbtNext = new JButton("Next");
  private JButton jbtPrior = new JButton("Prior");
  private JButton jbtLast = new JButton("Last");
  private JButton jbtDelete = new JButton("Delete");
  private JButton jbtCommit = new JButton("Commit");
  private JLabel jlblStatus = new JLabel();

  // Table model, table selection model, table, rowset
  private RowSetTableModel tableModel = new RowSetTableModel();
  private DefaultListSelectionModel listSelectionModel =
    new DefaultListSelectionModel();
  private JTable jTable1 = new JTable();
  private RowSet rowSet;

  /** Set a new row set */
  public void setRowSet(RowSet rowSet) {
    this.rowSet = rowSet;
    tableModel.setRowSet(rowSet);
    jTable1.setModel(tableModel);

    // Enable auto sort on columns
    TableRowSorter<TableModel> sorter =
      new TableRowSorter<TableModel>(tableModel);
    jTable1.setRowSorter(sorter);
  }

  /** Create a TableEditor */
  public TableEditor() {
    JPanel jPanel1 = new JPanel();
    jPanel1.add(jbtFirst);
    jPanel1.add(jbtNext);
    jPanel1.add(jbtPrior);
    jPanel1.add(jbtLast);
    jPanel1.add(jbtDelete);
    jPanel1.add(jbtCommit);

    setLayout(new BorderLayout());
    add(jPanel1, BorderLayout.NORTH);
    add(new JScrollPane(jTable1), BorderLayout.CENTER);
    add(jlblStatus, BorderLayout.SOUTH);

    // Set selection model for the table
    jTable1.setSelectionModel(listSelectionModel);

    // Register listeners
    jbtFirst.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        moveCursor("first");
      }
    });
    jbtNext.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        moveCursor("next");
      }
    });
    jbtPrior.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        moveCursor("previous");
      }
    });
    jbtLast.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        moveCursor("last");
      }
    });
    jbtDelete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        delete();
      }
    });
    jbtCommit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        try {
          ((CachedRowSetImpl)rowSet).acceptChanges();
        }
        catch (java.sql.SQLException ex) {
          ex.printStackTrace();
        }
      }
    });
    listSelectionModel.addListSelectionListener(
      new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        handleSelectionValueChanged(e);
      }
    });
  }

  /* Delete a row */
  private void delete() {
    try {
      // Delete the record from the database
      int currentRow = rowSet.getRow();
      rowSet.deleteRow();
      if (rowSet.isAfterLast())
        rowSet.last();
      else if (rowSet.getRow() >= currentRow)
        rowSet.absolute(currentRow);
      setTableCursor();
    }
    catch (java.sql.SQLException ex) {
      jlblStatus.setText(ex.toString());
    }
  }

  /** Set cursor in the table and set the row number in the status */
  private void setTableCursor() throws java.sql.SQLException {
    int row = rowSet.getRow();
    listSelectionModel.setSelectionInterval(row - 1, row - 1);
    jlblStatus.setText("Current row number: " + row);
  }

  /** Move cursor to the specified location */
  private void moveCursor(String whereToMove) {
    try {
      if (whereToMove.equals("first"))
        rowSet.first();
      else if (whereToMove.equals("next") && !rowSet.isLast())
        rowSet.next();
      else if (whereToMove.equals("previous") && !rowSet.isFirst())
        rowSet.previous();
      else if (whereToMove.equals("last"))
        rowSet.last();
      setTableCursor();
    }
    catch (java.sql.SQLException ex) {
      jlblStatus.setText(ex.toString());
    }
  }

  /** Handle the selection in the table */
  private void handleSelectionValueChanged(ListSelectionEvent e) {
    int selectedRow = jTable1.getSelectedRow();

    try {
      if (selectedRow != -1) {
        rowSet.absolute(selectedRow + 1);
        setTableCursor();
      }
    }
    catch (java.sql.SQLException ex) {
      jlblStatus.setText(ex.toString());
    }
  }
}
