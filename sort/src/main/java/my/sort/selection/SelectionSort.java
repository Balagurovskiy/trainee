package my.sort.selection;

import java.util.Arrays;

import my.sort.Sortable;

public class SelectionSort implements Sortable {

	@Override
	public <T extends Comparable<T>> T[] sort(T[] input) {
		T[] output = Arrays.copyOf(input, input.length);
		
		for (int i = 0; i < output.length; i++) {
			int min = i;
			for (int j = min; j < output.length; j++) {
				if (compareDesc(output[j], output[min])) {
					min = j;
				}
			}
			swap(output, i, min);
		}
		return output;
	}

}
