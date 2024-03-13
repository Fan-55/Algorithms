import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnion {
  public QuickUnion(int N) {
    count = N;
    id = new int[count];
    for (int i =0; i<N; i++) {
      id[i] = i;
    }
  }
  void union(int p, int q) {
    int root_p = find(p);
    int root_q = find(q);
    if (root_p == root_q) return;
    id[root_p] = root_q;
    count--;
  }
  /// p is site name range from 0 to N-1
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
  private int count;

  public static void main(String[] args) {
    int N = StdIn.readInt();
    QuickUnion uf = new QuickUnion(N);
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
