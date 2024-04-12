import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.ListIterator;

public class LinkedListStack<Item> implements Iterable<Item> {
  private Node first;
  private int size;
  public void push(Item item) {
    Node newNode = new Node();
    newNode.item = item;
    newNode.next = first;
    first = newNode;
    size++;
  }
  public boolean isEmpty() {
    return size == 0; /// or first === null
  }
  public int size() {
    return size;
  }
  public Item pop() {
    Item poppedItem = first.item;
    first = first.next;
    size--;
    return poppedItem;
  }
  private class Node {
    Item item;
    Node next;
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
  { // Create a stack and push/pop strings as directed on StdIn.
    LinkedListStack<String> s = new LinkedListStack<String>();
    while (!StdIn.isEmpty())
    {
      String item = StdIn.readString();
      if (!item.equals("-"))
        s.push(item);
      else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
    }
    StdOut.println("(" + s.size() + " left on stack)");
  }
}
