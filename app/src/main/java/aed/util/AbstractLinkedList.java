package aed.util;

import java.util.function.BiPredicate;

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
  
  protected void sizeIncrement() {
    size++;
  }

  protected void sizeDecrement() {
    size--;
  }  
}
