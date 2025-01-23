import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class UndirectedGraph {
  private final int V;              // number of vertices
  private int E;                    // number of edges
  private final Bag<Integer>[] adj; // adjacency lists

  public UndirectedGraph(int V) {
    this.V = V;
    this.E = 0;
    this.adj = (Bag<Integer>[]) new Bag[V];
    for (int i = 0; i < V; i++) {
      this.adj[i] = new Bag<Integer>();
    }
  }

  public UndirectedGraph(In in) {
    this(in.readInt());
    int E = in.readInt();
    for (int i = 0; i < E; i++) {
      int v = in.readInt();
      int w = in.readInt();
      this.addEdge(v,w);
    }
  }

  public int V() { return this.V; }

  public int E() { return this.E; }

  public void addEdge(int v, int w) {
    this.adj[v].add(w);
    this.adj[w].add(v);
    this.E++;
  }

  public Iterable<Integer> adj(int v) {
    return this.adj[v];
  }
}
