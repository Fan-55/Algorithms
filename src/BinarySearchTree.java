import edu.princeton.cs.algs4.StdOut;

public class BinarySearchTree <Key extends Comparable<Key>, Value>{

  private Node root;

  private class Node {
    private Key key;
    private Value val;
    private Node left;
    private Node right;
    private int N; // # of nodes in the subtree rooted here

    public Node(Key key, Value val, int N) {
      this.key = key;
      this.val = val;
      this.N = N;
    }
  }

  public int size() {
    return size(root);
  }

  private int size (Node node) {
    return node == null ? 0 : node.N;
  }

  public Value get(Key searchKey) {
    return get(root, searchKey);
  }

  private Value get(Node subtreeRoot, Key searchKey) {
    if (subtreeRoot == null) return null;
    int cmp = searchKey.compareTo(subtreeRoot.key);
    if (cmp < 0) {
      return get(subtreeRoot.left, searchKey);
    } else if (cmp > 0) {
      return get(subtreeRoot.right, searchKey);
    } else {
      return subtreeRoot.val;
    }
  }


  public void put(Key key, Value val) {
    root =  put(root, key, val);
  }

  private Node put(Node subtreeRoot, Key key, Value val) {
    if (subtreeRoot == null) return new Node(key, val, 1);
    int cmp = key.compareTo(subtreeRoot.key);
    if (cmp < 0) {
      subtreeRoot.left = put(subtreeRoot.left, key, val);
    } else if (cmp > 0) {
      subtreeRoot.right = put(subtreeRoot.right, key, val);
    } else {
      subtreeRoot.val = val;
    }
    subtreeRoot.N = 1 + size(subtreeRoot.left) + size(subtreeRoot.right);
    return subtreeRoot;
  }

  public Key min() {
    return min(root).key;
  }

  private Node min(Node subtreeRoot) {
    if (subtreeRoot.left == null) return subtreeRoot;
    return min(subtreeRoot.left);
  }

  public Key max() {
    return max(root).key;
  }

  private Node max(Node subtreeRoot) {
    if (subtreeRoot.right == null) return subtreeRoot;
    return max(subtreeRoot.right);
  }

  public Key floor(Key key) {
    return floor(root, key).key;
  }

  private Node floor(Node subtreeRoot, Key key) {
    if (subtreeRoot == null) return null;
    int cmp = key.compareTo(subtreeRoot.key);
    if (cmp < 0) {
      return floor(subtreeRoot.left, key);
    } else if (cmp == 0) {
      return subtreeRoot;
    }
    Node floorNode = floor(subtreeRoot.right, key);
    return floorNode == null ? subtreeRoot: floorNode;
  }

  public Key ceiling(Key key) {
    return ceiling(root, key).key;
  }

  private Node ceiling(Node subtreeRoot, Key key) {
    if (subtreeRoot == null) return null;
    int cmp = key.compareTo(subtreeRoot.key);
    if (cmp > 0) {
      return ceiling(subtreeRoot.right, key);
    } else if (cmp == 0) {
      return subtreeRoot;
    }
    Node ceilingNode = ceiling(subtreeRoot.left, key);
    return ceilingNode == null ? subtreeRoot: ceilingNode;
  }

  public Key select(int k) {
    return select(root,k).key;
  }

  private Node select(Node subtreeRoot, int k) {
    if (subtreeRoot == null) return null;
    int leftSubtreeSize = size(subtreeRoot.left);
    if (leftSubtreeSize == k) {
      return subtreeRoot;
    } else if (leftSubtreeSize > k) {
      return select(subtreeRoot.left, k);
    } else {
      return select(subtreeRoot.right, k-leftSubtreeSize-1);
    }
  }

  public int rank(Key key) {
    return rank(root, key);
  }

  private int rank(Node subtreeRoot, Key key) {
    if (subtreeRoot == null) return 0;
    int cmp = key.compareTo(subtreeRoot.key);
    if (cmp == 0) {
      return size(subtreeRoot.left);
    } else if (cmp < 0) {
      return  rank(subtreeRoot.left, key);
    } else {
      return size(subtreeRoot.left) + 1 + rank(subtreeRoot.right, key) ;
    }
  }

  public void deleteMin() {
    root = deleteMin(root);
  }

  private Node deleteMin(Node subtreeRoot) {
    if (subtreeRoot.left == null) {
      return subtreeRoot.right;
    };
    subtreeRoot.left = deleteMin((subtreeRoot.left));
    subtreeRoot.N = size(subtreeRoot.left) + size(subtreeRoot.right) + 1;
    return subtreeRoot;
  }

  public void delete(Key key) {
    root = delete(root, key);
  }

  private Node delete(Node subtreeRoot, Key key) {
    if (subtreeRoot == null) {
      return null;
    }
    int cmp = key.compareTo(subtreeRoot.key);
    if (cmp < 0) {
      subtreeRoot.left =  delete(subtreeRoot.left, key);
    } else if (cmp > 0) {
      subtreeRoot.right = delete(subtreeRoot.right, key);
    } else {
      if (subtreeRoot.left == null) {
        return subtreeRoot.right;
      } else if (subtreeRoot.right == null) {
        return subtreeRoot.left;
      } else {
        Node temp = subtreeRoot;
        subtreeRoot = min(subtreeRoot.right);
        subtreeRoot.right = deleteMin(temp.right);
        subtreeRoot.left = temp.left;
      }
    }
    subtreeRoot.N = size(subtreeRoot.right) + size(subtreeRoot.left) + 1;
    return subtreeRoot;
  }

  private void print(Node subtreeRoot) {
    if (subtreeRoot == null) return;
    print(subtreeRoot.left);
    StdOut.println(subtreeRoot.key);
    print(subtreeRoot.right);
  }
}
