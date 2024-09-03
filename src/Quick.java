import edu.princeton.cs.algs4.StdRandom;

public class Quick {
  public static void sort(Comparable[] a) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length - 1);
  }

  private static void sort(Comparable[] a, int lo, int hi) {
    if (lo >= hi) return;
    int j = partition2(a, lo, hi);
    sort(a, lo, j-1);
    sort(a, j+1, hi);
  }

  /// This is implemented based on Tim Roughgarden's pseudocode
  private static int partition1(Comparable[] a, int lo, int hi) {
    int i = lo + 1;
    Comparable pivot = a[lo];
    for (int j = lo + 1; j <= hi; j++) {
      if (less(a[j], pivot)) {
        /// swap a[j] and a[i]
        Comparable temp = a[j];
        a[j] = a[i];
        a[i] = temp;
        i++;
      }
    }
    /// Move pivot to its rightful place (i - 1)
    Comparable temp = a[i - 1];
    a[i - 1] = pivot;
    a[lo] = temp;
    return i - 1;
  }

  /// This is Robert Sedgewick's version of partition
  private static int partition2(Comparable[] a, int lo, int hi) {
    int i = lo + 1;
    int j = hi;
    Comparable pivot = a[lo];
    while(true) {
      while(less(a[i], pivot)) {
        i++;
        if (i == hi) break;
      }
      while(less(pivot, a[j])) {
        j--;
        if (j == lo + 1) break;
      }
      if (i >= j) break;
      swap(a, i, j);
    }
    swap(a, lo, j);
    return j;
  }

  private static void swap(Comparable[] a, int i, int j) {
    Comparable temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  private static boolean less(Comparable a, Comparable b) {
    return a.compareTo(b) < 0;
  }

  public static void main(String[] args) {
    Comparable<Integer>[] a = new Comparable[]{5, 8, 11, 9, 2, 18};
    sort(a);
    for (Comparable<Integer> ele: a) {
      System.out.println(ele);
    }
  }
}
