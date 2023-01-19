package chapter33;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShowLayout extends JApplet {
  // Get the url for HTML files
  private String flowLayoutDesc = "FlowLayout arranges components " +
    "according to their preferredSize in " +
    "a left-to-right flow, much like lines of text in a paragraph.";
  private String gridLayoutDesc = "GridLayout arranges ...";
  private String boxLayoutDesc = "BoxLayout arranges ...";

  private JRadioButton jrbFlowLayout =
    new JRadioButton("FlowLayout");
  private JRadioButton jrbGridLayout =
    new JRadioButton("GridLayout", true);
  private JRadioButton jrbBoxLayout =
    new JRadioButton("BoxLayout");

  private JPanel jpComponents = new JPanel();
  private JTextArea jtfDescription = new JTextArea();

  // Create layout managers
  private FlowLayout flowLayout = new FlowLayout();
  private GridLayout gridLayout = new GridLayout(2, 2, 3, 3);
  private BoxLayout boxLayout =
    new BoxLayout(jpComponents, BoxLayout.X_AXIS);

  public ShowLayout() {
    // Create a box to hold radio buttons
    Box jpChooseLayout = Box.createVerticalBox();
    jpChooseLayout.add(jrbFlowLayout);
    jpChooseLayout.add(jrbGridLayout);
    jpChooseLayout.add(jrbBoxLayout);

    // Group radio buttons
    ButtonGroup btg = new ButtonGroup();
    btg.add(jrbFlowLayout);
    btg.add(jrbGridLayout);
    btg.add(jrbBoxLayout);

    // Wrap lines and words
    jtfDescription.setLineWrap(true);
    jtfDescription.setWrapStyleWord(true);

    // Add fours buttons to jpComponents
    jpComponents.add(new JButton("Button 1"));
    jpComponents.add(new JButton("Button 2"));
    jpComponents.add(new JButton("Button 3"));
    jpComponents.add(new JButton("Button 4"));

    // Create two split panes to hold jpChooseLayout, jpComponents,
    // and jtfDescription
    JSplitPane jSplitPane2 = new JSplitPane(
      JSplitPane.VERTICAL_SPLIT, jpComponents,
      new JScrollPane(jtfDescription));
    JSplitPane jSplitPane1 = new JSplitPane(
      JSplitPane.HORIZONTAL_SPLIT, jpChooseLayout, jSplitPane2);

    // Set FlowLayout as default
    jpComponents.setLayout(flowLayout);
    jpComponents.revalidate();
    jtfDescription.setText(flowLayoutDesc);

    add(jSplitPane1, BorderLayout.CENTER);

    // Register listeners
    jrbFlowLayout.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jpComponents.setLayout(flowLayout);
        jtfDescription.setText(flowLayoutDesc);
        jpComponents.revalidate();
      }
    });
    jrbGridLayout.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jpComponents.setLayout(gridLayout);
        jtfDescription.setText(gridLayoutDesc);
        jpComponents.revalidate();
      }
    });
    jrbBoxLayout.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jpComponents.setLayout(boxLayout);
        jtfDescription.setText(boxLayoutDesc);
        jpComponents.revalidate();
      }
    });
  }

  public static void main(String[] args) {
    ShowLayout applet = new ShowLayout();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("ShowLayout");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
