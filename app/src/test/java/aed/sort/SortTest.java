package aed.sort;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import aed.sort.Sort;

public class SortTest { 
  
  List<Integer> listOrderInteger;
  List<Integer> listDesorderInteger;

  List<String> listOrderString;
  List<String> listDesorderString;

  @Before   
  public void setUp() {
    listOrderInteger = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    listDesorderInteger = new ArrayList<>(Arrays.asList(4, 1, 2, 5, 3));
    
    listOrderString = new ArrayList<>(Arrays.asList("daniel", "rocha", "silva"));
    listDesorderString = new ArrayList<>(Arrays.asList("rocha", "silva", "daniel"));
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
