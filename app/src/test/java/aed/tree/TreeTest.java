package aed.tree;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import util.People;

public class TreeTest {

  Tree<String> tree;

  @Before
  public void initialize() {
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

  @Test
  public void verifyIfTheMethodReplaceChangeValuesInTheTree() 
    throws Exception {
    
    var expectTrue = true;
    var expectFalse = false;

    var validValue = "Daniel";
    var invalidValue = "Pereira";

    assertEquals(tree.replace(validValue, "Replace"), expectTrue);
    assertEquals(tree.replace(invalidValue, "Replace"), expectFalse);
  }

  @Test
  public void shouldReturnALinkedListFromTree() {
    var list = tree.toLinkedList();

    assertEquals(list.length(), tree.size());
  }

  @Test
  public void shouldStoreAnObjectInTheTree() {
    var tree = new Tree<People>();
    final var elementsNumber = 5;
    for(int i = 0; i < elementsNumber; i++) {
      tree.insert(new People("Rocha" + i, "Silva" + i ));
    }

    assertEquals(tree.size(), elementsNumber);
  }

  @Test
  public void ShouldStoreObjectsInTheTreeWithCustomComparator() {
    var tree = new Tree<People>((a, b) -> a.getLastName().compareTo(b.getLastName()));
    
    final var elementsNumber = 5;
    for(int i = 0; i < elementsNumber; i++) {
      tree.insert(new People("Rocha" + i, "Silva" + i ));
    }

    assertEquals(tree.size(), elementsNumber);
  }
}