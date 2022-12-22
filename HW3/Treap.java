// https://www.sanfoundry.com/java-program-implement-treap/
/**
 *  Java Program to Implement Treap
 **/

import java.util.Random;

/** Class TreapNode **/
class TreapNode {
  TreapNode left, right;
  int priority, element;

  /** Constructor **/
  public TreapNode() {
    this.element = 0;
    this.left = this;
    this.right = this;
    this.priority = Integer.MAX_VALUE;
  }

  /** Constructor **/
  public TreapNode(int ele) {
    this(ele, null, null);
  }

  /** Constructor **/
  public TreapNode(int ele, TreapNode left, TreapNode right) {
    this.element = ele;
    this.left = left;
    this.right = right;
    this.priority = new Random().nextInt();
  }
}

/** Class TreapTree **/
class TreapTree {
  private TreapNode root;
  private static TreapNode nil = new TreapNode();

  /** Constructor **/
  public TreapTree() {
    root = nil;
  }

  public static void main(String[] args) {
    for (int i = 10; i < 30; i++) {
      TreapTree t = new TreapTree();
      long insertStartTime = System.nanoTime();
      for (int j = 0; j < Math.pow(2, i); j++) {
        Random r = new Random();
        int upperBound = (int) Math.pow(2, 30);
        t.insert(r.nextInt(upperBound));
      }
      long insertEndTime = System.nanoTime();
      for (int j = 0; j < 100000; j++) {
        Random r = new Random();
        int upperBound = (int) Math.pow(2, 30);
        t.search(r.nextInt(upperBound));
      }
      long searchEndTime = System.nanoTime();
      System.out.printf("%d,%d\n",
          insertEndTime - insertStartTime,
          searchEndTime - insertEndTime);
    }
  }

  /** Function to check if tree is empty **/
  public boolean isEmpty() {
    return root == nil;
  }

  /** Make the tree logically empty **/
  public void makeEmpty() {
    root = nil;
  }

  /** Functions to insert data **/
  public void insert(int X) {
    root = insert(X, root);
  }

  private TreapNode insert(int X, TreapNode T) {
    if (T == nil)
      return new TreapNode(X, nil, nil);
    else if (X < T.element) {
      T.left = insert(X, T.left);
      if (T.left.priority < T.priority) {
        TreapNode L = T.left;
        T.left = L.right;
        L.right = T;
        return L;
      }
    } else if (X > T.element) {
      T.right = insert(X, T.right);
      if (T.right.priority < T.priority) {
        TreapNode R = T.right;
        T.right = R.left;
        R.left = T;
        return R;
      }
    }
    return T;
  }

  /** Functions to count number of nodes **/
  public int countNodes() {
    return countNodes(root);
  }

  private int countNodes(TreapNode r) {
    if (r == nil)
      return 0;
    else {
      int l = 1;
      l += countNodes(r.left);
      l += countNodes(r.right);
      return l;
    }
  }

  /** Functions to search for an element **/
  public boolean search(int val) {
    return search(root, val);
  }

  private boolean search(TreapNode r, int val) {
    boolean found = false;
    while ((r != nil) && !found) {
      int rval = r.element;
      if (val < rval)
        r = r.left;
      else if (val > rval)
        r = r.right;
      else {
        found = true;
        break;
      }
      found = search(r, val);
    }
    return found;
  }

  /** Function for inorder traversal **/
  public void inorder() {
    inorder(root);
  }

  private void inorder(TreapNode r) {
    if (r != nil) {
      inorder(r.left);
      System.out.print(r.element + " ");
      inorder(r.right);
    }
  }

  /** Function for preorder traversal **/
  public void preorder() {
    preorder(root);
  }

  private void preorder(TreapNode r) {
    if (r != nil) {
      System.out.print(r.element + " ");
      preorder(r.left);
      preorder(r.right);
    }
  }

  /** Function for postorder traversal **/
  public void postorder() {
    postorder(root);
  }

  private void postorder(TreapNode r) {
    if (r != nil) {
      postorder(r.left);
      postorder(r.right);
      System.out.print(r.element + " ");
    }
  }
}
