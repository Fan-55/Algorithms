public class MaxPQ<Key extends Comparable<Key>> {
  private Key[] pq;
  private int N = 0; // size of heap

  public MaxPQ(int maxHeapSize) {
    pq = (Key[]) new Comparable[maxHeapSize + 1];
  }

  public void insert(Key newKey) {
    N++;
    pq[N] = newKey;
    swim(N);
  }

  public Key delMax() {
    Key max = pq[1];
    exchange(1, N);
    pq[N] = null;
    N--;
    sink(1);
    return max;
  }

  private void swim(int k) {
    // Parent of pq[k] is at position k/2. Swim up if parent is smaller.
    while (k > 1 && less(k/2, k)) {
      exchange(k/2, k);
      k = k/2;
    }
  }

  private void sink(int k) {
    // The sink operation is only applicable to non-leaf nodes, which are located at indices less than or equal to N/2.
    // Leaf nodes (indices greater than N/2) do not have children and thus cannot sink further.
    while(2*k <= N) {
      int j = 2*k;
      if (j+1 <= N && less(j, j+1)) j++;
      if (!less(k, j)) break;
      exchange(k, j);
      k = j;
    }
  }

  private void exchange(int i, int j) {
    Key temp = pq[i];
    pq[i] = pq[j];
    pq[j] = temp;
  }

  private boolean less(int i, int j) {
    return pq[i].compareTo(pq[j]) < 0;
  }

  private void print() {
    for (int i = 1; i< pq.length; i++) {
      System.out.print(pq[i].toString() + ' ');
    }
      System.out.print('\n');
  }

  public static void main(String[] args) {
    MaxPQ<Integer> maxPq = new MaxPQ<>(10);
    for (int i = 0; i < 10; i++) {
      maxPq.insert(i + 1);
    }
    maxPq.print();

    Integer max = maxPq.delMax();
    System.out.println(max);
    max = maxPq.delMax();
    System.out.println(max);
    max = maxPq.delMax();
    System.out.println(max);
  }
}
