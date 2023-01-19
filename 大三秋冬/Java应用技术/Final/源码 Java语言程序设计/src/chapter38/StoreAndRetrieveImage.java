package chapter38;

import java.sql.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import chapter17.DescriptionPanel;

public class StoreAndRetrieveImage extends JApplet {
  // Connection to the database
  private Connection connection;

  // Statement for static SQL statements
  private Statement stmt;

  // Prepared statement
  private PreparedStatement pstmt = null;
  private DescriptionPanel descriptionPanel1
    = new DescriptionPanel();

  private JComboBox jcboCountry = new JComboBox();

  /** Creates new form StoreAndRetrieveImage */
  public StoreAndRetrieveImage() {
    try {
      connectDB(); // Connect to DB
      storeDataToTable(); //Store data to the table (including image)
      fillDataInComboBox(); // Fill in combo box
      retrieveFlagInfo((String)(jcboCountry.getSelectedItem()));
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    jcboCountry.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent evt) {
        retrieveFlagInfo((String)(evt.getItem()));
      }
    });

    add(jcboCountry, BorderLayout.NORTH);
    add(descriptionPanel1, BorderLayout.CENTER);
  }

  private void connectDB() throws Exception {
    // Load the driver
    Class.forName("com.mysql.jdbc.Driver");
    System.out.println("Driver loaded");

    // Establish connection
    connection = DriverManager.getConnection
      ("jdbc:mysql://localhost/javabook", "scott", "tiger");
    System.out.println("Database connected");

    // Create a statement for static SQL
    stmt = connection.createStatement();

    // Create a prepared statement to retrieve flag and description
    pstmt = connection.prepareStatement("select flag, description " +
      "from Country where name = ?");
  }

  private void storeDataToTable() {
    String[] countries = {"Canada", "UK", "USA", "Germany",
      "Indian", "China"};

    String[] imageFilenames = {"/image/ca.gif", "/image/uk.gif",
      "/image/us.gif", "/image/germany.gif", "/image/india.gif",
      "/image/china.gif"};

    String[] descriptions = {"A text to describe Canadian " +
      "flag is omitted", "British flag ...", "American flag ...",
      "German flag ...", "Indian flag ...", "Chinese flag ..."};

    try {
      // Create a prepared statement to insert records
      PreparedStatement pstmt = connection.prepareStatement(
        "insert into Country values(?, ?, ?)");

      // Store all predefined records
      for (int i = 0; i < countries.length; i++) {
        pstmt.setString(1, countries[i]);

        // Store image to the table cell
        java.net.URL url =
          this.getClass().getResource(imageFilenames[i]);
        InputStream inputImage = url.openStream();
        pstmt.setBinaryStream(2, inputImage,
          (int)(inputImage.available()));

        pstmt.setString(3, descriptions[i]);
        pstmt.executeUpdate();
      }

      System.out.println("Table Country populated");
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void fillDataInComboBox() throws Exception {
    ResultSet rs = stmt.executeQuery("select name from Country");
    while (rs.next()) {
      jcboCountry.addItem(rs.getString(1));
    }
  }

  private void retrieveFlagInfo(String name) {
    try {
      pstmt.setString(1, name);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        Blob blob = rs.getBlob(1);
        ImageIcon imageIcon = new ImageIcon(
          blob.getBytes(1, (int)blob.length()));
        descriptionPanel1.setImageIcon(imageIcon);
        descriptionPanel1.setName(name);
        String description = rs.getString(2);
        descriptionPanel1.setDescription(description);
      }
    }
    catch (Exception ex) {
      System.err.println(ex);
    }
  }

  public static void main(String[] args) {
    StoreAndRetrieveImage applet = new StoreAndRetrieveImage();
    JFrame frame = new JFrame();
    frame.getContentPane().add(applet);
    frame.setDefaultCloseOperation(3);
    frame.setTitle("StoreAndRetrieveImage");
    frame.setSize(400, 320);
    frame.setVisible(true);
  }
}
