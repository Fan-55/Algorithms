import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnion {
  public WeightedQuickUnion(int N) {
    count = N;
    id = new int[count];
    sz = new int[count];
    for (int i = 0; i < N; i++) {
      id[i] = i;
      sz[i] = 1;
    }
  }
  void union(int p, int q) {
    int root_p = find(p);
    int root_q = find(q);
    if (root_p == root_q) return;
    if (sz[p] < sz[q]) {
      // connect smaller tree to the larger
      id[root_p] = root_q;
      // only the sizes of roots are needed, so we only need to update the size of bigger tree
      sz[root_q] += sz[root_p];
    } else {
      id[root_q] = root_p;
      sz[root_p] += sz[root_q];
    }
    count--;
  }
  int find(int p) {
    while(p != id[p]) {
      p = id[p];
    }
    return p;
  }
  boolean connected(int p, int q) {
    return find(p) == find(q);
  }
  int count() {
    return count;
  }
  private int[] id;
  private int[] sz;
  private int count; /// number of connected components

  public static void main(String[] args) {
    int N = StdIn.readInt();
    WeightedQuickUnion uf = new WeightedQuickUnion(N);
    while(!StdIn.isEmpty()) {
      int p = StdIn.readInt();
      int q = StdIn.readInt();
      if (uf.connected(p,q))continue;
      uf.union(p,q);
      StdOut.println(p + " " + q);
      StdOut.println(uf.count() + " components");
    }
  }
}
