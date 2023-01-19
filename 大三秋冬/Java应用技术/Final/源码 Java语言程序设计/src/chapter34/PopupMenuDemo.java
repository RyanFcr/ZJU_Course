package chapter34;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PopupMenuDemo extends JApplet {
  private JPopupMenu jPopupMenu1 = new JPopupMenu();
  private JMenuItem jmiNew = new JMenuItem("New",
    new ImageIcon(getClass().getResource("image/new.gif")));
  private JMenuItem jmiOpen = new JMenuItem("Open",
    new ImageIcon(getClass().getResource("image/open.gif")));
  private JMenuItem jmiPrint = new JMenuItem("Print",
    new ImageIcon(getClass().getResource("image/print.gif")));
  private JMenuItem jmiExit = new JMenuItem("Exit");
  private JTextArea jTextArea1 = new JTextArea();

  public PopupMenuDemo() {
    jPopupMenu1.add(jmiNew);
    jPopupMenu1.add(jmiOpen);
    jPopupMenu1.addSeparator();
    jPopupMenu1.add(jmiPrint);
    jPopupMenu1.addSeparator();
    jPopupMenu1.add(jmiExit);
    jPopupMenu1.add(jmiExit);
    
    add(new JScrollPane(jTextArea1), BorderLayout.CENTER);
    jTextArea1.setComponentPopupMenu(jPopupMenu1);
   
    jmiNew.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Process New");
      }
    });
    jmiOpen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Process Open");
      }
    });
    jmiPrint.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Process Print");
      }
    });
    jmiExit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
  }

  public static void main(String[] args) {
    PopupMenuDemo applet = new PopupMenuDemo();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("PopupMenuDemo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
