package chapter30;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class StudentClient extends JApplet {
  private JTextField jtfName = new JTextField(32);
  private JTextField jtfStreet = new JTextField(32);
  private JTextField jtfCity = new JTextField(20);
  private JTextField jtfState = new JTextField(2);
  private JTextField jtfZip = new JTextField(5);

  // Button for sending a student to the server
  private JButton jbtRegister = new JButton("Register to the Server");

  // Indicate if it runs as application
  private boolean isStandAlone = false;

  // Host name or ip
  String host = "localhost";

  public void init() {
    // Panel p1 for holding labels Name, Street, and City
    JPanel p1 = new JPanel();
    p1.setLayout(new GridLayout(3, 1));
    p1.add(new JLabel("Name"));
    p1.add(new JLabel("Street"));
    p1.add(new JLabel("City"));

    // Panel jpState for holding state
    JPanel jpState = new JPanel();
    jpState.setLayout(new BorderLayout());
    jpState.add(new JLabel("State"), BorderLayout.WEST);
    jpState.add(jtfState, BorderLayout.CENTER);

    // Panel jpZip for holding zip
    JPanel jpZip = new JPanel();
    jpZip.setLayout(new BorderLayout());
    jpZip.add(new JLabel("Zip"), BorderLayout.WEST);
    jpZip.add(jtfZip, BorderLayout.CENTER);

    // Panel p2 for holding jpState and jpZip
    JPanel p2 = new JPanel();
    p2.setLayout(new BorderLayout());
    p2.add(jpState, BorderLayout.WEST);
    p2.add(jpZip, BorderLayout.CENTER);

    // Panel p3 for holding jtfCity and p2
    JPanel p3 = new JPanel();
    p3.setLayout(new BorderLayout());
    p3.add(jtfCity, BorderLayout.CENTER);
    p3.add(p2, BorderLayout.EAST);

    // Panel p4 for holding jtfName, jtfStreet, and p3
    JPanel p4 = new JPanel();
    p4.setLayout(new GridLayout(3, 1));
    p4.add(jtfName);
    p4.add(jtfStreet);
    p4.add(p3);

    // Place p1 and p4 into StudentPanel
    JPanel studentPanel = new JPanel(new BorderLayout());
    studentPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
    studentPanel.add(p1, BorderLayout.WEST);
    studentPanel.add(p4, BorderLayout.CENTER);

    // Add the student panel and button to the applet
    add(studentPanel, BorderLayout.CENTER);
    add(jbtRegister, BorderLayout.SOUTH);

    // Register listener
    jbtRegister.addActionListener(new ButtonListener());

    // Find the IP address of the Web server
    if (!isStandAlone)
      host = getCodeBase().getHost();
  }

  /** Handle button action */
  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      try {
        // Establish connection with the server
        Socket socket = new Socket(host, 8000);

        // Create an output stream to the server
        ObjectOutputStream toServer =
          new ObjectOutputStream(socket.getOutputStream());

        // Get text field
        String name = jtfName.getText().trim();
        String street = jtfStreet.getText().trim();
        String city = jtfCity.getText().trim();
        String state = jtfState.getText().trim();
        String zip = jtfZip.getText().trim();

        // Create a Student object and send to the server
        StudentAddress s =
          new StudentAddress(name, street, city, state, zip);
        toServer.writeObject(s);
      }
      catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }

  /** Run the applet as an application */
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("Register Student Client");

    // Create an instance of the applet
    StudentClient applet = new StudentClient();
    applet.isStandAlone = true;

    // Get host
    if (args.length == 1) applet.host = args[0];

    // Add the applet instance to the frame
    frame.add(applet, BorderLayout.CENTER);

    // Invoke init() and start()
    applet.init();
    applet.start();

    // Display the frame
    frame.pack();
    frame.setVisible(true);
  }
}
