
package aed.queue;

import aed.util.AbstractLinkedList;
import aed.util.Node;

public class Queue<T> extends AbstractLinkedList<T> {
  public T pool() {

    if(isEmpty()) {
      return null;
    }

    Node targetNode = firstNode;
    
    firstNode = firstNode.getNodeReferenceRight();

    if(firstNode != null)
      firstNode.setNodeReferenceLeft(null);

    targetNode.clearReference();

    sizeDecrement();

    return (T)targetNode.getValue();
  }
  
  @Override
  public Queue<T> clone() {
    
    if(isEmpty()) return null;
    
    var cloneQueue = new Queue<T>();
    var nextNode = firstNode;
    
    while(nextNode != null) {
      cloneQueue.add(nextNode.getValue());
      nextNode = nextNode.getNodeReferenceRight();
    }
    
    return cloneQueue;
  }
  
  public T peek() {
    if(isEmpty()) return null;
    
    return firstNode.getValue();
  }
}