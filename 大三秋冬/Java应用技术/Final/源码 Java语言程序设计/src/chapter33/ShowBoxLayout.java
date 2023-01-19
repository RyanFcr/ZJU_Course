package chapter33;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShowBoxLayout extends JApplet {
  // Create two box containers
  private Box box1 = Box.createHorizontalBox();
  private Box box2 = Box.createVerticalBox();

  // Create a label to display flags
  private JLabel jlblFlag = new JLabel();

  // Create image icons for flags
  private ImageIcon imageIconUS =
    new ImageIcon(getClass().getResource("/image/us.gif"));
  private ImageIcon imageIconCanada =
    new ImageIcon(getClass().getResource("/image/ca.gif"));
  private ImageIcon imageIconNorway =
    new ImageIcon(getClass().getResource("/image/norway.gif"));
  private ImageIcon imageIconGermany =
    new ImageIcon(getClass().getResource("/image/germany.gif"));
  private ImageIcon imageIconPrint =
    new ImageIcon(getClass().getResource("/image/print.gif"));
  private ImageIcon imageIconSave =
    new ImageIcon(getClass().getResource("/image/save.gif"));

  // Create buttons to select images
  private JButton jbtUS = new JButton("US");
  private JButton jbtCanada = new JButton("Canada");
  private JButton jbtNorway = new JButton("Norway");
  private JButton jbtGermany = new JButton("Germany");

  public ShowBoxLayout() {
    box1.add(new JButton(imageIconPrint));
    box1.add(Box.createHorizontalStrut(20));
    box1.add(new JButton(imageIconSave));

    box2.add(jbtUS);
    box2.add(Box.createVerticalStrut(8));
    box2.add(jbtCanada);
    box2.add(Box.createGlue());
    box2.add(jbtNorway);
    box2.add(Box.createRigidArea(new Dimension(10, 8)));
    box2.add(jbtGermany);

    box1.setBorder(new javax.swing.border.LineBorder(Color.red));
    box2.setBorder(new javax.swing.border.LineBorder(Color.black));

    add(box1, BorderLayout.NORTH);
    add(box2, BorderLayout.EAST);
    add(jlblFlag, BorderLayout.CENTER);

    // Register listeners
    jbtUS.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jlblFlag.setIcon(imageIconUS);
      }
    });
    jbtCanada.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jlblFlag.setIcon(imageIconCanada);
      }
    });
    jbtNorway.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jlblFlag.setIcon(imageIconNorway);
      }
    });
    jbtGermany.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jlblFlag.setIcon(imageIconGermany);
      }
    });
  }

  public static void main(String[] args) {
    ShowBoxLayout applet = new ShowBoxLayout();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("ShowBoxLayout");
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
