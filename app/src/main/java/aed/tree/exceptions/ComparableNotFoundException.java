package aed.tree.exceptions;

public class ComparableNotFoundException extends Exception {
  public ComparableNotFoundException() {
    super("Comparable not found for custom type");
  }
}
