package chapter4;

import java.util.Scanner; 

public class TestDoWhile {
  /** Main method */
  public static void main(String[] args) {
    int data;
    int sum = 0;

    // Create a Scanner
    Scanner input = new Scanner(System.in);

    // Keep reading data until the input is 0
    do {
      // Read the next data
      System.out.print(
        "Enter an int value (the program exits if the input is 0): ");
      data = input.nextInt();

      sum += data;
    } while (data != 0);

    System.out.println("The sum is " + sum);
  }
}
