package aed.redblacktree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class RedBlackTreeTest {
  RedBlackTree<Integer> rbtree;
  
  @Before
  public void initialize() {
    rbtree = new RedBlackTree<Integer>() {{
      for(var i = 0; i < 10; i++) insert(i);
    }};
  }

  @Test
  public void shouldContainsValueIntheRedBlackTree() { 
    assertTrue(rbtree.contains(1));
  }

  @Test 
  public void shouldNotContainsValueIntheRedBlackTree() {
    assertFalse(rbtree.contains(10));
  }

  @Test
  public void shouldDeleteValueInTheRedBlackTree() {
    
    var removed = 2;

    assertTrue(rbtree.contains(removed));
    rbtree.remove(removed);
    assertFalse(rbtree.contains(removed));
  }

  @Test
  public void shouldAddValueInTheRedBlackTree() {
    
    var value = 11;

    assertFalse(rbtree.contains(value));
    rbtree.insert(value);
    assertTrue(rbtree.contains(value));
  }
}
