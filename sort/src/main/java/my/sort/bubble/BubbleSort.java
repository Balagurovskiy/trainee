package my.sort.bubble;

import java.util.Arrays;

import my.sort.Sortable;

public class BubbleSort implements Sortable{

	@Override
	public <T extends Comparable<T>> T[] sort(T[] input) {
		T[] output = Arrays.copyOf(input, input.length);
		
		for (int i = 0; i < output.length - 1; i++) {
			boolean swaped = false;
			for (int j = 0; j < output.length - 1; j++) {
				if (compareAsc(output[j], output[j + 1])) {
					swap(output, j, j + 1);
					swaped = true;
				}
			}
			if (!swaped) {
				break ;
			}
		}
		return output;
	}
}
