package chapter36;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class SimpleTreeDemo extends JApplet {
  // Create a default tree
  private JTree jTree1 = new JTree();

  // Create a tree with an array of Objects.
  private JTree jTree2 = new JTree(new String[]
    {"dog", "cow", "cat", "pig", "rabbit"});

  // Create a tree with a Hashtable
  private Vector<Object> vector = new Vector<Object>(Arrays.asList(
    new Object[]{"red", "green", "black", "white", "purple"}));
  private JTree jTree3 = new JTree(vector);

  private Hashtable<Integer, String> hashtable =
    new Hashtable<Integer, String>();
  private JTree jTree4;

  // Create a combo box for selecting rootVisible
  private JComboBox jcboRootVisible = new JComboBox(
    new String[]{"false", "true"});

  // Create a combo box for selecting showRootHandles
  private JComboBox jcboShowsRootHandles = new JComboBox(
    new String[] {"false", "true"});

  // Create a spinner for selecting row height
  private JSpinner jSpinnerRowHeight = new JSpinner(
      new SpinnerNumberModel(16, 1, 50, 1));

  public SimpleTreeDemo() {
    jTree1.setRootVisible(false);

    hashtable.put(1, "red");
    hashtable.put(2, "green");
    hashtable.put(3, "blue");
    hashtable.put(4, "yellow");
    jTree4 = new JTree(hashtable);

    JPanel panel1 = new JPanel(new GridLayout(1, 4));
    panel1.add(new JScrollPane(jTree1));
    panel1.add(new JScrollPane(jTree2));
    panel1.add(new JScrollPane(jTree3));
    panel1.add(new JScrollPane(jTree4));

    JPanel panel2 = new JPanel();
    panel2.add(new JLabel("rootVisible"));
    panel2.add(jcboRootVisible);
    panel2.add(new JLabel("rowHeight"));
    panel2.add(jSpinnerRowHeight);
    panel2.add(new JLabel("showsRootHandles"));
    panel2.add(jcboShowsRootHandles);

    add(panel1, BorderLayout.CENTER);
    add(panel2, BorderLayout.SOUTH);

    // Register listeners
    jcboRootVisible.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        boolean rootVisible =
          jcboRootVisible.getSelectedItem().equals("true");
        jTree1.setRootVisible(rootVisible);
        jTree2.setRootVisible(rootVisible);
        jTree3.setRootVisible(rootVisible);
        jTree4.setRootVisible(rootVisible);
      }
    });

    jcboShowsRootHandles.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        boolean showsRootHandles =
          jcboShowsRootHandles.getSelectedItem().equals("true");
        jTree1.setShowsRootHandles(showsRootHandles);
        jTree2.setShowsRootHandles(showsRootHandles);
        jTree3.setShowsRootHandles(showsRootHandles);
        jTree4.setShowsRootHandles(showsRootHandles);
      }
    });

    jSpinnerRowHeight.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        int height =
          ((Integer)(jSpinnerRowHeight.getValue())).intValue();
        jTree1.setRowHeight(height);
        jTree2.setRowHeight(height);
        jTree3.setRowHeight(height);
        jTree4.setRowHeight(height);
      }
    });
  }

  public static void main(String[] args) {
    SimpleTreeDemo applet = new SimpleTreeDemo();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("SimpleTreeDemo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
