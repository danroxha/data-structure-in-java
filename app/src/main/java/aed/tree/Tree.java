package aed.tree;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

import aed.tree.exceptions.ComparableNotFoundException;
import aed.util.Node;
import aed.linkedlist.LinkedList;

public class Tree<T> {
  
  protected Node<T> root = null;
  protected int sizeTree = 0;
  protected BiFunction<T, T, Integer> comparator = null;
  protected LinkedList<T> list = null;

  public Boolean contains(T value) {
    
    var current = root;

    while(current != null) {
      if(comparator.apply((T)value, (T)current.getValue()) < 0) {
        current = current.getNodeReferenceLeft();
      }
      else if(comparator.apply((T)value, (T)current.getValue()) > 0) {
        current = current.getNodeReferenceRight();
      }
      else {
        return true;
      }
    }

    return false;
  }

  public void forEach(Consumer<? super T> action) {
    walk(root, action);
  }

  public void insert(T value) throws ComparableNotFoundException {
    
    if(comparator == null) setComparatorDefault(value);
    if(value == null) return; 

    var newNode = new Node<T>(value);
    sizeIncrement();

    if(root == null) {
      root = newNode;
      return;
    }

    var current = root;
    var parent = current;

    while(true) {
      
      parent = current;
      
      if(comparator.apply((T)value, (T)current.getValue()) < 0) {
        current = current.getNodeReferenceLeft();

        if(current == null) {
          parent.setNodeReferenceLeft(newNode);
          return;
        }
      }
      else {
        current = current.getNodeReferenceRight();

        if(current == null) {
          parent.setNodeReferenceRight(newNode);
          return;
        }
      }
    }
  }

  public boolean isEmpty() {
    return size() == 0;
  }
  
  public void print() {
    printTreeOrder(root, 0, "root");
  }

  public void setComparator(BiFunction<T, T, Integer> comparator) {
    this.comparator = comparator;
  }

  public T remove(T value) throws ComparableNotFoundException {
    
    if(comparator == null) setComparatorDefault(value);

    if(isEmpty()) return null;
      
    root = recursiveRemove(root, value);
    
    sizeDecrement();
     
    return (root != null) ? value : null;
  }

  public Boolean replace(T valueA, T valueB) throws ComparableNotFoundException {
    if(!contains(valueA)) return false;

    remove(valueA);
    insert(valueB);

    return true;
  }
  
  public int size() {
    return sizeTree;
  }

  public LinkedList<T> toLinkedList() {
    list = new LinkedList<>();

    walk(root, value -> {
      list.add(value);
    });

    return list;
  }

  protected void sizeIncrement() {
    sizeTree++;
  }

  protected void sizeDecrement() {
    sizeTree--;
  }

  private void setComparatorDefault(T value) throws ComparableNotFoundException {
    
    BiFunction<Integer, Integer, Integer> comparatorNumber = (a, b) -> a - b;
    BiFunction<String, String, Integer> comparatorString = (a, b) -> a.compareTo(b);
  
    if(Number.class.isInstance(value))
      this.comparator = (BiFunction<T, T, Integer>)comparatorNumber;
    else if(String.class.isInstance(value))
      this.comparator = (BiFunction<T, T, Integer>) comparatorString;
    else
      throw new ComparableNotFoundException();
  }

  private void printTreeOrder(Node tree, Integer level, String dir) {
    
    if(tree == null) return;
    
    printTreeOrder(tree.getNodeReferenceLeft(), ++level, "left");
    System.out.println(String.format("%s %s : %s", "-".repeat(level), tree.getValue(), dir));
    printTreeOrder(tree.getNodeReferenceRight(), ++level, "right");
  }
  
  private Node recursiveRemove(Node node, T value) throws ComparableNotFoundException {
    if(node == null) return null;
      
    if(comparator.apply(value, (T)node.getValue()) < 0) {
      node.setNodeReferenceLeft(recursiveRemove(node.getNodeReferenceLeft(), value));
    }
    else if(comparator.apply(value, (T)node.getValue()) > 0) {
      node.setNodeReferenceRight(recursiveRemove(node.getNodeReferenceRight(), value));
    }
    else {
      
      if(node.getNodeReferenceLeft() == null) return node.getNodeReferenceRight();
      else if(node.getNodeReferenceRight() == null) return node.getNodeReferenceLeft();
      else {
        Node successorNode = searchForSuccessorNode(node.getNodeReferenceRight());
        node.setValue(successorNode.getValue());
        node.setNodeReferenceRight(recursiveRemove(successorNode, (T)node.getValue()));
      }
    }
    
    return node;
  }
   
  private Node searchForSuccessorNode(Node node){
    while(node.getNodeReferenceLeft() != null){
      node = node.getNodeReferenceLeft();
    }
    return node;
  }

  private void walk(Node<T> node, Consumer<? super T> action) {
     
    if(node == null) return;
    
    walk(node.getNodeReferenceLeft(), action);
    action.accept(node.getValue());
    walk(node.getNodeReferenceRight(), action);
  }
}
