package aed.hashtable;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class HashTableTest {
  HashTable<String, String> table;

  @Before
  public void initialize() {
    table = new HashTable<String, String>(){{
      put("name", "Daniel");
      put("last", "Rocha");
    }};
  }

  @Test
  public void shouldReturnTrueForContainsKeyMethod() {
    var key = "name";
    var result = table.containsKey(key);
    assertTrue(result);
  }

  @Test
  public void shouldReturnFalseForContainsKeyMethod() {
    var key = "first";
    var result = table.containsKey(key);

    assertFalse(result);
  }

  @Test
  public void shouldDeleteKeyAndValueForHashTable() {
    var table = new HashTable<String, String>(){{
      put("name", "Daniel");
      put("last", "Rocha");
    }};

    var key = "name";
    table.remove(key);
    var result = table.containsKey(key);

    assertFalse(result);
  }

  @Test
  public void shouldReplaceValueForHashTable() {
    var table = new HashTable<String, String>(){{
      put("name", "Daniel");
      put("last", "Rocha");
    }};

    var key = "last";
    var value = "Silva";
    table.replace(key, value);

    var result = table.get(key);

    assertEquals(result, value);
  }
}
