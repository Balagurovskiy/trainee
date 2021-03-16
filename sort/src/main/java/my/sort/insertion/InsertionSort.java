package my.sort.insertion;

import java.util.Arrays;

import my.sort.Sortable;

public class InsertionSort implements Sortable{

	@Override
	public <T extends Comparable<T>> T[] sort(T[] input) {
		T[] output = Arrays.copyOf(input, input.length);
		
		for (int i = 0; i < output.length; i++) {
			 T current = output[i];
			 int prevIdx = i;
			 while(--prevIdx >= 0) {
				 if (compareDesc(current, output[prevIdx])) {
					 output[prevIdx + 1] = output[prevIdx];
				 } else {
					 break ;
				 }
			 }
			 output[prevIdx + 1] = current;
		}
		return output;
	}
}


