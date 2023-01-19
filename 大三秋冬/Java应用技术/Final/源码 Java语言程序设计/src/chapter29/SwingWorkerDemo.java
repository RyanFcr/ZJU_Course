package chapter29;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingWorkerDemo extends JApplet {
  private JButton jbtComputeWithSwingWorker = new JButton("Compute");
  private JTextField jtfLimit1 = new JTextField(8);
  private JTextField jtfResult1 = new JTextField(6);
	private JButton jbtCompute = new JButton("Compute");
	private JTextField jtfLimit2 = new JTextField(8);
	private JTextField jtfResult2 = new JTextField(6);
	
	public SwingWorkerDemo() {
    JPanel panel1 = new JPanel(new GridLayout(2, 1));
    panel1.setBorder(BorderFactory.createTitledBorder(
      "Using SwingWorker"));
    JPanel panel11 = new JPanel();
    panel11.add(new JLabel("The number of prime numbers <= "));
    panel11.add(jtfLimit1);
    panel11.add(new JLabel("is"));
    panel11.add(jtfResult1);
    JPanel panel12 = new JPanel();
    panel12.add(jbtComputeWithSwingWorker);
    panel1.add(panel11);
    panel1.add(panel12);
		
    JPanel panel2 = new JPanel(new GridLayout(2, 1));
    panel2.setBorder(BorderFactory.createTitledBorder(
      "Without Using SwingWorker"));
    JPanel panel21 = new JPanel();
    panel21.add(new JLabel("The number of prime numbers <= "));
    panel21.add(jtfLimit2);
    panel21.add(new JLabel("is"));
    panel21.add(jtfResult2);
    JPanel panel22 = new JPanel();
    panel22.add(jbtCompute);
    panel2.add(panel21);
    panel2.add(panel22);
		
		setLayout(new GridLayout(1, 2));
		add(panel1);
		add(panel2);
		
		jbtComputeWithSwingWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				new ComputePrime(Integer.parseInt(jtfLimit1.getText()), 
					jtfResult1).execute(); // Execute SwingWorker
			}
		});
		
		jbtCompute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				int count = ComputePrime.getNumberOfPrimes(
					Integer.parseInt(jtfLimit2.getText()));
				jtfResult2.setText(count + "");
			}
		});	
	}
	
	/** Task class for SwingWorker */
	static class ComputePrime extends SwingWorker<Integer, Object> {
		private int limit; 
		private JTextField result; // Text field in the UI
		
		/** Construct a runnable Task */
		public ComputePrime(int limit, JTextField result) {
			this.limit = limit;
			this.result = result;
		}
		
		/** Code run on a background thread */
		protected Integer doInBackground() {
			return getNumberOfPrimes(limit);
		}
		
		/** Code executed after the background thread finishes */
		protected void done() {
			try {
			  result.setText(get().toString()); // Display in text field
			}
			catch (Exception ex) {
			  result.setText(ex.getMessage()); 
			}
		}	

		/** Return the number of primes <= limit */
		public static int getNumberOfPrimes(int limit) {		
	    int count = 0; // Count the number of prime numbers
	    int number = 2; // A number to be tested for primeness

	    // Repeatedly find prime numbers
	    while (number <= limit) {
	      // Print the prime number and increase the count
	      if (isPrime(number)) {
	        count++; // Increase the count
	      }

	      // Check if the next number is prime
	      number++;
	    }
	    
	    return count;
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
    SwingWorkerDemo applet = new SwingWorkerDemo();
    JFrame frame = new JFrame();
    frame.setTitle("SwingWorkerDemo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.pack();
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
