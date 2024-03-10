import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFind {
  public QuickFind(int N) {
    count = N;
    id = new int[count];
    for (int i =0; i<N; i++) {
      id[i] = i;
    }
  }
  void union(int p, int q) {
    if (connected(p,q)) return;
    int component_id_of_p = find(p);
    int component_id_of_q = find(q);
    for(int i = 0; i < id.length; i++) {
      if (id[i] == component_id_of_p)  {
        id[i] = component_id_of_q;
      }
    }
    count--;
  }
  int find(int p) {
    return id[p];
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
    QuickFind uf = new QuickFind(N);
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
