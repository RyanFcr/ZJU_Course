package chapter30;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class MultiThreadServer extends JFrame {
  // Text area for displaying contents
  private JTextArea jta = new JTextArea();

  public static void main(String[] args) {
    new MultiThreadServer();
  }

  public MultiThreadServer() {
    // Place text area on the frame
    setLayout(new BorderLayout());
    add(new JScrollPane(jta), BorderLayout.CENTER);

    setTitle("MultiThreadServer");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true); // It is necessary to show the frame here!

    try {
      // Create a server socket
      ServerSocket serverSocket = new ServerSocket(8000);
      jta.append("MultiThreadServer started at " + new Date() + '\n');

      // Number a client
      int clientNo = 1;

      while (true) {
        // Listen for a new connection request
        Socket socket = serverSocket.accept();

        // Display the client number
        jta.append("Starting thread for client " + clientNo +
          " at " + new Date() + '\n');

        // Find the client's host name, and IP address
        InetAddress inetAddress = socket.getInetAddress();
        jta.append("Client " + clientNo + "'s host name is "
          + inetAddress.getHostName() + "\n");
        jta.append("Client " + clientNo + "'s IP Address is "
          + inetAddress.getHostAddress() + "\n");

        // Create a new thread for the connection
        HandleAClient task = new HandleAClient(socket);

        // Start the new thread
        new Thread(task).start();

        // Increment clientNo
        clientNo++;
      }
    }
    catch(IOException ex) {
      System.err.println(ex);
    }
  }

  // Inner class
  // Define the thread class for handling new connection
  class HandleAClient implements Runnable {
    private Socket socket; // A connected socket

    /** Construct a thread */
    public HandleAClient(Socket socket) {
      this.socket = socket;
    }

    /** Run a thread */
    public void run() {
      try {
        // Create data input and output streams
        DataInputStream inputFromClient = new DataInputStream(
          socket.getInputStream());
        DataOutputStream outputToClient = new DataOutputStream(
          socket.getOutputStream());

        // Continuously serve the client
        while (true) {
          // Receive radius from the client
          double radius = inputFromClient.readDouble();

          // Compute area
          double area = radius * radius * Math.PI;

          // Send area back to the client
          outputToClient.writeDouble(area);

          jta.append("radius received from client: " +
            radius + '\n');
          jta.append("Area found: " + area + '\n');
        }
      }
      catch(IOException e) {
        System.err.println(e);
      }
    }
  }
}
