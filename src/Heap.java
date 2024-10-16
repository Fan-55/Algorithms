public class Heap {
  public static void sort(Comparable[] a) {
    // heap construction
    int N = a.length;
    for (int k = N/2; k >=1; k--) {
      sink(a, k, N);
    }
    // sortdown
    while(N > 1) {
      exchange(a,1, N);
      N--;
      sink(a, 1, N);
    }
  }

  private static void sink(Comparable[] a, int k, int heapSize) {
    // The sink operation is only applicable to non-leaf nodes, which are located at indices less than or equal to heapSize/2.
    // Leaf nodes (indices greater than heapSize/2) do not have children and thus cannot sink further.
    while(2*k <= heapSize) {
      int j = 2*k;
      if (j+1 <= heapSize && less(a, j, j+1)) j++;
      if (!less(a, k, j)) break;
      exchange(a, k, j);
      k = j;
    }
  }

  // Index is off by one to support 1-based indexing
  private static void exchange(Comparable[] a, int i, int j) {
    Comparable temp = a[i - 1];
    a[i - 1] = a[j - 1];
    a[j -1] = temp;
  }

  private static boolean less(Comparable[] a, int i, int j) {
    return a[i - 1].compareTo(a[j - 1]) < 0;
  }

  private static void print(Comparable[] a) {
    for (Comparable c : a) {
      System.out.print(c + " ");
    }
    System.out.print('\n');
  }

  public static void main(String[] args) {
    // The input string
    String input = "SORTEXAMPLE";

    // Create an array of Comparable objects, one for each character in the string
    Comparable[] charArray = new Comparable[input.length()];

    // Populate the array with characters from the string
    for (int i = 0; i < input.length(); i++) {
      charArray[i] = input.charAt(i);
    }

    print(charArray);
    Heap.sort(charArray);
    print(charArray);
  }
}
