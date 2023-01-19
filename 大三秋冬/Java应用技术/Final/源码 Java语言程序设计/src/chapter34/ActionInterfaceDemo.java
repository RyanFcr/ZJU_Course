package chapter34;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ActionInterfaceDemo extends JApplet {
  private JPanel buttonPanel = new JPanel();
  private FlowLayout flowLayout = new FlowLayout();

  public ActionInterfaceDemo() {
    // Create image icons
    ImageIcon leftImageIcon = new ImageIcon(getClass().getResource(
      "/image/leftAlignment.png"));
    ImageIcon centerImageIcon = new ImageIcon(getClass().getResource(
      "/image/centerAlignment.png"));
    ImageIcon rightImageIcon = new ImageIcon(getClass().getResource(
      "/image/rightAlignment.png"));

    // Create actions
    Action leftAction = new MyAction("Left", leftImageIcon,
      "Left alignment for the buttons in the panel",
      new Integer(KeyEvent.VK_L),
      KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
    Action centerAction = new MyAction("Center", centerImageIcon,
      "Center alignment for the buttons in the panel",
      new Integer(KeyEvent.VK_C),
      KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
    Action rightAction = new MyAction("Right", rightImageIcon,
      "Right alignment for the buttons in the panel",
      new Integer(KeyEvent.VK_R),
      KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));

    // Create menus
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jmenuAlignment = new JMenu("Alighnment");
    setJMenuBar(jMenuBar1);
    jMenuBar1.add(jmenuAlignment);

    // Add actions to the menu
    jmenuAlignment.add(leftAction);
    jmenuAlignment.add(centerAction);
    jmenuAlignment.add(rightAction);

    // Add actions to the toolbar
    JToolBar jToolBar1 = new JToolBar(JToolBar.VERTICAL);
    jToolBar1.setBorder(BorderFactory.createLineBorder(Color.red));
    jToolBar1.add(leftAction);
    jToolBar1.add(centerAction);
    jToolBar1.add(rightAction);

    // Add buttons to the button panel
    buttonPanel.setLayout(flowLayout);
    JButton jbtLeft = new JButton(leftAction);
    JButton jbtCenter = new JButton(centerAction);
    JButton jbtRight = new JButton(rightAction);
    buttonPanel.add(jbtLeft);
    buttonPanel.add(jbtCenter);
    buttonPanel.add(jbtRight);

    // Add tool bar to the east and panel to the center
    add(jToolBar1, BorderLayout.EAST);
    add(buttonPanel, BorderLayout.CENTER);
  }

  private class MyAction extends AbstractAction {
    String name;

    MyAction(String name, Icon icon) {
      super(name, icon);
      this.name = name;
    }

    MyAction(String name, Icon icon, String desc, Integer mnemonic,
        KeyStroke accelerator) {
      super(name, icon);
      putValue(Action.SHORT_DESCRIPTION, desc);
      putValue(Action.MNEMONIC_KEY, mnemonic);
      putValue(Action.ACCELERATOR_KEY, accelerator);
      this.name = name;
    }

    public void actionPerformed(ActionEvent e) {
      if (name.equals("Left"))
        flowLayout.setAlignment(FlowLayout.LEFT);
      else if (name.equals("Center"))
        flowLayout.setAlignment(FlowLayout.CENTER);
      else if (name.equals("Right"))
        flowLayout.setAlignment(FlowLayout.RIGHT);

      buttonPanel.revalidate();
    }
  }

  public static void main(String[] args) {
    ActionInterfaceDemo applet = new ActionInterfaceDemo();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("ActionInterfaceDemo");
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
