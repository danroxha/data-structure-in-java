package aed.util;

public class Node<T> {
  protected T value;
  protected Node<T> left;
  protected Node<T> right;

  public Node() {}
  public Node(T value) {
    this.value = value;
  }
  
  public void setValue(T value) {
    this.value = value;
  }
  public T getValue() {
    return value;
  }
  
  public void setNodeReferenceLeft(Node left) {
    this.left = left;
  }
  
  public void setNodeReferenceRight(Node right) {
    this.right = right;
  }
  
  public Node getNodeReferenceLeft() {
    return left;
  }
  
  public Node getNodeReferenceRight() {
    return right;
  }

  public void clearReference() {
    left = null;
    right = null;
  }
}
