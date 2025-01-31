import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstPath {
  private final boolean[] marked;
  private int[] edgeTo;
  private final int source;

  public DepthFirstPath(UndirectedGraph G, int s) {
    marked = new boolean[G.V()];
    edgeTo = new int[G.V()];
    this.source = s;
    dfs(G,s);
  }

  private void dfs(UndirectedGraph G, int v) {
      marked[v] = true;
      for (int w: G.adj(v)) {
        if (!marked[w]) {
          edgeTo[w] = v;
          dfs(G, w);
        }
      }
  }

  public boolean hasPathTo(int v) {
    return marked[v];
  }

  public Iterable<Integer> pathTo(int v) {
    if (!hasPathTo(v)) return null;
    Stack<Integer> path = new Stack<Integer>();
    for (int x = v; x != this.source; x = edgeTo[x]) {
      path.push(x);
    }
    path.push(this.source);
    return path;
  }

  // Test client
  public static void main(String[] args) {
    UndirectedGraph G = new UndirectedGraph(new In(args[0]));
    int s = Integer.parseInt(args[1]);
    DepthFirstPath search = new DepthFirstPath(G, s);
    for (int v = 0; v < G.V(); v++)
    {
      StdOut.print(s + " to " + v + ": ");
      if (search.hasPathTo(v))
        for (int x : search.pathTo(v))
          if (x == s) StdOut.print(x);
          else StdOut.print("-" + x);
      StdOut.println();
    }
  }
}
