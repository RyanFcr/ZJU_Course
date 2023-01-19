package chapter7;

import java.util.Scanner;

public class CheckSudokuSolution { 
  public static void main(String[] args) {
    // Read a Sudoku solution
    int[][] grid = readASolution();

    System.out.println(isValid(grid) ? "Valid solution" :
      "Invalid solution");
  }
  
  /** Read a Sudoku solution from the console */
  public static int[][] readASolution() {
    // Create a Scanner
    Scanner input = new Scanner(System.in);

    System.out.println("Enter a Sudoku puzzle solution:");
    int[][] grid = new int[9][9];
    for (int i = 0; i < 9; i++) 
      for (int j = 0; j < 9; j++)
        grid[i][j] = input.nextInt();
    
    return grid;
  }

  /** Check whether a solution is valid */
  public static boolean isValid(int[][] grid) {
    // Check whether each row has numbers 1 to 9
    for (int i = 0; i < 9; i++)
      if (!is1To9(grid[i])) // If grid[i] does not contain 1 to 9
        return false;
    
    // Check whether each column has numbers 1 to 9
    for (int j = 0; j < 9; j++) {
      // Obtain a column in the one-dimensional array
      int[] column = new int[9]; 
      for (int i = 0; i < 9; i++) {
        column[i] = grid[i][j];
      }
      
      if (!is1To9(column)) // If column does not contain 1 to 9
        return false;
    }
    
    // Check whether each 3 by 3 box has numbers 1 to 9
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        // The starting element in a small 3 by 3 box
        int k = 0;
        int[] list = new int[9]; // Get all number in the box to list
        for (int row = i * 3; row < i * 3 + 3; row ++)
          for (int column = j * 3; column < j * 3 + 3; column++) 
            list[k++] = grid[row][column];
            
        if (!is1To9(list)) // If list does not contain 1 to 9
          return false;
      }
    }

    return true; // The fixed cells are valid
  }
  
  /** Check whether the one-dimensional array contains 1 to 9 */
  public static boolean is1To9(int[] list) {
    // Make a copy of the array
    int[] temp = new int[list.length];
    System.arraycopy(list, 0, temp, 0, list.length);
    
    // Sort the array
    java.util.Arrays.sort(temp);
    
    // Check if list contains 1, 2, 3, ..., 9
    for (int i = 0; i < 9; i++)
      if (temp[i] != i + 1)  
        return false;
    
    return true; // The list contains exactly 1 to 9
  }  
}
