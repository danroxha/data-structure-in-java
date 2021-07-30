package aed.sort;

import java.util.List;
import java.util.Comparator;

public class Sort {

  public static <T>void quicksort(List<T> collection, Comparator<? super T> comparator) {
    sort(collection, 0, collection.size() - 1, comparator);
  }
          
  public static <T>void cocktail(List<T> collection, Comparator<? super T> comparator) {
    
    var low = 0; 
    var high = collection.size() - 1;
    
    while (low < high) {
      
      for (var i = low; i < high; i++) {
        
        var diff = comparator.compare(collection.get(i), collection.get(i + 1));
        
        if (diff > 0) {
          var swap = collection.get(i);
          collection.set(i, collection.get(i + 1));
          collection.set(i + 1, swap);
        }
      }
      
      high = high - 1;
        
      for (var i = high; i > low; i--) {
        
        var diff = comparator.compare(collection.get(i), collection.get(i - 1));

        if (diff < 0) {
          var swap = collection.get(i);
          collection.set(i, collection.get(i - 1));
          collection.set(i - 1, swap);
        }
      }

      low = low + 1;
    }
  }

  /**HeapSort start*/
  public static <T>void heapsort(List<T> collection, Comparator<? super T> comparator) {

    var middle = (collection.size() - 1) / 2;
    for(var i = middle; i >= 0; i--) {
      heap(collection, i, collection.size() - 1, comparator);
    }

    var high = collection.size() - 1;
    for(var i = high; i >= 1; i--) {

      final int INITIAL_INDEX = 0;

      var swap = collection.get(INITIAL_INDEX);
      collection.set(INITIAL_INDEX, collection.get(i));
      collection.set(i, swap);

      heap(collection, INITIAL_INDEX, i - 1, comparator);
    }
  }

  public static <T>void heap(List<T> collection, int low, int high, Comparator<? super T> comparator) {

    var swap = collection.get(low);
    var indexChild = low * 2 + 1;

    while(indexChild <= high) {
      if(indexChild < high) {

        var diff = comparator.compare(collection.get(indexChild), collection.get(indexChild + 1));

        if(diff < 0) {
          indexChild++;
        }
      }

      var diff = comparator.compare(swap, collection.get(indexChild));

      if(diff < 0) {
        collection.set(low, collection.get(indexChild));
        low = indexChild;
        indexChild = 2 * low + 1;
      }
      else {
        indexChild = high + 1;
      }
    }

    collection.set(low, swap);
  }
  /**HeapSort end*/

  /**QuickSort start*/
	private static <T>int partition(List<T> collection, int low, int high, Comparator<? super T> comparator) {
		var pivot = collection.get(high);
		var i = (low - 1);
		
    for (var j = low; j < high; j++) {
      var diff = comparator.compare(collection.get(j), pivot);
			if (diff <= 0) {
				i++;
				var swap = collection.get(i);
				collection.set(i, collection.get(j));
				collection.set(j, swap);
			}
		}

		var swap = collection.get(i + 1);
		collection.set(i + 1, collection.get(high));
		collection.set(high, swap);

		return i + 1;
	}

	private static <T>void sort(List<T> collection, int low, int high, Comparator<? super T> comparator) {
		if (low > high) return;

		var middle = partition(collection, low, high, comparator);

		sort(collection, low, middle - 1, comparator);
		sort(collection, middle + 1, high, comparator);
	}
  /**QuickSort end*/
}
