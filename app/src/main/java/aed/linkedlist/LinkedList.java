package aed.linkedlist;

import aed.util.AbstractLinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList <T> extends AbstractLinkedList <T> implements Iterable<T> {
  
  public LinkedList(){}

  @SafeVarargs
  public LinkedList(T... values) {
    for(var value : values) {
      add(value);
    }
  }

  public LinkedList(LinkedList<T> linkedlist) {
    if(linkedlist == null) return;
    
    Node<T> nextNode = linkedlist.firstNode;
    while(nextNode != null) {
      add((T)nextNode.getValue());
      nextNode = nextNode.getNodeReferenceRight();
    }
  }

  public void add(int index, T value) throws IndexOutOfBoundsException {
    
    if(index == length()) {
      add(value);
      return;
    } 
    
    var targetNode = searchIndexLeftOrRight(index);
    var newNode = new Node<T>(value);
    
    var previousNode = targetNode.getNodeReferenceLeft();
    
    if(previousNode != null)
      previousNode.setNodeReferenceRight(newNode);
    
    targetNode.setNodeReferenceLeft(newNode);
    
    newNode.setNodeReferenceLeft(previousNode);
    newNode.setNodeReferenceRight(targetNode);
    
    if(index == 0) firstNode = newNode;
    if(index == length() - 1) lastNode = targetNode;
    
    sizeIncrement();
  }
  
  @Override
  public LinkedList<T> clone() {
    
    if(isEmpty()) return new LinkedList<T>();
    
    var cloneLinkedList = new LinkedList<T>();
    var nextNode = firstNode;
    
    while(nextNode != null) {
      cloneLinkedList.add(nextNode.getValue());
      nextNode = nextNode.getNodeReferenceRight();
    }
    
    return cloneLinkedList;
  }
  
  public void concat(LinkedList<T>... linkedlist) {
    
    if(linkedlist == null) return;

    for(var list : linkedlist) {

      if(this == list) {
        list = new LinkedList<T>(list);
      }

      var nextNode = list.firstNode;
    
      while(nextNode != null) {
        add(nextNode.getValue());
        nextNode = nextNode.getNodeReferenceRight();
      }  
    } 
  }
  
  public T get(int index) throws IndexOutOfBoundsException {
    
    if(index >= length() || index < 0) 
      throw new IndexOutOfBoundsException(); 

    var target = searchIndexLeftOrRight(index);

    return target.getValue();
  }
  
  public int indexOf(T value) {
    
    if(value == null) return -1;
    
    int index = 0;    
    var nextNode = firstNode;
    
    while(nextNode != null) {
      
      if(value.equals(nextNode.getValue())) {
        return index;
      }
      index++;
      nextNode = nextNode.getNodeReferenceRight(); 
    }
    
    return -1;
  }
  
  public T remove(int index) throws IndexOutOfBoundsException {
    
    var targetNode = searchIndexLeftOrRight(index);
    var previousNode = targetNode.getNodeReferenceLeft();
    var nextNode = targetNode.getNodeReferenceRight();

    if(previousNode != null) 
      previousNode.setNodeReferenceRight(nextNode);
    else firstNode = nextNode;

    if(nextNode != null) nextNode.setNodeReferenceLeft(previousNode);
    else lastNode = previousNode;

    targetNode.clearReference();

    sizeDecrement();
    
    return (T) targetNode.getValue();
  }
  
  private Node<T> searchIndexLeftOrRight(int index) throws IndexOutOfBoundsException {
    if(index >= length() || index < 0) 
      throw new IndexOutOfBoundsException();

    Integer countIndex = 0;
    var targetNode = firstNode;

    if(index > length() / 2) {
      countIndex = length() - 1;
      targetNode = lastNode;

      while(countIndex != index && targetNode.getNodeReferenceLeft() != null) {
        targetNode = targetNode.getNodeReferenceLeft();
        countIndex--;
      }
    }
    else {
      while(countIndex != index ) {
        targetNode = targetNode.getNodeReferenceRight();
        countIndex++;
      }
    }

    return targetNode;
  }
  
  public T set(int index, T value) throws IndexOutOfBoundsException {
    if(index < 0 || index >= length())
      throw new IndexOutOfBoundsException();
    
    var targetNode = searchIndexLeftOrRight(index);
    var oldValue  = targetNode.getValue();
    targetNode.setValue(value);
    
    return (T)oldValue;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {

      private Node<T> target = firstNode;

      @Override
      public boolean hasNext() {
        return target != null;
      }
  
      @Override
      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }

        var value = (T)target.getValue();
        target = target.getNodeReferenceRight();
        
        return value;
      }
    };
  }

}
