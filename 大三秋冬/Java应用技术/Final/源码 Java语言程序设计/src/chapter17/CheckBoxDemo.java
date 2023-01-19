package chapter17;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CheckBoxDemo extends ButtonDemo {
  // Create three check boxes to control the display of message
  private JCheckBox jchkCentered = new JCheckBox("Centered");
  private JCheckBox jchkBold = new JCheckBox("Bold");
  private JCheckBox jchkItalic = new JCheckBox("Italic");

  public static void main(String[] args) {
    CheckBoxDemo frame = new CheckBoxDemo();
    frame.setTitle("CheckBoxDemo");
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 200);
    frame.setVisible(true);
  }

  public CheckBoxDemo() {
    // Set mnemonic keys
    jchkCentered.setMnemonic('C');
    jchkBold.setMnemonic('B');
    jchkItalic.setMnemonic('I');

    // Create a new panel to hold check boxes
    JPanel jpCheckBoxes = new JPanel();
    jpCheckBoxes.setLayout(new GridLayout(3, 1));
    jpCheckBoxes.add(jchkCentered);
    jpCheckBoxes.add(jchkBold);
    jpCheckBoxes.add(jchkItalic);
    add(jpCheckBoxes, BorderLayout.EAST);

    // Register listeners with the check boxes
    jchkCentered.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
         messagePanel.setCentered(jchkCentered.isSelected());
      }
    });
    jchkBold.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setNewFont();
      }
    });
    jchkItalic.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setNewFont();
      }
    });
  }

  private void setNewFont() {
    // Determine a font style
    int fontStyle = Font.PLAIN;
    fontStyle += (jchkBold.isSelected() ? Font.BOLD : Font.PLAIN);
    fontStyle += (jchkItalic.isSelected() ? Font.ITALIC : Font.PLAIN);

    // Set font for the message
    Font font = messagePanel.getFont();
    messagePanel.setFont(
      new Font(font.getName(), fontStyle, font.getSize()));
  }
}
