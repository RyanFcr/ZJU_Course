package chapter35;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class CircleController extends JPanel {
  private CircleModel model;
  private JTextField jtfRadius = new JTextField();
  private JComboBox jcboFilled = new JComboBox(new Boolean[]{
    new Boolean(false), new Boolean(true)});

  /** Creates new form CircleController */
  public CircleController() {
    // Panel to group labels
    JPanel panel1 = new JPanel();
    panel1.setLayout(new GridLayout(2, 1));
    panel1.add(new JLabel("Radius"));
    panel1.add(new JLabel("Filled"));

    // Panel to group text field, combo box, and another panel
    JPanel panel2 = new JPanel();
    panel2.setLayout(new GridLayout(2, 1));
    panel2.add(jtfRadius);
    panel2.add(jcboFilled);

    setLayout(new BorderLayout());
    add(panel1, BorderLayout.WEST);
    add(panel2, BorderLayout.CENTER);

    // Register listeners
    jtfRadius.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (model == null) return; // No model associated yet. Do nothing
        model.setRadius(new Double(jtfRadius.getText()).doubleValue());
      }
    });
    jcboFilled.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (model == null) return; // No model associated yet. Do nothing
        model.setFilled(
          ((Boolean)jcboFilled.getSelectedItem()).booleanValue());
      }
    });
  }

  public void setModel(CircleModel newModel) {
    model = newModel;
  }

  public CircleModel getModel() {
    return model;
  }
}
