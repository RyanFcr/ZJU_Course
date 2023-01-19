package chapter31;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.text.NumberFormat;

public class ResourceBundleDemo extends JApplet {
  // Combo box for selecting available locales
  private JComboBox jcboLocale = new JComboBox();
  private ResourceBundle res = ResourceBundle.getBundle("MyResource");

  // Create labels
  private JLabel jlblInterestRate =
    new JLabel(res.getString("Annual_Interest_Rate"));
  private JLabel jlblNumberOfYears =
    new JLabel(res.getString("Number_Of_Years"));
  private JLabel jlblLoanAmount = 
    new JLabel(res.getString("Loan_Amount"));
  private JLabel jlblMonthlyPayment =
    new JLabel(res.getString("Monthly_Payment"));
  private JLabel jlblTotalPayment =
    new JLabel(res.getString("Total_Payment"));

  // Create titled borders
  private TitledBorder comboBoxTitle =
    new TitledBorder(res.getString("Choose_a_Locale"));
  private TitledBorder inputTitle = new TitledBorder
    (res.getString("Enter_Interest_Rate"));
  private TitledBorder paymentTitle =
    new TitledBorder(res.getString("Payment"));

  // Text fields for interest rate, year, loan amount,
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
  private JButton jbtCompute = new JButton(res.getString("Compute"));

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
    p1.setBorder(comboBoxTitle);

    // Panel p2 to hold the input for annual interest rate,
    // number of years and loan amount
    JPanel p2 = new JPanel();
    p2.setLayout(new GridLayout(3, 3));
    p2.add(jlblInterestRate);
    p2.add(jtfInterestRate);
    p2.add(jtfFormattedInterestRate);
    p2.add(jlblNumberOfYears);
    p2.add(jtfNumberOfYears);
    p2.add(jtfFormattedNumberOfYears);
    p2.add(jlblLoanAmount);
    p2.add(jtfLoanAmount);
    p2.add(jtfFormattedLoanAmount);
    p2.setBorder(inputTitle);

    // Panel p3 to hold the payment
    JPanel p3 = new JPanel();
    p3.setLayout(new GridLayout(2, 2));
    p3.setBorder(paymentTitle);
    p3.add(jlblMonthlyPayment);
    p3.add(jtfMonthlyPayment);
    p3.add(jlblTotalPayment);
    p3.add(jtfTotalPayment);

    // Set text field alignment
    jtfFormattedInterestRate.setHorizontalAlignment
      (JTextField.RIGHT);
    jtfFormattedNumberOfYears.setHorizontalAlignment
      (JTextField.RIGHT);
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
        updateStrings();
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

  /** Update resource strings */
  private void updateStrings() {
    res = ResourceBundle.getBundle("MyResource", locale);
    jlblInterestRate.setText(res.getString("Annual_Interest_Rate"));
    jlblNumberOfYears.setText(res.getString("Number_Of_Years"));
    jlblLoanAmount.setText(res.getString("Loan_Amount"));
    jlblTotalPayment.setText(res.getString("Total_Payment"));
    jlblMonthlyPayment.setText(res.getString("Monthly_Payment"));
    jbtCompute.setText(res.getString("Compute"));
    comboBoxTitle.setTitle(res.getString("Choose_a_Locale"));
    inputTitle.setTitle(res.getString("Enter_Interest_Rate"));
    paymentTitle.setTitle(res.getString("Payment"));

    // Make sure the new labels are displayed
    repaint();
}

  /** Main method */
  public static void main(String[] args) {
    // Create an instance of the applet
    ResourceBundleDemo applet = new ResourceBundleDemo();

    // Create a frame with a resource string
    JFrame frame = new JFrame(
      applet.res.getString("Number_Formatting"));

    // Add the applet instance to the frame
    frame.add(applet, BorderLayout.CENTER);

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
