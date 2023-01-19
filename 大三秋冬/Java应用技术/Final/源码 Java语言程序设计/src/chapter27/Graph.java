package chapter27;

public interface Graph<V> {
  /** Return the number of vertices in the graph */
  public int getSize();

  /** Return the vertices in the graph */
  public java.util.List<V> getVertices();

  /** Return the object for the specified vertex index */
  public V getVertex(int index);

  /** Return the index for the specified vertex object */
  public int getIndex(V v);

  /** Return the neighbors of vertex with the specified index */
  public java.util.List<Integer> getNeighbors(int index);

  /** Return the degree for a specified vertex */
  public int getDegree(int v);

  /** Return the adjacency matrix */
  public int[][] getAdjacencyMatrix();

  /** Print the adjacency matrix */
  public void printAdjacencyMatrix();

  /** Print the edges */
  public void printEdges();

  /** Obtain a depth-first search tree */
  public AbstractGraph<V>.Tree dfs(int v);

  /** Obtain a breadth-first search tree */
  public AbstractGraph<V>.Tree bfs(int v);
  
  /** Return a Hamiltonian path from the specified vertex
   * Return null if the graph does not contain a Hamiltonian path */
  public java.util.List<Integer> getHamiltonianPath(V vertex);
  
  /** Return a Hamiltonian path from the specified vertex label
   * Return null if the graph does not contain a Hamiltonian path */
  public java.util.List<Integer> getHamiltonianPath(int inexe);
}
