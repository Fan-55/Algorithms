import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class BreadthFirstPath {
  private final boolean[] marked;
  private final int[] edgeTo;
  private final int source;

  public BreadthFirstPath(UndirectedGraph G, int s) {
    marked = new boolean[G.V()];
    edgeTo = new int[G.V()];
    this.source = s;
    bfs(G, s);
  }

  private void bfs(UndirectedGraph G, int s) {
    marked[s] = true;
    Queue<Integer> queue = new Queue<>();
    queue.enqueue(s);
    while(!queue.isEmpty()) {
      int v = queue.dequeue();
      for (int w : G.adj(v)) {
        if (!marked[w]) {
          marked[w] = true;
          edgeTo[w] = v;
          queue.enqueue(w);
        }
      }
    }
  }

  public boolean hasPathTo(int v) {
    return marked[v];
  }

  public Iterable<Integer> pathTo(int v) {
    if (!this.hasPathTo(v)) return null;
    Stack<Integer> path = new Stack<>();
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
    BreadthFirstPath search = new BreadthFirstPath(G, s);
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
