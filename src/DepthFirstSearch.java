import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstSearch {
  private final boolean[] marked;
  private int count;

  public DepthFirstSearch(UndirectedGraph G, int s) {
    marked = new boolean[G.V()];
    dfs(G, s);
  }

  private void dfs(UndirectedGraph G, int v) {
    marked[v] = true;
    count++;
    for (int w : G.adj(v)) {
      if (!marked[w]) dfs(G, w);
    }
  }

  public boolean marked(int v) {    // check if v is connected to s
    return marked[v];
  }

  public int count() {              // number of vertices connected to s
    return count;
  }

  // Test client
  public static void main(String[] args) {
    UndirectedGraph G = new UndirectedGraph(new In(args[0]));
    int s = Integer.parseInt(args[1]);
    DepthFirstSearch search = new DepthFirstSearch(G, s);

    for (int i = 0; i < G.V(); i++) {
      if (search.marked(i)) {
        StdOut.print(i + " ");
      }
    }
    StdOut.println();
    if (search.count() != G.V()) {
      StdOut.println("NOT connected");
    }
  }
}
