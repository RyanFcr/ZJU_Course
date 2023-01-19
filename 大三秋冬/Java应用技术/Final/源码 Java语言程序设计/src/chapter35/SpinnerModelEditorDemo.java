package chapter35;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.text.*;
import java.awt.*;

public class SpinnerModelEditorDemo extends JApplet {
  // Create four spinners for date, day, month, and year
  private JSpinner jspDate =
    new JSpinner(new SpinnerDateModel());
  private JSpinner jspDay =
    new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
  private String[] monthNames = new DateFormatSymbols().getMonths();
  private JSpinner jspMonth = new JSpinner
    (new SpinnerListModel(Arrays.asList(monthNames).subList(0, 12)));
  private JSpinner spinnerYear =
    new JSpinner(new SpinnerNumberModel(2004, 1, 3000, 1));

  public SpinnerModelEditorDemo() {
    // Group labels
    JPanel panel1 = new JPanel();
    panel1.setLayout(new GridLayout(4, 1));
    panel1.add(new JLabel("Date"));
    panel1.add(new JLabel("Day"));
    panel1.add(new JLabel("Month"));
    panel1.add(new JLabel("Year"));

    // Group spinners
    JPanel panel2 = new JPanel();
    panel2.setLayout(new GridLayout(4, 1));
    panel2.add(jspDate);
    panel2.add(jspDay);
    panel2.add(jspMonth);
    panel2.add(spinnerYear);

    // Add spinner and label to the UI
    add(panel1, BorderLayout.WEST);
    add(panel2, BorderLayout.CENTER);

    // Set editor for date
    JSpinner.DateEditor dateEditor =
      new JSpinner.DateEditor(jspDate, "MMM dd, yyyy");
    jspDate.setEditor(dateEditor);

    // Set editor for year
    JSpinner.NumberEditor yearEditor =
      new JSpinner.NumberEditor(spinnerYear, "####");
    spinnerYear.setEditor(yearEditor);

    // Update date to synchronize with the day, month, and year
    updateDate();

    // Register and create a listener for jspDay
    jspDay.addChangeListener(new ChangeListener() {
      public void stateChanged(javax.swing.event.ChangeEvent e) {
        updateDate();
      }
    });

    // Register and create a listener for jspMonth
    jspMonth.addChangeListener(new ChangeListener() {
      public void stateChanged(javax.swing.event.ChangeEvent e) {
        updateDate();
      }
    });

    // Register and create a listener for spinnerYear
    spinnerYear.addChangeListener(new ChangeListener() {
      public void stateChanged(javax.swing.event.ChangeEvent e) {
        updateDate();
      }
    });
  }

  /** Update date spinner to synchronize with the other spinners */
  private void updateDate() {
    // Get current month and year in int
    int month = ((SpinnerListModel)jspMonth.getModel()).
        getList().indexOf(jspMonth.getValue());
    int year = ((Integer)spinnerYear.getValue()).intValue();

    // Set a new maximum number of days for the new month and year
    SpinnerNumberModel numberModel =
      (SpinnerNumberModel)jspDay.getModel();
    numberModel.setMaximum(new Integer(maxDaysInMonth(year, month)));

    // Set a new current day if it exceeds the maximum
    if (((Integer)(numberModel.getValue())).intValue() >
        maxDaysInMonth(year, month))
      numberModel.setValue(new Integer(maxDaysInMonth(year, month)));

    // Get the current day
    int day = ((Integer)jspDay.getValue()).intValue();

    // Set a new date in the date spinner
    jspDate.setValue(
      new GregorianCalendar(year, month, day).getTime());
  }

  /** Return the maximum number of days in a month. For example,
    Feb 2004 has 29 days. */
  private int maxDaysInMonth(int year, int month) {
    Calendar calendar = new GregorianCalendar(year, month, 1);
    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
  }

  public static void main(String[] args) {
    javax.swing.JFrame frame = new javax.swing.JFrame(
        "SpinnerModelEditorDemo");

    SpinnerModelEditorDemo applet = new SpinnerModelEditorDemo();

    // Add the applet instance to the frame
    frame.getContentPane().add(applet, java.awt.BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Invoke init and start
    applet.init();
    applet.start();

    // Display the frame
    frame.pack();
    frame.setVisible(true);
  }
}
