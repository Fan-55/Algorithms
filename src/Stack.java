import java.util.Iterator;

public class Stack<Item> implements Iterable<Item>{
  private Item[] items = (Item[]) new Object[1];

  private int size = 0;

  public boolean isEmpty() { return size == 0; }

  public int size() { return size; }

  public void push(Item newItem) {
    if (size == items.length) {
      resize(2* items.length);
    }
    /// read the size first then increment
    items[size++] = newItem;
  }

  public Item pop() {
    /// decrement first then read the value
    Item poppedItem = items[--size];
    /// avoid loitering
    items[size] = null;
    if (size > 0 && size == items.length/4) {
      resize(items.length / 2);
    }
    return poppedItem;
  }

  private void resize(int newSize) {
    Item[] newItems = (Item[]) new Object[newSize];
    for (int i = 0; i < size; i++) {
      newItems[i] = items[i];
    }
    items = newItems;
  }

  @Override
  public Iterator<Item> iterator() {
    return new ReverseArrayIterator();
  }

  private class ReverseArrayIterator implements Iterator<Item> {
    private int i = size - 1;
    @Override
    public boolean hasNext() {
      return i > 0;
    }

    @Override
    public Item next() {
      return items[i--];
    }
  }
}
