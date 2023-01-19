package chapter33;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShowCardLayout extends JApplet {
  private CardLayout cardLayout = new CardLayout(20, 10);
  private JPanel cardPanel = new JPanel(cardLayout);
  private JButton jbtFirst, jbtNext, jbtPrevious, jbtLast;
  private JComboBox jcboImage;
  private final int NUM_OF_FLAGS = 6;

  public ShowCardLayout() {
    cardPanel.setBorder(
      new javax.swing.border.LineBorder(Color.red));

    // Add 9 labels for displaying images into cardPanel
    for (int i = 1; i <= NUM_OF_FLAGS; i++) {
      JLabel label =
        new JLabel(new ImageIcon("image/flag" + i + ".gif"));
      cardPanel.add(label, String.valueOf(i));
    }

    // Panel p to hold buttons and a combo box
    JPanel p = new JPanel();
    p.add(jbtFirst = new JButton("First"));
    p.add(jbtNext = new JButton("Next"));
    p.add(jbtPrevious= new JButton("Previous"));
    p.add(jbtLast = new JButton("Last"));
    p.add(new JLabel("Image"));
    p.add(jcboImage = new JComboBox());

    // Initialize combo box items
    for (int i = 1; i <= NUM_OF_FLAGS; i++)
      jcboImage.addItem(String.valueOf(i));

    // Place panels in the frame
    add(cardPanel, BorderLayout.CENTER);
    add(p, BorderLayout.SOUTH);

    // Register listeners with the source objects
    jbtFirst.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Show the first component in cardPanel
        cardLayout.first(cardPanel);
      }
    });
    jbtNext.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Show the first component in cardPanel
        cardLayout.next(cardPanel);
      }
    });
    jbtPrevious.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Show the first component in cardPanel
        cardLayout.previous(cardPanel);
      }
    });
    jbtLast.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Show the first component in cardPanel
        cardLayout.last(cardPanel);
      }
    });
    jcboImage.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        // Show the component at specified index
        cardLayout.show(cardPanel, (String)e.getItem());
      }
    });
  }

  //Main method
  public static void main(String[] args) {
    ShowCardLayout applet = new ShowCardLayout();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("ShowCardLayout");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2,
      (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }
}
