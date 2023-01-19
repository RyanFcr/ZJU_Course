package chapter25;

public class MyQueue<E> {
  private MyLinkedList<E> list = new MyLinkedList<E>();

  public void enqueue(E e) {
    list.addLast(e);
  }

  public E dequeue() {
    return list.removeFirst();
  }

  public int getSize() {
    return list.size();
  }

  public String toString() {
    return "Queue: " + list.toString();
  }
}
