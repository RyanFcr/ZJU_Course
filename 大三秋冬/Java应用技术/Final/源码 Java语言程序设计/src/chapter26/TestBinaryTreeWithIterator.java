package chapter26;

public class TestBinaryTreeWithIterator {
  public static void main(String[] args) {
    BinaryTree<String> tree = new BinaryTree<String>();
    tree.insert("George");
    tree.insert("Michael");
    tree.insert("Tom");
    tree.insert("Adam");
    tree.insert("Jones");
    tree.insert("Peter");
    tree.insert("Daniel");

    java.util.Iterator iterator = tree.iterator();
    while (iterator.hasNext()) {
      System.out.println(((String)(iterator.next())).toUpperCase());
    }
  }
}
