package chapter30;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;
import javax.swing.event.*;
import java.io.*;

public class WebBrowser extends JApplet {
  // JEditor pane to view HTML files
  private JEditorPane jep = new JEditorPane();

  // Label for URL
  private JLabel jlblURL = new JLabel("URL");

  // Text field for entering URL
  private JTextField jtfURL = new JTextField();

  /** Initialize the applet */
  public void init() {
    // Create a panel jpURL to hold the label and text field
    JPanel jpURL = new JPanel();
    jpURL.setLayout(new BorderLayout());
    jpURL.add(jlblURL, BorderLayout.WEST);
    jpURL.add(jtfURL, BorderLayout.CENTER);

    // Place jpURL and jspViewer in the applet
    add(new JScrollPane(jep), BorderLayout.CENTER);
    add(jpURL, BorderLayout.NORTH);

    // Set jep noneditable
    jep.setEditable(false);

    // Register listener
    jep.addHyperlinkListener(new HyperlinkListener() {
      public void hyperlinkUpdate(HyperlinkEvent e) {
        try {
          jep.setPage(e.getURL());
        }
        catch (IOException ex) {
          System.out.println(ex);
        }
      }
    });
    jtfURL.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          // Get the URL from text field
          URL url = new URL(jtfURL.getText().trim());

          // Display the HTML file
          jep.setPage(url);
        }
        catch (IOException ex) {
          System.out.println(ex);
        }
      }
    });
  }

  /** Main method */
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("Web Browser");

    // Create an instance of ViewRemoteFile
    WebBrowser applet = new WebBrowser();

    // Add the applet instance to the frame
    frame.getContentPane().add(applet, BorderLayout.CENTER);

    // Invoke init() and start()
    applet.init();
    applet.start();

    // Display the frame
    frame.setSize(300, 300);
    frame.setVisible(true);
  }
}
