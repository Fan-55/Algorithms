import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>{
  private int size;
  private class Node {
    Item item;
    Node next;
  }
  private Node first;
  private Node last;
  public int size() {return size;}
  public boolean isEmpty() {return first == null;}
  public void enqueue(Item item) {
    /// Add new node to the end
    Node oldLast = last;
    last = new Node();
    last.item = item;
    if (isEmpty()) {
      first = last;
    } else {
      oldLast.next = last;
    }
    size++;
  }
  public Item dequeue() {
    /// Remove first node
    Item poppedItem = first.item;
    first = first.next;
    if (isEmpty()) {
      last = first;
    }
    size--;
    return poppedItem;
  }
  private class ListIterator implements Iterator<Item> {
    private Node current = first;
    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public Item next() {
      Item currentItem = current.item;
      current = current.next;
      return currentItem;
    }
  }

  public Iterator<Item> iterator() {
    return new ListIterator();
  }
  public static void main(String[] args)
  { // Create a queue and enqueue/dequeue strings.
    Queue<String> q = new Queue<String>();
    while (!StdIn.isEmpty())
    {
      String item = StdIn.readString();
      if (!item.equals("-"))
        q.enqueue(item);
      else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
    }
    StdOut.println("(" + q.size() + " left on queue)");
  }
}
