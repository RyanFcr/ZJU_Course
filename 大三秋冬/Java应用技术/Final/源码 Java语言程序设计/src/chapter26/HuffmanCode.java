package chapter26;

import java.util.Scanner;
import chapter24.Heap;

public class HuffmanCode {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a text: ");
    String text = input.nextLine();
    
    int[] counts = getCharacterFrequency(text); // Count frequency

    System.out.printf("%-15s%-15s%-15s%-15s\n",
      "ASCII Code", "Character", "Frequency", "Code");  
    
    Tree tree = getHuffmanTree(counts); // Create a Huffman tree
    String[] codes = getCode(tree.root); // Get codes
        
    for (int i = 0; i < codes.length; i++)
      if (counts[i] != 0) // (char)i is not in text if counts[i] is 0
        System.out.printf("%-15d%-15s%-15d%-15s\n", 
          i, (char)i + "", counts[i], codes[i]);
  }
  
  /** Get Huffman codes for the characters 
   * This method is called once after a Huffman tree is built
   */
  public static String[] getCode(Tree.Node root) {
    if (root == null) return null;    
    String[] codes = new String[2 * 128];
    assignCode(root, codes);
    return codes;
  }
  
  /* Recursively get codes to the leaf node */
  private static void assignCode(Tree.Node root, String[] codes) {
    if (root.left != null) {
      root.left.code = root.code + "0";
      assignCode(root.left, codes);
      
      root.right.code = root.code + "1";
      assignCode(root.right, codes);
    }
    else {
      codes[(int)root.element] = root.code;
    }
  }
  
  /** Get a Huffman tree from the codes */  
  public static Tree getHuffmanTree(int[] counts) {
    // Create a heap to hold trees
    Heap<Tree> heap = new Heap<Tree>(); // Defined in Listing 24.10
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] > 0)
        heap.add(new Tree(counts[i], (char)i)); // A leaf node tree
    }
    
    while (heap.getSize() > 1) { 
      Tree t1 = heap.remove(); // Remove the smallest weight tree
      Tree t2 = heap.remove(); // Remove the next smallest weight 
      heap.add(new Tree(t1, t2)); // Combine two trees
    }

    return heap.remove(); // The final tree
  }
  
  /** Get the frequency of the characters */
  public static int[] getCharacterFrequency(String text) {
    int[] counts = new int[256]; // 256 ASCII characters
    
    for (int i = 0; i < text.length(); i++)
      counts[(int)text.charAt(i)]++; // Count the character in text
    
    return counts;
  }
  
  /** Define a Huffman coding tree */
  public static class Tree implements Comparable<Tree> {
    Node root; // The root of the tree

    /** Create a tree with two subtrees */
    public Tree(Tree t1, Tree t2) {
      root = new Node();
      root.left = t1.root;
      root.right = t2.root;
      root.weight = t1.root.weight + t2.root.weight;
    }
    
    /** Create a tree containing a leaf node */
    public Tree(int weight, char element) {
      root = new Node(weight, element);
    }
    
    /** Compare trees based on their weights */
    public int compareTo(Tree o) {
      if (root.weight < o.root.weight) // Purposely reverse the order
        return 1;
      else if (root.weight == o.root.weight)
        return 0;
      else
        return -1;
    }

    public class Node {
      char element; // Stores the character for a leaf node
      int weight; // weight of the subtree rooted at this node
      Node left; // Reference to the left subtree
      Node right; // Reference to the right subtree
      String code = ""; // The code of this node from the root

      /** Create an empty node */
      public Node() {
      }
      
      /** Create a node with the specified weight and character */
      public Node(int weight, char element) {
        this.weight = weight;
        this.element = element;
      }
    }
  }  
}
