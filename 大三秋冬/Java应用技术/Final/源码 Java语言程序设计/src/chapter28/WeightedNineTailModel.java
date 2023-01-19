package chapter28;

import java.util.*;
import chapter27.NineTailModel;

public class WeightedNineTailModel extends NineTailModel {
  /** Construct a model */
  public WeightedNineTailModel() {
    // Create edges
    List<WeightedEdge> edges = getEdges();
    
    // Create a graph
    WeightedGraph<Integer> graph = new WeightedGraph<Integer>(
      edges, NUMBER_OF_NODES); 

    // Obtain a BSF tree rooted at the target node
    tree = graph.getShortestPath(511);
  }

  /** Create all edges for the graph */
  private List<WeightedEdge> getEdges() {
    // Store edges
    List<WeightedEdge> edges = new ArrayList<WeightedEdge>(); 

    for (int u = 0; u < NUMBER_OF_NODES; u++) {
      for (int k = 0; k < 9; k++) {
        char[] node = getNode(u); // Get the node for vertex u
        if (node[k] == 'H') {
          int v = getFlippedNode(node, k);
          int numberOfFlips = getNumberOfFlips(u, v);
          
          // Add edge (v, u) for a legal move from node u to node v
          edges.add(new WeightedEdge(v, u, numberOfFlips));
        }
      }
    }

    return edges;
  }
  
  private static int getNumberOfFlips(int u, int v) {
    char[] node1 = getNode(u);
    char[] node2 = getNode(v);

    int count = 0; // Count the number of different cells
    for (int i = 0; i < node1.length; i++)
      if (node1[i] != node2[i]) count++;

    return count;
  }

  public int getNumberOfFlips(int u) {
    return 
      ((WeightedGraph<Integer>.ShortestPathTree)tree).getCost(u);
  }
}
