package chapter16;

import javax.swing.*;
import java.awt.event.*;

public class FrameAsListenerDemo extends JFrame 
    implements ActionListener {
  // Create four buttons
  private JButton jbtNew = new JButton("New");
  private JButton jbtOpen = new JButton("Open");
  private JButton jbtSave = new JButton("Save");
  private JButton jbtPrint = new JButton("Print");

  public FrameAsListenerDemo() {
    // Create a panel to hold buttons
    JPanel panel = new JPanel();
    panel.add(jbtNew);
    panel.add(jbtOpen);
    panel.add(jbtSave);
    panel.add(jbtPrint);
    
    add(panel);

    // Register listener with buttons 
    jbtNew.addActionListener(this);
    jbtOpen.addActionListener(this);
    jbtSave.addActionListener(this);
    jbtPrint.addActionListener(this);
  }

  /** Implement actionPerformed */
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == jbtNew)
      System.out.println("Process New");
    else if (e.getSource() == jbtOpen)
      System.out.println("Process Open");
    else if (e.getSource() == jbtSave)
      System.out.println("Process Save");
    else if (e.getSource() == jbtPrint)
      System.out.println("Process Print");
  }

  /** Main method */
  public static void main(String[] args) {
    JFrame frame = new FrameAsListenerDemo();
    frame.setTitle("FrameAsListenerDemo");
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
