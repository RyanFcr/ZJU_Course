package chapter36;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

public class TestTreePath extends JApplet {
  private JTree jTree = new JTree();
  private JTextArea jtaOutput = new JTextArea();
  private JButton jbtShowPath = new JButton("Show Path");
  private JButton jbtShowPaths = new JButton("Show Paths");

  public TestTreePath() {
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
      new JScrollPane(jTree), new JScrollPane(jtaOutput));

    JPanel panel = new JPanel();
    panel.add(jbtShowPath);
    panel.add(jbtShowPaths);

    add(splitPane, BorderLayout.CENTER);
    add(panel, BorderLayout.NORTH);

    jbtShowPath.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        TreePath path = jTree.getSelectionPath();
        jtaOutput.append("\nProcessing a single path\n");
        jtaOutput.append("# of elements: " + path.getPathCount());
        jtaOutput.append("\nlast element: "
          + path.getLastPathComponent());
        jtaOutput.append("\nfrom last node in the path to the root: ");
        TreeNode node = (TreeNode)path.getLastPathComponent();
        while (node != null) {
          jtaOutput.append(node.toString() + " ");
          node = node.getParent();
        }
      }});

    jbtShowPaths.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jtaOutput.append("\nProcessing multiple paths\n");
        javax.swing.tree.TreePath[] paths = jTree.getSelectionPaths();
        for (int i = 0; i < paths.length; i++)
          jtaOutput.append(paths[i].toString() + "\n");
      }});
  }

  public static void main(String[] args) {
    TestTreePath applet = new TestTreePath();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("TestTreePath");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
