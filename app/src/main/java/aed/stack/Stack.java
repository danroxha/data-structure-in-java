package aed.stack;

import aed.util.AbstractLinkedList;
import aed.util.Node;

public class Stack<T> extends AbstractLinkedList<T> {
  
  @Override
  public Stack<T> clone() {
    
    if(isEmpty()) return null;
    
    var cloneStack = new Stack<T>();
    var nextNode = firstNode;
    
    while(nextNode != null) {
      cloneStack.push(nextNode.getValue());
      nextNode = nextNode.getNodeReferenceRight();
    }
    
    return cloneStack;
  }
  
  public T pop() {
    
    if(isEmpty()) {
      return null;
    }
    
    Node targetNode = lastNode;
    
    lastNode = lastNode.getNodeReferenceLeft();
    if(lastNode != null) {
      lastNode.setNodeReferenceRight(null);
    }
    
    targetNode.clearReference();
    
    sizeDecrement();
    
    return (T) targetNode.getValue();
  }
  
  public T peek() {
    if(isEmpty()) return null;
    
    return lastNode.getValue();
  }
  
  public T push(T value) {
    return add(value);
  }
}
