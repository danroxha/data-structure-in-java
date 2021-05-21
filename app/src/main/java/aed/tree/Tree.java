package aed.tree;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

import aed.tree.exceptions.ComparableNotFoundException;
import aed.util.Node;

public class Tree<T> {
  
  protected Node<T> root = null;
  protected Integer sizeTree = 0;
  protected BiFunction<T, T, Integer> comparator = null;

  public void forEach(Consumer<? super T> action) {
    walk(root, action);
  }

  public void insert(T value) throws ComparableNotFoundException {

    if(comparator == null) setComparatorDefault(value);

    if(value == null) return;

    if(comparator == null) 
      throw new ComparableNotFoundException();

    if(root == null) {
      root = new Node<T>(value);
      sizeIncrement();
      return;
    }
    
    recursiveInsert(root, value);
    sizeIncrement();
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
  
  public Integer size() {
    return sizeTree;
  }

  public Boolean contains(T value) {

    Node targetNode = recursiveContains(root, value);
    return targetNode != null;
  }

  private Node recursiveContains(Node node, T value) {
    Node left = null, right = null;

    if(comparator.apply((T)value, (T)node.getValue()) != 0) {
      if(node.getNodeReferenceLeft() != null) {
        left = recursiveContains(node.getNodeReferenceLeft(), value);
      }

      if(left == null) {
        if(node.getNodeReferenceRight() != null)
          right = recursiveContains(node.getNodeReferenceRight(), value);

        if(right == null)
          return null;
        else
          return right;
      }
      else {
        return left;
      }
    }
    
    return node;
    
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

  private void recursiveInsert(Node node, T value) {
    
    if(node == null) return;
    
    Boolean valueIsLess = comparator.apply((T)value, (T)node.getValue()) < 0;
    if(valueIsLess) {
      if(node.getNodeReferenceLeft() != null) {
        recursiveInsert(node.getNodeReferenceLeft(), value);
      }
      else {
        node.setNodeReferenceLeft(new Node<T>(value));
      }
    }
    else {
      if(node.getNodeReferenceRight() != null) {
        recursiveInsert(node.getNodeReferenceRight(), value);
      }
      else {
        node.setNodeReferenceRight(new Node<T>(value));
      }
    }
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
