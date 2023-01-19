package chapter38;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sql.RowSet;
import com.sun.rowset.*;

public class TestTableEditor extends JApplet {
  private JComboBox jcboDriver = new JComboBox(new String[] {
    "sun.jdbc.odbc.JdbcOdbcDriver",
    "com.mysql.jdbc.Driver",
    "oracle.jdbc.driver.OracleDriver"
  });
  private JComboBox jcboURL = new JComboBox(new String[] {
    "jdbc:odbc:exampleMDBDataSource",
    "jdbc:mysql://localhost/javabook",
    "jdbc:oracle:thin:@liang.armstrong.edu:1521:orcl"
  });

  private JButton jbtConnect =
    new JButton("Connect to DB & Get Table");
  private JTextField jtfUserName = new JTextField();
  private JPasswordField jpfPassword = new JPasswordField();
  private JTextField jtfTableName = new JTextField();
  private TableEditor tableEditor1 = new TableEditor();
  private JLabel jlblStatus = new JLabel();

  /** Creates new form TestTableEditor */
  public TestTableEditor() {
    JPanel jPanel1 = new JPanel(new GridLayout(5, 0));
    jPanel1.add(jcboDriver);
    jPanel1.add(jcboURL);
    jPanel1.add(jtfUserName);
    jPanel1.add(jpfPassword);
    jPanel1.add(jtfTableName);

    JPanel jPanel2 = new JPanel(new GridLayout(5, 0));
    jPanel2.add(new JLabel("JDBC Driver"));
    jPanel2.add(new JLabel("Database URL"));
    jPanel2.add(new JLabel("Username"));
    jPanel2.add(new JLabel("Password"));
    jPanel2.add(new JLabel("Table Name"));

    JPanel jPanel3 = new JPanel(new BorderLayout());
    jPanel3.add(jbtConnect, BorderLayout.SOUTH);
    jPanel3.add(jPanel2, BorderLayout.WEST);
    jPanel3.add(jPanel1, BorderLayout.CENTER);
    tableEditor1.setPreferredSize(new Dimension(400, 200));

    jcboURL.setEditable(true);
    jcboDriver.setEditable(true);

    add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
      jPanel3, tableEditor1), BorderLayout.CENTER);
    add(jlblStatus, BorderLayout.SOUTH);

    jbtConnect.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        try {
          // Connect to the database and create a rowset
          Class.forName(((String)jcboDriver.getSelectedItem()).trim());
          CachedRowSetImpl rowSet = new CachedRowSetImpl(); 
          rowSet.setUrl(((String)jcboURL.getSelectedItem()).trim());
          rowSet.setUsername(jtfUserName.getText().trim());
          rowSet.setPassword(new String(jpfPassword.getPassword()));
          rowSet.setCommand("select * from " +
            jtfTableName.getText().trim());
          rowSet.execute(); 
          rowSet.beforeFirst();
          tableEditor1.setRowSet(rowSet);
        }
        catch (Exception ex) {
          jlblStatus.setText(ex.toString());
        }
      }
    });
  }

  /** Main method */
  public static void main(String[] args) {
    TestTableEditor applet = new TestTableEditor();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(3);
    frame.setTitle("TestTableEditor");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
