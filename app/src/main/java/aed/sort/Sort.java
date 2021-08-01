package aed.sort;

import java.util.List;
import java.util.Comparator;

public class Sort {

  public static <T>void quicksort(List<T> collection, Comparator<? super T> comparator) {
    qsort(collection, 0, collection.size() - 1, comparator);
  }
          
  public static <T>void cocktail(List<T> collection, Comparator<? super T> comparator) {
    
    var low = 0; 
    var high = collection.size() - 1;
    
    while (low < high) {
      
      for (var i = low; i < high; i++) {
        
        var diff = comparator.compare(collection.get(i), collection.get(i + 1));
        
        if (diff > 0) {
          swap(collection, i, i + 1);
        }
      }
      
      high = high - 1;
        
      for (var i = high; i > low; i--) {
        
        var diff = comparator.compare(collection.get(i), collection.get(i - 1));

        if (diff < 0) {
          swap(collection, i, i - 1);
        }
      }

      low = low + 1;
    }
  }

  public static <T>void heapsort(List<T> collection, Comparator<? super T> comparator) {
    
    buildHeap(collection, comparator);
    
    final int INITIAL_INDEX = 0;
    var high = collection.size() - 1;
    
    for(var i = high; i > INITIAL_INDEX; i--) {
      swap(collection, INITIAL_INDEX, i);    
      heapify(collection, i, INITIAL_INDEX, comparator);
    }
  }

  private static <T>void heapify(List<T> collection, int length, int low, Comparator<? super T> comparator) {
    
    var root  = low;
    var left  = 2 * root + 1;
    var right = 2 * root + 2;

    var leftPointerIsInsideTheArray = left < length;
    
    if(leftPointerIsInsideTheArray) {
      var leftBiggerThanRoot = comparator.compare(collection.get(left), collection.get(root)) > 0;
      if(leftBiggerThanRoot)
        root = left;
    }

    var rightPointerIsInsideTheArray = right < length;

    if(rightPointerIsInsideTheArray) {
      var rightBiggerThanRoot = comparator.compare(collection.get(right), collection.get(root)) > 0;
      if(rightBiggerThanRoot)
        root = right;
    }

    if(root != low) {
      swap(collection, low, root);
      heapify(collection, length, root, comparator);
    }
  }

  private static <T>void buildHeap(List<T> collection, Comparator<? super T> comparator) { 
    var middle = collection.size() / 2 - 1;
    for(var i = middle; i >= 0; i--) {
      heapify(collection, collection.size(), i, comparator);
    }
  }

  /**QuickSort start*/
	private static <T>int partition(List<T> collection, int low, int high, Comparator<? super T> comparator) {
		var pivot = collection.get(high);
		var i = (low - 1);
		
    for (var j = low; j < high; j++) {
      var diff = comparator.compare(collection.get(j), pivot);
			if (diff <= 0) {
				i++;
        swap(collection, i, j);
			}
		}

    swap(collection, i + 1, high);
		return i + 1;
	}

	private static <T>void qsort(List<T> collection, int low, int high, Comparator<? super T> comparator) {
		if (low > high) return;

		var middle = partition(collection, low, high, comparator);

		qsort(collection, low, middle - 1, comparator);
		qsort(collection, middle + 1, high, comparator);
	}
  /**QuickSort end*/

  private static <T>void swap(List<T> collection, int indexA, int indexB) {
    var swap = collection.get(indexA);
    collection.set(indexA, collection.get(indexB));
    collection.set(indexB, swap);
  }
}
