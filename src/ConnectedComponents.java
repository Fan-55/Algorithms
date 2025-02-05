import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ConnectedComponents {
  private final boolean[] marked;
  private final int[] id;
  private int count = 0;

  public ConnectedComponents(UndirectedGraph G) {
    marked = new boolean[G.V()];
    id = new int[G.V()];
    for (int s = 0; s < G.V(); s++) {
      if (!marked[s]) {
        dfs(G, s);
        count++;
      }
    }
  }

  private void dfs(UndirectedGraph G, int v) {
    marked[v] = true;
    id[v] = count;
    for(int w: G.adj(v)) {
      if (!marked[w]) dfs(G,w);
    }
  }

  public boolean connected(int v, int w) {
    return id[v] == id[w];
  }

  public int id(int v) {
    return id[v];
  }

  public int count() {
    return count;
  }

  public static void main(String[] args) {
    UndirectedGraph g = new UndirectedGraph(new In(args[0]));
    ConnectedComponents cc = new ConnectedComponents(g);
    int ccCount = cc.count();
    StdOut.println(ccCount + " connected components.");
    Bag<Integer>[] components = (Bag<Integer>[]) new Bag[ccCount];
    for (int i = 0; i < ccCount; i++) {
      components[i] = new Bag<Integer>();
    }
    for (int i = 0; i < g.V(); i++) {
      components[cc.id(i)].add(i);
    }
    for (int i = 0; i < ccCount; i++) {
      for(int w: components[i]) {
        StdOut.print(w+ " ");
      }
      StdOut.println();
    }
  }
}
