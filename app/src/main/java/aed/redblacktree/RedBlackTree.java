package aed.redblacktree;

import java.util.Comparator;

public class RedBlackTree<T> {
  
  private static final Boolean RED = true;
  private static final Boolean BLACK = false;
  private final Comparator<? super T> comparator;

  private Node<T> root;

  public RedBlackTree() {
    this(null);
  }

  public RedBlackTree(Comparator<? super T> comparator) {
    this.comparator = comparator;
  }
  
  public Boolean contains(T value) {
    if(root == null)
      return false;

    var current = root;

    while(current != null) {
      
      if(compare(value, current.value) == 0)
        return true;
      
      if(compare(value, current.value) > 0)
        current = current.right;
      else
        current = current.left;
    }

    return false;
  }

  public T insert(T value) {
  
    root = insert(root, value);

    if(root != null)
      root.color = BLACK;

    return value;
  }
  
  public Boolean remove(T value) {
    if(!contains(value))
      return false;

    root = remove(root, value);

    if(root != null)
      root.color = BLACK;
    
    return true;
  }

  private Node<T> remove(Node<T> current, T value) {
    
    if(compare(value, current.value) < 0) {
      if(nodeIsBlack(current.left) && nodeIsBlack(current.left.left))
        current = moveToLeft(current);

      current.left = remove(current.left, value);
    }
    else{
      if(nodeIsRed(current.left))
        current = rightRotate(current);

      if(compare(value, current.value) == 0 && (current.right == null))
        return null;

      if(nodeIsBlack(current.right) && nodeIsBlack(current.right.left))
          current = modeToRight(current);

      if(compare(value, current.value) == 0) {
        var successorNode = successorNode(current.right);
        current.value = successorNode.value;
        current.right = removeSmallestKnot(current.right);
      }
      else
        current.right = remove(current.right, value);
    }

    return balance(current);
  }

  private Node<T> removeSmallestKnot(Node<T> current){
    if(current.left == null) {
      return null;
    }
    if(nodeIsBlack(current.left) && nodeIsBlack(current.left.left))
      current = moveToLeft(current);

    current.left = removeSmallestKnot(current.left);
    return balance(current);
  }

  private Node<T> successorNode(Node<T> current) {
    Node<T> nodeA = current;
    Node<T> nodeB = current.left;

    while(nodeB != null) {
      nodeA = nodeB;
      nodeB = nodeB.left;
    }

    return nodeA;
  }

  private Node<T> balance(Node<T> current) {
    
    if(nodeIsRed(current.right))
      current = leftRotate(current);

    if(current.left != null && nodeIsRed(current.left) && nodeIsRed(current.left.left))
      current = rightRotate(current);

    if(nodeIsRed(current.left) && nodeIsRed(current.right))
      changeColor(current);

    return current;
  }
  
  private Node<T> moveToLeft(Node<T> current) {
    
    changeColor(current);

    if(nodeIsRed(current.right.left)) {
      current.right = rightRotate(current.right);
      current = leftRotate(current);
      changeColor(current);
    }

    return current;
  }

  private Node<T> modeToRight(Node<T> current) {
    
    changeColor(current);

    if(nodeIsRed(current.left.left)){
      current = rightRotate(current);
      changeColor(current);
    }

    return current;
  }

  private Boolean nodeIsRed(Node<T> node) {
    return (node == null)? false : node.color == RED;
  }

  private Boolean nodeIsBlack(Node<T> node) {
    return (node == null)? true : node.color == BLACK;
  }

  private Node<T> insert(Node<T> current, T value) {
    if(current == null){
      return new Node<>(value);
    }

    if(compare(value, current.value) < 0)
      current.left = insert(current.left,value);
    else
      current.right = insert(current.right,value);

    if(nodeIsRed(current.right) && nodeIsBlack(current.left))
      current = leftRotate(current);

    if(nodeIsRed(current.left) && nodeIsRed(current.left.left))
      current = rightRotate(current);

    if(nodeIsRed(current.left) && nodeIsRed(current.right))
      changeColor(current);

    return current;
  }

  private void changeColor(Node<T> current) {
    
    current.color = !nodeIsRed(current);

    if(current.left != null)
      current.left.color = !nodeIsRed(current.left);
    
    if(current.right != null)
      current.right.color = !nodeIsRed(current.right);
  }

  private Node<T> rightRotate(Node<T> nodeA) {
    
    var nodeB = nodeA.left;
    
    nodeA.left = nodeB.right;
    nodeB.right = nodeA;

    nodeB.color = nodeA.color;
    nodeA.color = RED;
    
    return nodeB;
  }

  private Node<T> leftRotate(Node<T> nodeA) {
    
    var nodeB = nodeA.right;
    
    nodeA.right = nodeB.left;
    nodeB.left = nodeA;
    
    nodeB.color = nodeA.color;
    nodeA.color = RED;
    
    return nodeB;
  }

  private final int compare(T valueA, T valueB) {
    return comparator == null 
      ? ((Comparable<? super T>)valueA).compareTo(valueB)
      : comparator.compare(valueA, valueB);
  }

  private static class Node<T> {
    
    protected T value;
    protected Node<T> left;
    protected Node<T> right;
    protected Boolean color = RED;

    public Node(T value) {
      this.value = value;
    }
  }

  private void imprimirArvore(Node<T> root, int space) {
        
    if(root != null) {
      
      space = space + 10;
      for(var i = 0; i < space; i++) {
      }

      imprimirArvore(root.right, space);
      
      System.out.print("\n");
      
      for (int i = 10; i < space; i++)
        System.out.print(" ");
      
      System.out.print(root.value);   
      
      System.out.print("\n");
      
      imprimirArvore(root.left, space);
    }
  }
  
  public void printTree() {
    imprimirArvore(root, 0);
  }
}