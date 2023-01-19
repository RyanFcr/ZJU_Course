package chapter17;

import javax.swing.*;
import java.awt.*;

public class DescriptionPanel extends JPanel {
  /** Label for displaying an image icon and a text */
  private JLabel jlblImageTitle = new JLabel();

  /** Text area for displaying text */
  private JTextArea jtaDescription = new JTextArea();

  public DescriptionPanel() {
    // Center the icon and text and place the text under the icon
    jlblImageTitle.setHorizontalAlignment(JLabel.CENTER);
    jlblImageTitle.setHorizontalTextPosition(JLabel.CENTER);
    jlblImageTitle.setVerticalTextPosition(JLabel.BOTTOM);

    // Set the font in the label and the text field
    jlblImageTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
    jtaDescription.setFont(new Font("Serif", Font.PLAIN, 14));

    // Set lineWrap and wrapStyleWord true for the text area
    jtaDescription.setLineWrap(true);
    jtaDescription.setWrapStyleWord(true);
    jtaDescription.setEditable(false);

    // Create a scroll pane to hold the text area
    JScrollPane scrollPane = new JScrollPane(jtaDescription);

    // Set BorderLayout for the panel, add label and scrollpane
    setLayout(new BorderLayout(5, 5));
    add(scrollPane, BorderLayout.CENTER);
    add(jlblImageTitle, BorderLayout.WEST);
  }

  /** Set the title */
  public void setTitle(String title) {
    jlblImageTitle.setText(title);
  }

  /** Set the image icon */
  public void setImageIcon(ImageIcon icon) {
    jlblImageTitle.setIcon(icon);
  }

  /** Set the text description */
  public void setDescription(String text) {
    jtaDescription.setText(text);
  }
}
