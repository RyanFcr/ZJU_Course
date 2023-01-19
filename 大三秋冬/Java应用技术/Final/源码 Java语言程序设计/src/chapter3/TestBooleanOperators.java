package chapter3;

import java.util.Scanner; 

public class TestBooleanOperators {
  public static void main(String[] args) {
    // Create a Scanner
    Scanner input = new Scanner(System.in);

    // Receive an input
    System.out.print("Enter an integer: ");
    int number = input.nextInt();

    System.out.println("Is " + number + " divisible by 2 and 3? " +
        ((number % 2 == 0) && (number % 3 == 0)));
   
    System.out.println("Is " + number + " divisible by 2 or 3? " +
        ((number % 2 == 0) || (number % 3 == 0)));

    System.out.println("Is " + number + 
         " divisible by 2 or 3, but not both? " +
         ((number % 2 == 0) ^ (number % 3 == 0))); 
  }
}
