package aed.sort;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import static aed.sort.Sort.quicksort;
import static aed.sort.Sort.cocktail;
import static aed.sort.Sort.heapsort;

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
    quicksort(listDesorderInteger, (a, b) -> a - b);  
    assertEquals(listOrderInteger, listDesorderInteger);
  }

  @Test
  public void shouldSortStringListWithQuickSort() {
    quicksort(listDesorderString, (a, b) -> a.compareTo(b));
    assertEquals(listOrderString, listDesorderString);
  }
  
  @Test
  public void shouldSortIntegerListWithCocktail() {
    cocktail(listDesorderInteger, (a, b) -> a - b);  
    assertEquals(listOrderInteger, listDesorderInteger);
  }

  @Test
  public void shouldSortStringListWithCocktail() {
    cocktail(listDesorderString, (a, b) -> a.compareTo(b));
    assertEquals(listOrderString, listDesorderString);
  }

  @Test
  public void shouldSortIntegerListWithHeapSort() {
    heapsort(listDesorderInteger, (a, b) -> a - b);
    assertEquals(listOrderInteger, listDesorderInteger);
  }

  @Test
  public void shouldSortStringListWithHeapSort() {
    heapsort(listDesorderString, (a, b) -> a.compareTo(b));
    assertEquals(listOrderString, listDesorderString);
  }
}
