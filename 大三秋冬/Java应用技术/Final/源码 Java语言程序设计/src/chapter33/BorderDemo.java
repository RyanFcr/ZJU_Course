package chapter33;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.*;

public class BorderDemo extends JApplet {
  // Declare a label for displaying message
  private JLabel jLabel1 = new JLabel("Display the border type",
    JLabel.CENTER);

  // A check box for selecting a border with or without a title
  private JCheckBox jchkTitled;

  // Radio buttons for border styles
  private JRadioButton jrbLoweredBevel, jrbRaisedBevel,
    jrbEtched, jrbLine, jrbMatte, jrbEmpty;

  // Radio buttons for titled border options
  private JRadioButton jrbAboveBottom, jrbBottom,
    jrbBelowBottom, jrbAboveTop, jrbTop, jrbBelowTop,
    jrbLeft, jrbCenter, jrbRight;

  // TitledBorder for the label
  private TitledBorder jLabel1Border;

  /** Constructor */
  public BorderDemo() {
    // Create a JLabel instance and set colors
    jLabel1.setBackground(Color.yellow);
    jLabel1.setBorder(jLabel1Border);

    // Place title position radio buttons
    JPanel jpPosition = new JPanel();
    jpPosition.setLayout(new GridLayout(3, 2));
    jpPosition.add(
      jrbAboveBottom = new JRadioButton("ABOVE_BOTTOM"));
    jpPosition.add(jrbAboveTop = new JRadioButton("ABOVE_TOP"));
    jpPosition.add(jrbBottom = new JRadioButton("BOTTOM"));
    jpPosition.add(jrbTop = new JRadioButton("TOP"));
    jpPosition.add(
      jrbBelowBottom = new JRadioButton("BELOW_BOTTOM"));
    jpPosition.add(jrbBelowTop = new JRadioButton("BELOW_TOP"));
    jpPosition.setBorder(new TitledBorder("Position"));

    // Place title justification radio buttons
    JPanel jpJustification = new JPanel();
    jpJustification.setLayout(new GridLayout(3,1));
    jpJustification.add(jrbLeft = new JRadioButton("LEFT"));
    jpJustification.add(jrbCenter = new JRadioButton("CENTER"));
    jpJustification.add(jrbRight = new JRadioButton("RIGHT"));
    jpJustification.setBorder(new TitledBorder("Justification"));

    // Create panel jpTitleOptions to hold jpPosition and
    // jpJustification
    JPanel jpTitleOptions = new JPanel();
    jpTitleOptions.setLayout(new BorderLayout());
    jpTitleOptions.add(jpPosition, BorderLayout.CENTER);
    jpTitleOptions.add(jpJustification, BorderLayout.EAST);

    // Create Panel jpTitle to hold a check box and title position
    // radio buttons, and title justification radio buttons
    JPanel jpTitle = new JPanel();
    jpTitle.setBorder(new TitledBorder("Border Title"));
    jpTitle.setLayout(new BorderLayout());
    jpTitle.add(jchkTitled = new JCheckBox("Titled"),
      BorderLayout.NORTH);
    jpTitle.add(jpTitleOptions, BorderLayout.CENTER);

    // Group radio buttons for title position
    ButtonGroup btgTitlePosition = new ButtonGroup();
    btgTitlePosition.add(jrbAboveBottom);
    btgTitlePosition.add(jrbBottom);
    btgTitlePosition.add(jrbBelowBottom);
    btgTitlePosition.add(jrbAboveTop);
    btgTitlePosition.add(jrbTop);
    btgTitlePosition.add(jrbBelowTop);

    // Group radio buttons for title justification
    ButtonGroup btgTitleJustification = new ButtonGroup();
    btgTitleJustification.add(jrbLeft);
    btgTitleJustification.add(jrbCenter);
    btgTitleJustification.add(jrbRight);

    // Create Panel jpBorderStyle to hold border style radio buttons
    JPanel jpBorderStyle = new JPanel();
    jpBorderStyle.setBorder(new TitledBorder("Border Style"));
    jpBorderStyle.setLayout(new GridLayout(6, 1));
    jpBorderStyle.add(jrbLoweredBevel =
      new JRadioButton("Lowered Bevel"));
    jpBorderStyle.add(jrbRaisedBevel =
      new JRadioButton("Raised Bevel"));
    jpBorderStyle.add(jrbEtched = new JRadioButton("Etched"));
    jpBorderStyle.add(jrbLine = new JRadioButton("Line"));
    jpBorderStyle.add(jrbMatte = new JRadioButton("Matte"));
    jpBorderStyle.add(jrbEmpty = new JRadioButton("Empty"));

    // Group radio buttons for border styles
    ButtonGroup btgBorderStyle = new ButtonGroup();
    btgBorderStyle.add(jrbLoweredBevel);
    btgBorderStyle.add(jrbRaisedBevel);
    btgBorderStyle.add(jrbEtched);
    btgBorderStyle.add(jrbLine);
    btgBorderStyle.add(jrbMatte);
    btgBorderStyle.add(jrbEmpty);

    // Create Panel jpAllChoices to place jpTitle and jpBorderStyle
    JPanel jpAllChoices = new JPanel();
    jpAllChoices.setLayout(new BorderLayout());
    jpAllChoices.add(jpTitle, BorderLayout.CENTER);
    jpAllChoices.add(jpBorderStyle, BorderLayout.EAST);

    // Place panels in the frame
    setLayout(new BorderLayout());
    add(jLabel1, BorderLayout.CENTER);
    add(jpAllChoices, BorderLayout.SOUTH);

    // Register listeners
    ActionListener listener = new EventListener();
    jchkTitled.addActionListener(listener);
    jrbAboveBottom.addActionListener(listener);
    jrbBottom.addActionListener(listener);
    jrbBelowBottom.addActionListener(listener);
    jrbAboveTop.addActionListener(listener);
    jrbTop.addActionListener(listener);
    jrbBelowTop.addActionListener(listener);
    jrbLeft.addActionListener(listener);
    jrbCenter.addActionListener(listener);
    jrbRight.addActionListener(listener);
    jrbLoweredBevel.addActionListener(listener);
    jrbRaisedBevel.addActionListener(listener);
    jrbLine.addActionListener(listener);
    jrbEtched.addActionListener(listener);
    jrbMatte.addActionListener(listener);
    jrbEmpty.addActionListener(listener);
  }

  private class EventListener implements ActionListener {
    /** Handle ActionEvents on check box and radio buttons */
    public void actionPerformed(ActionEvent e) {
      // Get border style
      Border border = new EmptyBorder(2, 2, 2, 2);

      if (jrbLoweredBevel.isSelected()) {
        border = new BevelBorder(BevelBorder.LOWERED);
        jLabel1.setText("Lowered Bevel Style");
      }
      else if (jrbRaisedBevel.isSelected()) {
        border = new BevelBorder(BevelBorder.RAISED);
        jLabel1.setText("Raised Bevel Style");
      }
      else if (jrbEtched.isSelected()) {
        border = new EtchedBorder();
        jLabel1.setText("Etched Style");
      }
      else if (jrbLine.isSelected()) {
        border = new LineBorder(Color.black, 5);
        jLabel1.setText("Line Style");
      }
      else if (jrbMatte.isSelected()) {
        border = new MatteBorder(15, 15, 15, 15,
          new ImageIcon(getClass().getResource
            ("/image/caIcon.gif")));
        jLabel1.setText("Matte Style");
      }
      else if (jrbEmpty.isSelected()) {
        border = new EmptyBorder(2, 2, 2, 2);
        jLabel1.setText("Empty Style");
      }

      if (jchkTitled.isSelected()) {
        // Get the title position and justification
        int titlePosition = TitledBorder.DEFAULT_POSITION;
        int titleJustification = TitledBorder.DEFAULT_JUSTIFICATION;

        if (jrbAboveBottom.isSelected())
          titlePosition = TitledBorder.ABOVE_BOTTOM;
        else if (jrbBottom.isSelected())
          titlePosition = TitledBorder.BOTTOM;
        else if (jrbBelowBottom.isSelected())
          titlePosition = TitledBorder.BELOW_BOTTOM;
        else if (jrbAboveTop.isSelected())
          titlePosition = TitledBorder.ABOVE_TOP;
        else if (jrbTop.isSelected())
          titlePosition = TitledBorder.TOP;
        else if (jrbBelowTop.isSelected())
          titlePosition = TitledBorder.BELOW_TOP;

        if (jrbLeft.isSelected())
          titleJustification = TitledBorder.LEFT;
        else if (jrbCenter.isSelected())
          titleJustification = TitledBorder.CENTER;
        else if (jrbRight.isSelected())
          titleJustification = TitledBorder.RIGHT;

        jLabel1Border = new TitledBorder("A Title");
        jLabel1Border.setBorder(border);
        jLabel1Border.setTitlePosition(titlePosition);
        jLabel1Border.setTitleJustification(titleJustification);
        jLabel1.setBorder(jLabel1Border);
      }
      else {
        jLabel1.setBorder(border);
      }
    }
  }

  public static void main(String[] args) {
    BorderDemo applet = new BorderDemo();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("BorderDemo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
