package chapter27;

import java.util.Scanner;

public class NineTail {
  public static void main(String[] args) {
    // Prompt the user to enter nine coins H and T's
    System.out.print("Enter an initial nine coin H’s and T's: ");
    Scanner input = new Scanner(System.in);
    String s = input.nextLine(); 
    char[] initialNode = s.toCharArray();

    NineTailModel model = new NineTailModel();
    java.util.List<Integer> path =
      model.getShortestPath(NineTailModel.getIndex(initialNode));

    System.out.println("The steps to flip the coins are ");
    for (int i = 0; i < path.size(); i++)
      NineTailModel.printNode(
        NineTailModel.getNode(path.get(i).intValue()));    
  }
}
