package chapter29;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;

public class ProgressBarDemo extends JApplet {
  private JProgressBar jpb = new JProgressBar();
  private JTextArea jtaResult = new JTextArea();
  private JTextField jtfPrimeCount = new JTextField(8);
  private JButton jbtDisplayPrime = new JButton("Display Prime");
  
  public ProgressBarDemo() {   
    jpb.setStringPainted(true); // Paint the percent in a string
    jpb.setValue(0);
    jpb.setMaximum(100);
    
    jtaResult.setWrapStyleWord(true);
    jtaResult.setLineWrap(true);
    
    JPanel panel = new JPanel();
    panel.add(new JLabel("Enter the prime number count"));
    panel.add(jtfPrimeCount);
    panel.add(jbtDisplayPrime);
    
    add(jpb, BorderLayout.NORTH);
    add(new JScrollPane(jtaResult), BorderLayout.CENTER);
    add(panel, BorderLayout.SOUTH);
    
    jbtDisplayPrime.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {      
        ComputePrime task = new ComputePrime(
          Integer.parseInt(jtfPrimeCount.getText()), jtaResult);
                
        task.addPropertyChangeListener(new PropertyChangeListener() {
          public void propertyChange(PropertyChangeEvent e) {
            if ("progress".equals(e.getPropertyName())) {
              jpb.setValue((Integer)e.getNewValue());
            }
          }
        });
        
        task.execute(); // Execute SwingWorker
      }
    });
  }
  
  /** Task class for SwingWorker */
  static class ComputePrime extends SwingWorker<Integer, Integer> {
    private int count; 
    private JTextArea result; // Textarea in the UI
    
    /** Construct a runnable Task */
    public ComputePrime(int count, JTextArea result) {
      this.count = count;
      this.result = result;
    }
    
    /** Code run on a background thread */
    protected Integer doInBackground() {
      publishPrimeNumbers(count);
      
      return 0; // doInBachground must return a value
    }

    /** Override process to display published prime values */
    protected void process(java.util.List<Integer> list) {
      for (int i = 0; i < list.size(); i++) 
        result.append(list.get(i) + " ");
    }
    
    /** Publish the first n primes number */
    private void publishPrimeNumbers(int n) {    
      int count = 0; // Count the number of prime numbers
      int number = 2; // A number to be tested for primeness

      // Repeatedly find prime numbers
      while (count <= n) {
        // Print the prime number and increase the count
        if (isPrime(number)) {
          count++; // Increase the count
          setProgress(100 * count / n); // Update progress
          publish(number); // Publish the prime number
        }

        // Check if the next number is prime
        number++;
      }
    }

    /** Check whether number is prime */
    private static boolean isPrime(int number) {
      for (int divisor = 2; divisor <= number / 2; divisor++) {
        if (number % divisor == 0) { // If true, number is not prime
          return false; // number is not a prime
        }
      }

      return true; // number is prime
    }
  }
  
  public static void main(String[] args) {
    ProgressBarDemo applet = new ProgressBarDemo();
    JFrame frame = new JFrame();
    frame.setTitle("ProgressBarDemo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400, 200);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
