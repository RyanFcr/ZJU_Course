package chapter31;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.text.NumberFormat;

public class NumberFormatDemo extends JApplet {
  // Combo box for selecting available locales
  private JComboBox jcboLocale = new JComboBox();

  // Text fields for interest rate, year, and loan amount
  private JTextField jtfInterestRate = new JTextField("6.75");
  private JTextField jtfNumberOfYears = new JTextField("15");
  private JTextField jtfLoanAmount = new JTextField("107000");
  private JTextField jtfFormattedInterestRate = new JTextField(10);
  private JTextField jtfFormattedNumberOfYears = new JTextField(10);
  private JTextField jtfFormattedLoanAmount = new JTextField(10);

  // Text fields for monthly payment and total payment
  private JTextField jtfTotalPayment = new JTextField();
  private JTextField jtfMonthlyPayment = new JTextField();

  // Compute button
  private JButton jbtCompute = new JButton("Compute");

  // Current locale
  private Locale locale = Locale.getDefault();

  // Declare locales to store available locales
  private Locale locales[] = Calendar.getAvailableLocales();

  /** Initialize the combo box */
  public void initializeComboBox() {
    // Add locale names to the combo box
    for (int i = 0; i < locales.length; i++)
      jcboLocale.addItem(locales[i].getDisplayName());
  }

  /** Initialize the applet */
  public void init() {
    // Panel p1 to hold the combo box for selecting locales
    JPanel p1 = new JPanel();
    p1.setLayout(new FlowLayout());
    p1.add(jcboLocale);
    initializeComboBox();
    p1.setBorder(new TitledBorder("Choose a Locale"));

    // Panel p2 to hold the input
    JPanel p2 = new JPanel();
    p2.setLayout(new GridLayout(3, 3));
    p2.add(new JLabel("Interest Rate"));
    p2.add(jtfInterestRate);
    p2.add(jtfFormattedInterestRate);
    p2.add(new JLabel("Number of Years"));
    p2.add(jtfNumberOfYears);
    p2.add(jtfFormattedNumberOfYears);
    p2.add(new JLabel("Loan Amount"));
    p2.add(jtfLoanAmount);
    p2.add(jtfFormattedLoanAmount);
    p2.setBorder(new TitledBorder("Enter Annual Interest Rate, " +
       "Number of Years, and Loan Amount"));

    // Panel p3 to hold the result
    JPanel p3 = new JPanel();
    p3.setLayout(new GridLayout(2, 2));
    p3.setBorder(new TitledBorder("Payment"));
    p3.add(new JLabel("Monthly Payment"));
    p3.add(jtfMonthlyPayment);
    p3.add(new JLabel("Total Payment"));
    p3.add(jtfTotalPayment);

    // Set text field alignment
    jtfFormattedInterestRate.setHorizontalAlignment(JTextField.RIGHT);
    jtfFormattedNumberOfYears.setHorizontalAlignment(JTextField.RIGHT);
    jtfFormattedLoanAmount.setHorizontalAlignment(JTextField.RIGHT);
    jtfTotalPayment.setHorizontalAlignment(JTextField.RIGHT);
    jtfMonthlyPayment.setHorizontalAlignment(JTextField.RIGHT);

    // Set editable false
    jtfFormattedInterestRate.setEditable(false);
    jtfFormattedNumberOfYears.setEditable(false);
    jtfFormattedLoanAmount.setEditable(false);
    jtfTotalPayment.setEditable(false);
    jtfMonthlyPayment.setEditable(false);

    // Panel p4 to hold result payments and a button
    JPanel p4 = new JPanel();
    p4.setLayout(new BorderLayout());
    p4.add(p3, BorderLayout.CENTER);
    p4.add(jbtCompute, BorderLayout.SOUTH);

    // Place panels to the applet
    add(p1, BorderLayout.NORTH);
    add(p2, BorderLayout.CENTER);
    add(p4, BorderLayout.SOUTH);

    // Register listeners
    jcboLocale.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        locale = locales[jcboLocale.getSelectedIndex()];
        computeLoan();
      }
    });

    jbtCompute.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        computeLoan();
      }
    });
  }

  /** Compute payments and display results locale-sensitive format */
  private void computeLoan() {
    // Retrieve input from user
    double loan = new Double(jtfLoanAmount.getText()).doubleValue();
    double interestRate =
      new Double(jtfInterestRate.getText()).doubleValue() / 1240;
    int numberOfYears =
      new Integer(jtfNumberOfYears.getText()).intValue();

    // Calculate payments
    double monthlyPayment = loan * interestRate/
     (1 - (Math.pow(1 / (1 + interestRate), numberOfYears * 12)));
    double totalPayment = monthlyPayment * numberOfYears * 12;

    // Get formatters
    NumberFormat percentFormatter =
      NumberFormat.getPercentInstance(locale);
    NumberFormat currencyForm =
      NumberFormat.getCurrencyInstance(locale);
    NumberFormat numberForm = NumberFormat.getNumberInstance(locale);
    percentFormatter.setMinimumFractionDigits(2);

    // Display formatted input
    jtfFormattedInterestRate.setText(
      percentFormatter.format(interestRate * 12));
    jtfFormattedNumberOfYears.setText
      (numberForm.format(numberOfYears));
    jtfFormattedLoanAmount.setText(currencyForm.format(loan));

    // Display results in currency format
    jtfMonthlyPayment.setText(currencyForm.format(monthlyPayment));
    jtfTotalPayment.setText(currencyForm.format(totalPayment));
  }

  /** Main method */
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("NumberFormatDemo");

    // Create an instance of the applet
    NumberFormatDemo applet = new NumberFormatDemo();

    // Add the applet instance to the frame
    frame.getContentPane().add(applet, BorderLayout.CENTER);

    // Invoke init() and start()
    applet.init();
    applet.start();

    // Display the frame
    frame.setSize(400, 300);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
