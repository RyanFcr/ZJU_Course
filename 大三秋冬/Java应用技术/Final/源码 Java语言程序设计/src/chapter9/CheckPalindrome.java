package chapter9;

import java.util.Scanner;

public class CheckPalindrome {
  /** Main method */
  public static void main(String[] args) {
    // Create a Scanner
    Scanner input = new Scanner(System.in);

    // Prompt the user to enter a string
    System.out.print("Enter a string: ");
    String s = input.nextLine();

    if (isPalindrome(s))
      System.out.println(s + " is a palindrome");
    else
      System.out.println(s + " is not a palindrome");
  }

  /** Check if a string is a palindrome */
  public static boolean isPalindrome(String s) {
    // The index of the first character in the string
    int low = 0;

    // The index of the last character in the string
    int high = s.length() - 1;

    while (low < high) {
      if (s.charAt(low) != s.charAt(high))
        return false; // Not a palindrome

      low++;
      high--;
    }

    return true; // The string is a palindrome
  }
}
