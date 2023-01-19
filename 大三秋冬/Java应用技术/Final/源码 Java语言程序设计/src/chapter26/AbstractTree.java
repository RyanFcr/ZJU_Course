package chapter26;

public abstract class AbstractTree<E extends Comparable<E>>
    implements Tree<E> {
  /** Inorder traversal from the root*/
  public void inorder() {
  }

  /** Postorder traversal from the root */
  public void postorder() {
  }

  /** Preorder traversal from the root */
  public void preorder() {
  }

  /** Return true if the tree is empty */
  public boolean isEmpty() {
    return getSize() == 0;
  }

  /** Return an iterator to traverse elements in the tree */
  public java.util.Iterator iterator() {
    return null;
  }
}
