package chapter11;

public class MyStack {
  private java.util.ArrayList list = new java.util.ArrayList();

  public boolean isEmpty() {
    return list.isEmpty();
  }

  public int getSize() {
    return list.size();
  }

  public Object peek() {
    return list.get(getSize() - 1);
  }

  public Object pop() {
    Object o = list.get(getSize() - 1);
    list.remove(getSize() - 1);
    return o;
  }

  public void push(Object o) {
    list.add(o);
  }

  public int search(Object o) {
    return list.lastIndexOf(o);
  }

  /** Override the toString in the Object class */
  public String toString() {
    return "stack: " + list.toString();
  }
}
