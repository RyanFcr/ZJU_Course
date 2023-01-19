package chapter28;

import chapter27.AbstractGraph;

public class WeightedEdge extends AbstractGraph.Edge
    implements Comparable<WeightedEdge> {
  public int weight; // The weight on edge (u, v)

  /** Create a weighted edge on (u, v) */
  public WeightedEdge(int u, int v, int weight) {
    super(u, v);
    this.weight = weight;
  }

  /** Compare two edges on weights */
  public int compareTo(WeightedEdge edge) {
    if (weight > edge.weight) 
      return 1;
    else if (weight == edge.weight) {
      return 0;
    }
    else {
      return -1;
    }
  }
}
