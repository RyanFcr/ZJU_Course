package chapter17;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RadioButtonDemo extends CheckBoxDemo {
  // Declare radio buttons
  private JRadioButton jrbRed, jrbGreen, jrbBlue;

  public static void main(String[] args) {
    RadioButtonDemo frame = new RadioButtonDemo();
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("RadioButtonDemo");
    frame.setSize(500, 200);
    frame.setVisible(true);
  }

  public RadioButtonDemo() {
    // Create a new panel to hold check boxes
    JPanel jpRadioButtons = new JPanel();
    jpRadioButtons.setLayout(new GridLayout(3, 1));
    jpRadioButtons.add(jrbRed = new JRadioButton("Red"));
    jpRadioButtons.add(jrbGreen = new JRadioButton("Green"));
    jpRadioButtons.add(jrbBlue = new JRadioButton("Blue"));
    add(jpRadioButtons, BorderLayout.WEST);

    // Create a radio button group to group three buttons
    ButtonGroup group = new ButtonGroup();
    group.add(jrbRed);
    group.add(jrbGreen);
    group.add(jrbBlue);

    // Set keyboard mnemonics
    jrbRed.setMnemonic('E');
    jrbGreen.setMnemonic('G');
    jrbBlue.setMnemonic('U');

    // Register listeners for check boxes
    jrbRed.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        messagePanel.setForeground(Color.red);
      }
    });
    jrbGreen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        messagePanel.setForeground(Color.green);
      }
    });
    jrbBlue.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        messagePanel.setForeground(Color.blue);
      }
    });

    // Set initial message color to blue
    jrbBlue.setSelected(true);
    messagePanel.setForeground(Color.blue);
  }
}
