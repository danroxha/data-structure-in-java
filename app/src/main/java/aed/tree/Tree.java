package aed.tree;

import java.util.Comparator;
import java.util.function.Consumer;
import aed.linkedlist.LinkedList;
import static aed.util.AbstractLinkedList.Node;

public class Tree<T> {
  
  protected Node<T> root = null;
  protected int sizeTree = 0;
  protected LinkedList<T> list = null;
  private final Comparator<? super T> comparator;
  
  public Tree() {
    comparator = null;
  }

  public Tree(Comparator<? super T> comparator) {
    this.comparator = comparator;
  }

  final int compare(Object valueA, Object valueB) {
    return comparator == null 
      ? ((Comparable<? super T>)valueA).compareTo((T)valueB)
      : comparator.compare((T)valueA, (T)valueB);
  }

  public Boolean contains(T value) {
    
    var current = root;

    while(current != null) {
      if(compare(value, current.getValue()) < 0) {
        current = current.getNodeReferenceLeft();
      }
      else if(compare(value, current.getValue()) > 0) {
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

  public void insert(T value) {
    
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
      
      if(compare(value, current.getValue()) < 0) {
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


  public T remove(T value) {
    
  
    if(isEmpty()) return null;
      
    root = recursiveRemove(root, value);
    
    sizeDecrement();
     
    return (root != null) ? value : null;
  }

  public Boolean replace(T valueA, T valueB) {
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

  private void printTreeOrder(Node<T> tree, Integer level, String dir) {
    
    if(tree == null) return;
    
    printTreeOrder(tree.getNodeReferenceLeft(), ++level, "left");
    System.out.println(String.format("%s %s : %s", "-".repeat(level), tree.getValue(), dir));
    printTreeOrder(tree.getNodeReferenceRight(), ++level, "right");
  }
  
  private Node<T> recursiveRemove(Node<T> node, T value) {
    if(node == null) return null;
      
    if(compare(value, node.getValue()) < 0) {
      node.setNodeReferenceLeft(recursiveRemove(node.getNodeReferenceLeft(), value));
    }
    else if(compare(value, node.getValue()) > 0) {
      node.setNodeReferenceRight(recursiveRemove(node.getNodeReferenceRight(), value));
    }
    else {
      
      if(node.getNodeReferenceLeft() == null) return node.getNodeReferenceRight();
      else if(node.getNodeReferenceRight() == null) return node.getNodeReferenceLeft();
      else {
        Node<T> successorNode = searchForSuccessorNode(node.getNodeReferenceRight());
        node.setValue((T)successorNode.getValue());
        node.setNodeReferenceRight(recursiveRemove(successorNode, (T)node.getValue()));
      }
    }
    
    return node;
  }
   
  private Node<T> searchForSuccessorNode(Node<T> node){
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
