package my.sort.shell;

import java.util.Arrays;

import my.sort.Sortable;

public class ShellSort implements Sortable{

	@Override
	public <T extends Comparable<T>> T[] sort(T[] input) {
		T[] output = Arrays.copyOf(input, input.length);
		int gap = output.length / 2;
		
		while(gap >= 1) {
		
			for (int i = 0; i < output.length; i++) {
				for (int j = i - gap; j >= 0; j -= gap) {
					if (compareAsc(output[j], output[j + gap])) {
						swap(output, j, j + gap);
					}
				}
			}
			gap /= 2;
		}
		return output;
	}

}
