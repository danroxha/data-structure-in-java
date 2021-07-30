package aed.sort;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertArrayEquals;

import java.util.List;
import java.util.ArrayList;

import aed.sort.Sort;

public class SortTest { 
  
  Integer[] listOrderInteger;
  Integer[] listDesorderInteger;

  String[] listOrderString;
  String[] listDesorderString;


  @Before   
  public void setUp() {
    
    listOrderInteger = new Integer[]{0, 1, 2, 3, 4, 5};
    listDesorderInteger = new Integer[]{5, 0, 3, 4, 2, 1};
    
    listOrderString = new String[]{"daniel", "rocha", "silva"};
    listDesorderString = new String[]{"silva", "daniel", "rocha"};

     
  }

  @Test
  public void shouldSortIntegerListWithQuickSort() {
    Sort.<Integer>quickSort(listDesorderInteger, (a, b) -> a - b);  
    assertArrayEquals(listOrderInteger, listDesorderInteger);
  }

  @Test
  public void shouldSortStringListWithQuickSort() {
    Sort.<String>quickSort(listDesorderString, (a, b) -> a.compareTo(b));
    assertArrayEquals(listOrderString, listDesorderString);
  }
}
