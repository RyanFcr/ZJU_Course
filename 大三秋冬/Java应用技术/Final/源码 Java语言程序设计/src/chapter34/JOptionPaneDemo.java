package chapter34;

import javax.swing.*;
import chapter10.Loan;

public class JOptionPaneDemo {
  public static void main(String args[]) {
    // Create an array for annual interest rates
    Object[] rateList = new Object[25];
    int i = 0;
    for (double rate = 5; rate <= 8; rate += 1.0 / 8)
      rateList[i++] = new Double(rate);

    // Prompt the user to select an annual interest rate
    Object annualInterstRateObject = JOptionPane.showInputDialog(
      null, "Select annual interest rate:", "JOptionPaneDemo",
      JOptionPane.QUESTION_MESSAGE, null, rateList, null);
    double annualInterestRate =
      ((Double)annualInterstRateObject).doubleValue();

    // Create an array for number of years
    Object[] yearList = {new Integer(7), new Integer(15),
      new Integer(30)};

    // Prompt the user to enter number of years
    Object numberOfYearsObject = JOptionPane.showInputDialog(null,
      "Select number of years:", "JOptionPaneDemo",
      JOptionPane.QUESTION_MESSAGE, null, yearList, null);
    int numberOfYears = ((Integer)numberOfYearsObject).intValue();

    // Prompt the user to enter loan amount
    String loanAmountString = JOptionPane.showInputDialog(null,
      "Enter loan amount,\nfor example, 150000 for $150000",
      "JOptionPaneDemo", JOptionPane.QUESTION_MESSAGE);
    double loanAmount = Double.parseDouble(loanAmountString);

    // Obtain monthly payment and total payment
    Loan loan = new Loan(
      annualInterestRate, numberOfYears, loanAmount);
    double monthlyPayment = loan.getMonthlyPayment();
    double totalPayment = loan.getTotalPayment();

    // Prepare output string
    String output = "Interest Rate: " + annualInterestRate + "%" +
      " Number of Years: " + numberOfYears + " Loan Amount: $"
      + loanAmount;
    output += "\nMonthly Payment: " + "$" +
      (int)(monthlyPayment * 100) / 100.0;
    output += "\nTotal Payment: $" +
      (int)(monthlyPayment * 12 * numberOfYears * 100) / 100.0 + "\n";

    // Obtain monthly interest rate
    double monthlyInterestRate = annualInterestRate / 1200;

    double balance = loanAmount;
    double interest;
    double principal;

    // Display the header
    output += "\nPayment#\tInterest\tPrincipal\tBalance\n";

    for (i = 1; i <= numberOfYears * 12; i++) {
      interest = (int)(monthlyInterestRate * balance * 100) / 100.0;
      principal = (int)((monthlyPayment - interest) * 100) / 100.0;
      balance = (int)((balance - principal) * 100) / 100.0;
      output += i + "\t" + interest + "\t" + principal + "\t" +
        balance + "\n";
    }

    // Display monthly payment and total payment
    JScrollPane jsp = new JScrollPane(new JTextArea(output));
    jsp.setPreferredSize(new java.awt.Dimension(400, 200));
    JOptionPane.showMessageDialog(null, jsp,
      "JOptionPaneDemo", JOptionPane.INFORMATION_MESSAGE, null);
  }
}
