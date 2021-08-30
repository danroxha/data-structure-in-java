package aed.stack;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class StackTest {
  Stack<String> stack;

  @Before
  public void initialize() {
    stack = new Stack<>();
    stack.push("Daniel");
    stack.push("Rocha");
    stack.push("Silva");
  }

  @Test
  public void verifyIfTopStackElementIsCorrect() {
    var element = stack.peek();
    var expect = "Silva";

    assertEquals(element, expect);
  }

  @Test
  public void verifyIfElementIsRemovedWhenIsGetTheLastOfTheStack() {
    var expectLength = stack.length() - 1;
    var expect = "Silva";
    var element = stack.pop();

    assertEquals(element, expect);
    assertEquals(stack.length(), expectLength);
  }

  @Test
  public void shouldCloneStack() {
    var stackClone = stack.clone();
    
    assertEquals(stackClone.length(), stack.length());

    while(!stackClone.isEmpty()) {
      var elementA = stackClone.pop();
      var elementB = stack.pop();
      assertEquals(elementA, elementB);
    }
  }
}