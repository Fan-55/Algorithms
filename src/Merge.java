public class Merge {
  public static void merge(Comparable[] a, int lo, int mid, int hi) {
    // Merge a[lo..mid] with a[mid+1..hi]
    int i = lo;
    int j = mid + 1;
    Comparable[] aux = new Comparable[hi - lo + 1];
    // Copy a[lo..hi] to aux array
    for (int k = lo; k <= hi; k++) {
      aux[k] = a[k];
    }
    // Merge back to a[lo..hi]
    for (int k = lo; k <= hi; k++) {
      if (i > mid) {
        a[k] = aux[j++];
      } else if (j > hi) {
        a[k] = aux[i++];
      } else if (less(aux[i], aux[j])) {
        a[k] = aux[i++];
      } else {
        a[k] = aux[j++];
      }
    }
  }

  public static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    int mid = lo + (hi - lo) / 2;
    sort(a, lo, mid);
    sort(a, mid + 1, hi);
    // 1st half and 2nd half are already sorted, skip merge
    if (a[mid].compareTo(a[mid + 1]) <= 0) return;
    merge(a, lo, mid, hi);
  }

  public static boolean less(Comparable a, Comparable b) {
    return a.compareTo(b) < 0;
  }
}
