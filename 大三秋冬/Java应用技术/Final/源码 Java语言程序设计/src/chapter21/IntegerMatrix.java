package chapter21;

public class IntegerMatrix extends GenericMatrix<Integer> {
  /** Implement the add method for adding two matrix elements */
  protected Integer add(Integer o1, Integer o2) {
    return o1 + o2;
  }

  /** Implement the multiply method for multiplying two
     matrix elements */
  protected Integer multiply(Integer o1, Integer o2) {
    return o1 * o2;
  }

  /** Implement the zero method to specify zero for Integer */
  protected Integer zero() {
    return 0;
  }
}
