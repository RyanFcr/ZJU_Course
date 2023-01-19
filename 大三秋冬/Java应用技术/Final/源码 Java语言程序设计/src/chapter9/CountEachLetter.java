package chapter9;

import java.util.Scanner;

public class CountEachLetter {
  /** Main method */
  public static void main(String[] args) {
    // Create a Scanner
    Scanner input = new Scanner(System.in);

    // Prompt the user to enter a string
    System.out.print("Enter a string: ");
    String s = input.nextLine();

    // Invoke the countLetters method to count each letter
    int[] counts = countLetters(s.toLowerCase());

    // Display results
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] != 0)
        System.out.println((char)('a' + i) + " appears  " +
          counts[i] + ((counts[i] == 1) ? " time" : " times"));
    }
  }

  /** Count each letter in the string */
  public static int[] countLetters(String s) {
    int[] counts = new int[26];

    for (int i = 0; i < s.length(); i++) {
      if (Character.isLetter(s.charAt(i)))
        counts[s.charAt(i) - 'a']++;
    }

    return counts;
  }
}
