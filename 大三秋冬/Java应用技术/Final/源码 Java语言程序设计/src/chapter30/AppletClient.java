package chapter30;

import java.io.*;
import java.net.*;
import javax.swing.*;

public class AppletClient extends JApplet {
  // Label for displaying the visit count
  private JLabel jlblCount = new JLabel();

  // Indicate if it runs as application
  private boolean isStandAlone = false;

  // Host name or ip
  private String host = "localhost";

  /** Initialize the applet */
  public void init() {
    add(jlblCount);

    try {
      // Create a socket to connect to the server
      Socket socket;
      if (isStandAlone)
        socket = new Socket(host, 8000);
      else
        socket = new Socket(getCodeBase().getHost(), 8000);

      // Create an input stream to receive data from the server
      DataInputStream inputFromServer =
        new DataInputStream(socket.getInputStream());

      // Receive the count from the server and display it on label
      int count = inputFromServer.readInt();
      jlblCount.setText("You are visitor number " + count);

      // Close the stream
      inputFromServer.close();
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /** Run the applet as an application */
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("Applet Client");

    // Create an instance of the applet
    AppletClient applet = new AppletClient();
    applet.isStandAlone = true;

    // Get host
    if (args.length == 1) applet.host = args[0];

    // Add the applet instance to the frame
    frame.add(applet, java.awt.BorderLayout.CENTER);

    // Invoke init() and start()
    applet.init();
    applet.start();

    // Display the frame
    frame.pack();
    frame.setVisible(true);
  }
}
