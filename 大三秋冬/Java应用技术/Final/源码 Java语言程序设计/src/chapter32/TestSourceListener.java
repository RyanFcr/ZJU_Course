package chapter32;

import javax.swing.*;
import java.awt.event.*;

public class TestSourceListener {
  public static void main(String[] args) {
    JFrame frame = new JFrame("TestSourceListener");
    // Create a source object
    JButton jbt = new JButton("OK");
    frame.add(jbt);
    frame.setSize(200, 200);
    frame.setVisible(true);

    // Create listeners
    MyListener listener = new MyListener();

    // Register listeners
    jbt.addActionListener(listener);
  }
}

/** MyListener class */
class MyListener implements ActionListener {
  public void actionPerformed(ActionEvent e) {
    System.out.println("I will process it!");
  }
}
