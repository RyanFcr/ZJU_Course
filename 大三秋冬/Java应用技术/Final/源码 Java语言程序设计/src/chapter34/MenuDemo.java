package chapter34;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuDemo extends JApplet {
  // Text fields for Number 1, Number 2, and Result
  private JTextField jtfNum1, jtfNum2, jtfResult;

  // Buttons "Add", "Subtract", "Multiply" and "Divide"
  private JButton jbtAdd, jbtSub, jbtMul, jbtDiv;

  // Menu items "Add", "Subtract", "Multiply","Divide" and "Close"
  private JMenuItem jmiAdd, jmiSub, jmiMul, jmiDiv, jmiClose;

  public MenuDemo() {
    // Create menu bar
    JMenuBar jmb = new JMenuBar();

    // Set menu bar to the applet
    setJMenuBar(jmb);

    // Add menu "Operation" to menu bar
    JMenu operationMenu = new JMenu("Operation");
    operationMenu.setMnemonic('O');
    jmb.add(operationMenu);

    // Add menu "Exit" to menu bar
    JMenu exitMenu = new JMenu("Exit");
    exitMenu.setMnemonic('E');
    jmb.add(exitMenu);

    // Add menu items with mnemonics to menu "Operation"
    operationMenu.add(jmiAdd= new JMenuItem("Add", 'A'));
    operationMenu.add(jmiSub = new JMenuItem("Subtract", 'S'));
    operationMenu.add(jmiMul = new JMenuItem("Multiply", 'M'));
    operationMenu.add(jmiDiv = new JMenuItem("Divide", 'D'));
    exitMenu.add(jmiClose = new JMenuItem("Close", 'C'));

    // Set keyboard accelerators
    jmiAdd.setAccelerator(
      KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
    jmiSub.setAccelerator(
      KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    jmiMul.setAccelerator(
      KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
    jmiDiv.setAccelerator(
      KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));

    // Panel p1 to hold text fields and labels
    JPanel p1 = new JPanel(new FlowLayout());
    p1.add(new JLabel("Number 1"));
    p1.add(jtfNum1 = new JTextField(3));
    p1.add(new JLabel("Number 2"));
    p1.add(jtfNum2 = new JTextField(3));
    p1.add(new JLabel("Result"));
    p1.add(jtfResult = new JTextField(4));
    jtfResult.setEditable(false);

    // Panel p2 to hold buttons
    JPanel p2 = new JPanel(new FlowLayout());
    p2.add(jbtAdd = new JButton("Add"));
    p2.add(jbtSub = new JButton("Subtract"));
    p2.add(jbtMul = new JButton("Multiply"));
    p2.add(jbtDiv = new JButton("Divide"));

    // Add panels to the frame
    setLayout(new BorderLayout());
    add(p1, BorderLayout.CENTER);
    add(p2, BorderLayout.SOUTH);

    // Register listeners
    jbtAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        calculate('+');
      }
    });
    jbtSub.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        calculate('-');
      }
    });
    jbtMul.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        calculate('*');
      }
    });
    jbtDiv.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        calculate('/');
      }
    });
    jmiAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        calculate('+');
      }
    });
    jmiSub.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        calculate('-');
      }
    });
    jmiMul.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        calculate('*');
      }
    });
    jmiDiv.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        calculate('/');
      }
    });
    jmiClose.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
  }

  /** Calculate and show the result in jtfResult */
  private void calculate(char operator) {
    // Obtain Number 1 and Number 2
    int num1 = (Integer.parseInt(jtfNum1.getText().trim()));
    int num2 = (Integer.parseInt(jtfNum2.getText().trim()));
    int result = 0;

    // Perform selected operation
    switch (operator) {
      case '+': result = num1 + num2;
                break;
      case '-': result = num1 - num2;
                break;
      case '*': result = num1 * num2;
                break;
      case '/': result = num1 / num2;
    }

    // Set result in jtfResult
    jtfResult.setText(String.valueOf(result));
  }

  public static void main(String[] args) {
    MenuDemo applet = new MenuDemo();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("MenuDemo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
