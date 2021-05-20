package aed.tree;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import aed.tree.Tree;
import aed.tree.exceptions.ComparableNotFoundException;

public class TreeTest {

  Tree<String> tree;

  @Before
  public void initialize() throws ComparableNotFoundException {
    tree = new Tree<>();
    tree.insert("Daniel");
    tree.insert("Rocha");
    tree.insert("Silva");
  }

  @Test
  public void verifyIfTheMethodContainsReturnsTrueForTheValueContainedInTheTree() {

    final boolean expect = true;
    var element1 = "Daniel";
    var element2 = "Rocha";
    var element3 = "Silva";

    assertEquals(tree.contains(element1), expect);
    assertEquals(tree.contains(element2), expect);
    assertEquals(tree.contains(element3), expect);
  }

  @Test
  public void verifyIfTheMethodContainsFalseReturnsForTheValueNotContainedInTheTree() {

    final boolean expect = false;
    var element1 = "Peirera";
    assertEquals(tree.contains(element1), expect);
  }
}