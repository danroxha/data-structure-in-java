package aed.sort;

import java.util.List;
import java.util.Comparator;

public class Sort {

  public static <T>void quickSort(T[] collection, Comparator<? super T> comparator) {
    sort(collection, 0, collection.length - 1, comparator);
  }

	private static <T>int partition(T collection[], int low, int high, Comparator<? super T> comparator) {
		var pivot = collection[high];
		int i = (low - 1);
		
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
}
