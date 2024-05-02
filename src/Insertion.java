import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Insertion {
  public static void sort(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      for (int j = i; j > 0; j--) {
        if (less(a[j], a[j-1])) {
          exchange(a, j, j-1);
        } else {
          break;
        }
      }
    }
  }

  private static boolean less(Comparable a, Comparable b) {
    return a.compareTo(b) < 0;
  }

  private static void exchange(Comparable[] a, int i, int j) {
    Comparable temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++) {
      /**
       * If the item with higher index is less than the item with lower
       * index, it is not sorted.
       */
      if (less(a[i], a[i - 1])) {
        return false;
      }
    }
    return true;
  }

  private static void show(Comparable[] a) {
    /// Print the array, on a single line.
    for (Comparable comparable : a) {
      StdOut.print(comparable + " ");
    }
    StdOut.println();
  }

  public static void main(String[] args) {
    String[] a = StdIn.readAllStrings();
    sort(a);
    assert isSorted(a);
    show(a);
  }
}
