package chapter33;

import java.awt.*;
import javax.swing.*;

public class ShowNoLayout extends JApplet {
  private JButton jbtRed = new JButton("Red" );
  private JButton jbtBlue = new JButton("Blue" );
  private JButton jbtGreen = new JButton("Green" );

  public ShowNoLayout() {
    // Set foreground color for the buttons
    jbtRed.setForeground(Color.RED);
    jbtBlue.setForeground(Color.BLUE);
    jbtGreen.setForeground(Color.GREEN);

    // Specify no layout manager
    setLayout(null);

    // Add components to container
    add(jbtRed);
    add(jbtBlue);
    add(jbtGreen);

    // Put components in the right place
    jbtRed.setBounds(150, 50, 100, 50);
    jbtBlue.setBounds(100, 100, 100, 50);
    jbtGreen.setBounds(200, 100, 100, 50);
  }

  public static void main(String[] args) {
    ShowNoLayout applet = new ShowNoLayout();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("ShowNoLayout");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400, 200);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }
}
