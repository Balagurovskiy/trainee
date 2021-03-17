package my.sort.quick;

import java.util.Arrays;

import my.sort.Sortable;

public class QuickSort implements Sortable{
	private <T extends Comparable<T>> int partition(T[] source, int left, int right) {
        T pivot = source[left];
        while (left < right) {
           while (compareDesc(source[left], pivot)) {
        	   left++;
           }
           while (compareAsc(source[right], pivot)) {
        	   right--;
           }
           swap(source, left, right);
        }
        return left;
    }
    
    private <T extends Comparable<T>> void sort(T[] source, int left, int right) {
        if (left < right) {
	        int pivotIndex = partition(source, left, right);
	        sort(source, left, pivotIndex);
	        sort(source, pivotIndex + 1, right);
   		}
     }
	@Override
	public <T extends Comparable<T>> T[] sort(T[] input) {
		T[] output = Arrays.copyOf(input, input.length);
		
		sort(output, 0 , output.length - 1);
		
		return output;
	}
}

 
 
