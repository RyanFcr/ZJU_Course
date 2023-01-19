package chapter27;

public class TestGraph {
  public static void main(String[] args) {
    String[] vertices = {"Seattle", "San Francisco", "Los Angeles",
      "Denver", "Kansas City", "Chicago", "Boston", "New York",
      "Atlanta", "Miami", "Dallas", "Houston"};

    // Edge array for graph in Figure 27.1
    int[][] edges = {
      {0, 1}, {0, 3}, {0, 5},
      {1, 0}, {1, 2}, {1, 3},
      {2, 1}, {2, 3}, {2, 4}, {2, 10},
      {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
      {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
      {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
      {6, 5}, {6, 7},
      {7, 4}, {7, 5}, {7, 6}, {7, 8},
      {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
      {9, 8}, {9, 11},
      {10, 2}, {10, 4}, {10, 8}, {10, 11},
      {11, 8}, {11, 9}, {11, 10}
    };

    Graph<String> graph1 = 
      new UnweightedGraph<String>(edges, vertices);
    System.out.println("The number of vertices in graph1: " 
      + graph1.getSize());
    System.out.println("The vertex with index 1 is " 
      + graph1.getVertex(1));
    System.out.println("The index for Miami is " + 
      graph1.getIndex("Miami"));
    System.out.println("The edges for graph1:");
    graph1.printEdges();
    System.out.println("Adjacency matrix for graph1:");
    graph1.printAdjacencyMatrix();

    // List of Edge objects for graph in Figure 27.3(a)
    String[] names = {"Peter", "Jane", "Mark", "Cindy", "Wendy"};
    java.util.ArrayList<AbstractGraph.Edge> edgeList
      = new java.util.ArrayList<AbstractGraph.Edge>();
    edgeList.add(new AbstractGraph.Edge(0, 2));
    edgeList.add(new AbstractGraph.Edge(1, 2));
    edgeList.add(new AbstractGraph.Edge(2, 4));
    edgeList.add(new AbstractGraph.Edge(3, 4));
    // Create a graph with 5 vertices
    Graph<String> graph2 = new UnweightedGraph<String>
      (edgeList, java.util.Arrays.asList(names));
    System.out.println("The number of vertices in graph2: " 
      + graph2.getSize());
    System.out.println("The edges for graph2:");
    graph2.printEdges();
    System.out.println("\nAdjacency matrix for graph2:");
    graph2.printAdjacencyMatrix();
    
    for (int i = 0; i < 5; i++)
      System.out.println("vertex " + i + ": " + graph2.getVertex(i));
  }
}
