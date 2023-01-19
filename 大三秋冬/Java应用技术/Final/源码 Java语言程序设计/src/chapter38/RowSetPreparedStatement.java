package chapter38;

import java.sql.*;
import javax.sql.RowSet;
import com.sun.rowset.*;

public class RowSetPreparedStatement {
  public static void main(String[] args)
      throws SQLException, ClassNotFoundException {
    // Load the JDBC driver
    Class.forName("com.mysql.jdbc.Driver");
    System.out.println("Driver loaded");

    // Create a row set
    RowSet rowSet = new JdbcRowSetImpl();

    // Set RowSet properties
    rowSet.setUrl("jdbc:mysql://localhost/javabook");
    rowSet.setUsername("scott");
    rowSet.setPassword("tiger");
    rowSet.setCommand("select * from Student where lastName = ? " +
      "and mi = ?");
    rowSet.setString(1, "Smith");
    rowSet.setString(2, "R");
    rowSet.execute();

    ResultSetMetaData rsMetaData = rowSet.getMetaData();
    for (int i = 1; i <= rsMetaData.getColumnCount(); i++)
      System.out.printf("%-12s\t", rsMetaData.getColumnName(i));
    System.out.println();

    // Iterate through the result and print the student names
    while (rowSet.next()) {
      for (int i = 1; i <= rsMetaData.getColumnCount(); i++)
        System.out.printf("%-12s\t", rowSet.getObject(i));
      System.out.println();
    }

    // Close the connection
    rowSet.close();
  }
}
