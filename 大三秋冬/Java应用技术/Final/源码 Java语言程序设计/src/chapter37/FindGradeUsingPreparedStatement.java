package chapter37;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class FindGradeUsingPreparedStatement extends JApplet {
  private JTextField jtfSSN = new JTextField(9);
  private JTextField jtfCourseId = new JTextField(5);
  private JButton jbtShowGrade = new JButton("Show Grade");

  // PreparedStatement for executing queries
  private PreparedStatement preparedStatement;

  /** Initialize the applet */
  public void init() {
    // Initialize database connection and create a Statement object
    initializeDB();

    jbtShowGrade.addActionListener(
      new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jbtShowGrade_actionPerformed(e);
      }
    });

    JPanel jPanel1 = new JPanel();
    jPanel1.add(new JLabel("SSN"));
    jPanel1.add(jtfSSN);
    jPanel1.add(new JLabel("Course ID"));
    jPanel1.add(jtfCourseId);
    jPanel1.add(jbtShowGrade);

    add(jPanel1, BorderLayout.NORTH);
  }

  private void initializeDB() {
    try {
      // Load the JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
//      Class.forName("oracle.jdbc.driver.OracleDriver");
      System.out.println("Driver loaded");

      // Establish a connection
      Connection connection = DriverManager.getConnection
        ("jdbc:mysql://localhost/javabook", "scott", "tiger");
//    ("jdbc:oracle:thin:@liang.armstrong.edu:1521:orcl",
//     "scott", "tiger");
      System.out.println("Database connected");

      String queryString = "select firstName, mi, " +
        "lastName, title, grade from Student, Enrollment, Course " +
        "where Student.ssn = ? and Enrollment.courseId = ? " +
        "and Enrollment.courseId = Course.courseId";

      // Create a statement
      preparedStatement = connection.prepareStatement(queryString);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbtShowGrade_actionPerformed(ActionEvent e) {
    String ssn = jtfSSN.getText();
    String courseId = jtfCourseId.getText();
    try {
      preparedStatement.setString(1, ssn);
      preparedStatement.setString(2, courseId);
      ResultSet rset = preparedStatement.executeQuery();

      if (rset.next()) {
        String lastName = rset.getString(1);
        String mi = rset.getString(2);
        String firstName = rset.getString(3);
        String title = rset.getString(4);
        String grade = rset.getString(5);

        // Display result in a dialog box
        JOptionPane.showMessageDialog(null, firstName + " " + mi +
          " " + lastName + "'s grade on course " + title + " is " +
          grade);
      }
      else {
        // Display result in a dialog box
        JOptionPane.showMessageDialog(null, "Not found");
      }
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  /** Main method */
  public static void main(String[] args) {
    FindGradeUsingPreparedStatement applet = new
      FindGradeUsingPreparedStatement();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Find Grades");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(380, 80);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
