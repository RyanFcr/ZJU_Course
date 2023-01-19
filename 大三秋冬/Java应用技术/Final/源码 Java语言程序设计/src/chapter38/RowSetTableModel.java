package chapter38;

import java.sql.*;
import javax.sql.*;
import javax.swing.table.AbstractTableModel;

public class RowSetTableModel extends AbstractTableModel
    implements RowSetListener {
  // RowSet for the result set
  private RowSet rowSet;

  /** Return the rowset */
  public RowSet getRowSet() {
    return rowSet;
  }

  /** Set a new rowset */
  public void setRowSet(RowSet rowSet) {
    if (rowSet != null) {
      this.rowSet = rowSet;
      rowSet.addRowSetListener(this);
      fireTableStructureChanged();
    }
  }

  /** Return the number of rows in the row set */
  public int getRowCount() {
    try {
      rowSet.last();
      return rowSet.getRow(); // Get the current row number
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    return 0;
  }

  /** Return the number of columns in the row set */
  public int getColumnCount() {
    try {
      if (rowSet != null) {
        return rowSet.getMetaData().getColumnCount();
      }
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }

    return 0;
  }

  /** Return value at the specified row and column */
  public Object getValueAt(int row, int column) {
    try {
      rowSet.absolute(row + 1);
      return rowSet.getObject(column + 1);
    }
    catch (SQLException sqlex) {
      sqlex.printStackTrace();
    }

    return null;
  }

  /** Return the column name at a specified column */
  public String getColumnName(int column) {
    try {
      return rowSet.getMetaData().getColumnLabel(column + 1);
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }

    return "";
  }

  /** Implement rowSetChanged */
  public void rowSetChanged(RowSetEvent e) {
    System.out.println("RowSet changed");
    fireTableStructureChanged();
  }

  /** Implement rowChanged */
  public void rowChanged(RowSetEvent e) {
    System.out.println("Row changed");
    fireTableDataChanged();
  }

  /** Implement cursorMoved */
  public void cursorMoved(RowSetEvent e) {
    System.out.println("Cursor moved");
  }
}
