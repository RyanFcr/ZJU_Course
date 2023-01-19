package chapter34;

import javax.swing.*;
import java.awt.*;

public class ToolBarDemo extends JApplet {
  private JButton jbtNew = new JButton(
    new ImageIcon(getClass().getResource("/image/new.gif")));
  private JButton jbtOpen = new JButton(
    new ImageIcon(getClass().getResource("/image/open.gif")));
  private JButton jbPrint = new JButton(
    new ImageIcon(getClass().getResource("/image/print.gif")));

  public ToolBarDemo() {
    JToolBar jToolBar1 = new JToolBar("My Tool Bar");
    jToolBar1.setFloatable(false);
    jToolBar1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    jToolBar1.add(jbtNew);
    jToolBar1.add(jbtOpen);
    jToolBar1.add(jbPrint);

    jbtNew.setToolTipText("New");
    jbtOpen.setToolTipText("Open");
    jbPrint.setToolTipText("Print");

    jbtNew.setBorderPainted(false);
    jbtOpen.setBorderPainted(false);
    jbPrint.setBorderPainted(false);

    add(jToolBar1, BorderLayout.NORTH);
  }

  public static void main(String[] args) {
    ToolBarDemo applet = new ToolBarDemo();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("ToolBarDemo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
