package aed.util;

import java.util.function.BiPredicate;
import java.util.Objects;

public abstract class AbstractLinkedList<T> {
  
  protected Node<T> firstNode = null;
  protected Node<T> lastNode = null;
  private int size = 0;

  public T add(T value) {
    
    if(firstNode == null) {
      firstNode = new Node<T>(value);
      lastNode = firstNode;
      sizeIncrement();
      return value;
    }
  
    Node newNode = new Node<T>(value);
    
    lastNode.setNodeReferenceRight(newNode);
    newNode.setNodeReferenceLeft(lastNode);
    lastNode = newNode;

    sizeIncrement();
    return value;
  }
  
  public void clear() {
    
    Node nextNode = firstNode;
    while(nextNode != null) {
      Node targetNode = nextNode;
      nextNode = nextNode.getNodeReferenceRight();
      targetNode.clearReference();
    }
    
    firstNode = null;
    lastNode = null;
    size = 0;
  }
  
  abstract public Object clone();
  
  public Boolean contains(T value) {
    
    if(value == null) return false;
    
    Node node = firstNode;
    while(node != null) {
      if(value.equals((T) node.getValue())) {
        return true;
      }  
      node = node.getNodeReferenceRight();
    }
    
    return false;
  }
  
  public Boolean contains(T value, BiPredicate comparator) {
    
    Node node = firstNode;
    while(node != null) {
      if(comparator.test(value, node.getValue())) {
        return true;
      }  
      node = node.getNodeReferenceRight();
    }
    
    return false;
  }
 
  public int length() {
    return size;
  }

  public Boolean isEmpty() {
    return length() == 0;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj == null) return false;
    if(this == obj) return true;

    return hashCode() == obj.hashCode();
  }

  @Override
  public int hashCode() {
    
    var hash = new StringBuilder();
    var nextNode = firstNode;
    while(nextNode != null) {
      hash.append(nextNode.getValue());
      nextNode = nextNode.getNodeReferenceRight();
    }

    return Objects.hash(hash.toString());
  }
  
  protected void sizeIncrement() {
    size++;
  }

  protected void sizeDecrement() {
    size--;
  }

  public static class Node<T> {
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

    public void setNodeReferenceLeft(Node<T> left) {
      this.left = left;
    }

    public void setNodeReferenceRight(Node<T> right) {
      this.right = right;
    }

    public Node<T> getNodeReferenceLeft() {
      return left;
    }

    public Node<T> getNodeReferenceRight() {
      return right;
    }

    public void clearReference() {
      left = null;
      right = null;
    }
  }
}
