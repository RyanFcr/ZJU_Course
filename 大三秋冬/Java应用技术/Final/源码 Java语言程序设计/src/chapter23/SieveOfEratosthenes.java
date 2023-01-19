package chapter23;

import java.util.Scanner;

public class SieveOfEratosthenes {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Find all prime numbers <= n, enter n: ");
    int n = input.nextInt();
    
    boolean[] primes = new boolean[n + 1]; // Prime number sieve
    
    // Initialize primes[i] to true
    for (int i = 0; i < primes.length; i++) {
      primes[i] = true; 
    }
    
    for (int k = 2; k <= n / k; k++) {
      if (primes[k]) {
        for (int i = k; i <= n / k; i++) {
          primes[k * i] = false; // k * i is not prime
        }
      }
    }
    
    final int NUMBER_PER_LINE = 10; // Display 10 per line
    int count = 0; // Count the number of prime numbers found so far
    // Print prime numbers
    for (int i = 2; i < primes.length; i++) {
      if (primes[i]) {
        count++;
        if (count % 10 == 0) 
          System.out.printf("%7d\n", i);
        else
          System.out.printf("%7d", i);          
      }
    }
    
    System.out.println("\n" + count + 
      " prime(s) less than or equal to " + n);
  }
}
