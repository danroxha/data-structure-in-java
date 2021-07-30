package aed.sort;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

import aed.sort.Sort;

public class SortTest { 
  
  List<Integer> listOrderInteger;
  List<Integer> listDesorderInteger;

  List<String> listOrderString;
  List<String> listDesorderString;

  @Before   
  public void setUp() {
    listOrderInteger = new ArrayList<>(){{ add(0); add(1); add(2); add(3); add(4); add(5); }};
    listDesorderInteger = new ArrayList<>(){{ add(5); add(0); add(3); add(4); add(2); add(1); }};
    
    listOrderString = new ArrayList<>(){{ add("daniel"); add("rocha"); add("silva"); }};
    listDesorderString = new ArrayList<>(){{ add("silva"); add("daniel"); add("rocha"); }};     
  }

  @Test
  public void shouldSortIntegerListWithQuickSort() {
    Sort.<Integer>quicksort(listDesorderInteger, (a, b) -> a - b);  
    assertEquals(listOrderInteger, listDesorderInteger);
  }

  @Test
  public void shouldSortStringListWithQuickSort() {
    Sort.<String>quicksort(listDesorderString, (a, b) -> a.compareTo(b));
    assertEquals(listOrderString, listDesorderString);
  }
  
  @Test
  public void shouldSortIntegerListWithCocktail() {
    Sort.<Integer>cocktail(listDesorderInteger, (a, b) -> a - b);  
    assertEquals(listOrderInteger, listDesorderInteger);
  }

  @Test
  public void shouldSortStringListWithCocktail() {
    Sort.<String>cocktail(listDesorderString, (a, b) -> a.compareTo(b));
    assertEquals(listOrderString, listDesorderString);
  }

  @Test
  public void shouldSortIntegerListWithHeapSort() {
    Sort.<Integer>heapsort(listDesorderInteger, (a, b) -> a - b);
    assertEquals(listOrderInteger, listDesorderInteger);
  }

  @Test
  public void shouldSortStringListWithHeapSort() {
    Sort.<String>heapsort(listDesorderString, (a, b) -> a.compareTo(b));
    assertEquals(listOrderString, listDesorderString);
  }
}
