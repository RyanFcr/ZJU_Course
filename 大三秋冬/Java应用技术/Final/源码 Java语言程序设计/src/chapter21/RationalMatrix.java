package chapter21;

import chapter14.Rational;

public class RationalMatrix extends GenericMatrix<Rational> {
  /** Implement the add method for adding two rational elements */
  protected Rational add(Rational r1, Rational r2) {
    return r1.add(r2);
  }

  /** Implement the multiply method for multiplying
     two rational elements */
  protected Rational multiply(Rational r1, Rational r2) {
    return r1.multiply(r2);
  }

  /** Implement the zero method to specify zero for Rational */
  protected Rational zero() {
    return new Rational(0,1);
  }
}
