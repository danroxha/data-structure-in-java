package aed.sort;

import java.util.List;
import java.util.Comparator;

public class Sort {

  public static <T>void quicksort(T[] collection, Comparator<? super T> comparator) {
    sort(collection, 0, collection.length - 1, comparator);
  }
          
  public static <T>void cocktail(T[] collection, Comparator<? super T> comparator) {
    
    var low = 0; 
    var high = collection.length - 1;
    
    while (low < high) {
      
      
      for (int i = low; i < high; i = i + 1) {
        var diff = comparator.compare(collection[i], collection[i + 1]);
        if (diff > 0) {
          var swap = collection[i];
          collection[i] = collection[i + 1];
          collection[i + 1] = swap;
        }
      }
      
      high = high - 1;
        
      for (int i = high; i > low; i = i - 1) {
        
        var diff = comparator.compare(collection[i], collection[i - 1]);

        if (diff < 0) {
          var swap = collection[i];
          collection[i] = collection[i - 1];
          collection[i - 1] = swap;
        }
      }

      low = low + 1;
    }
  }


  /**QuickSort start*/
	private static <T>int partition(T collection[], int low, int high, Comparator<? super T> comparator) {
		var pivot = collection[high];
		var i = (low - 1);
		
    for (var j = low; j < high; j++) {
      var diff = comparator.compare(collection[j], pivot);
			if (diff <= 0) {
				i++;
				var swap = collection[i];
				collection[i] = collection[j];
				collection[j] = swap;
			}
		}

		var swap = collection[i + 1];
		collection[i + 1] = collection[high];
		collection[high] = swap;

		return i + 1;
	}

	private static <T>void sort(T collection[], int low, int high, Comparator<? super T> comparator) {
		if (low > high) return;

		var middle = partition(collection, low, high, comparator);

		sort(collection, low, middle - 1, comparator);
		sort(collection, middle + 1, high, comparator);
	}
  /**QuickSort end*/
}
