package chapter14;

public class GenericSort {
  public static void main(String[] args) {
    // Create an Integer array
    Integer[] intArray = {new Integer(2), new Integer(4),
      new Integer(3)};

    // Create a Double array
    Double[] doubleArray = {new Double(3.4), new Double(1.3),
      new Double(-22.1)};

    // Create a Character array
    Character[] charArray = {new Character('a'),
      new Character('J'), new Character('r')};

    // Create a String array
    String[] stringArray = {"Tom", "John", "Fred"};

    // Sort the arrays
    sort(intArray);
    sort(doubleArray);
    sort(charArray);
    sort(stringArray);

    // Display the sorted arrays
    System.out.print("Sorted Integer objects: ");
    printList(intArray);
    System.out.print("Sorted Double objects: ");
    printList(doubleArray);
    System.out.print("Sorted Character objects: ");
    printList(charArray);
    System.out.print("Sorted String objects: ");
    printList(stringArray);
  }

  /** Sort an array of comparable objects */
  public static void sort(Comparable[] list) {
    Comparable currentMin;
    int currentMinIndex;

    for (int i = 0; i < list.length - 1; i++) {
      // Find the maximum in the list[0..i]
      currentMin = list[i];
      currentMinIndex = i;

      for (int j = i + 1; j < list.length; j++) {
        if (currentMin.compareTo(list[j]) > 0) {
          currentMin = list[j];
          currentMinIndex = j;
        }
      }

      // Swap list[i] with list[currentMinIndex] if necessary;
      if (currentMinIndex != i) {
        list[currentMinIndex] = list[i];
        list[i] = currentMin;
      }
    }
  }

  /** Print an array of objects */
  public static void printList(Object[] list) {
    for (int i = 0; i < list.length; i++)
      System.out.print(list[i] + " ");
    System.out.println();
  }
}
