package my.sort.merge;


import java.util.Arrays;

import my.sort.Sortable;

public class MergeSort implements Sortable{
	
	private <T extends Comparable<T>> void merge(T source[], int left, int middle, int right)
    {
        int leftPartSize = middle - left + 1;
        int rightPartSize = right - middle;
 
        Object leftPart[] = new Object[leftPartSize];
        Object rightPart[] = new Object[rightPartSize];
 
        System.arraycopy(source, left , leftPart, 0, leftPartSize);
        System.arraycopy(source, middle + 1 , rightPart, 0, rightPartSize);
 
        int i = 0;
        int j = 0;

        while (i < leftPartSize && j < rightPartSize) {
        	T leftElement = (T) leftPart[i];
        	T rightElement = (T) rightPart[j];
            if (compareDesc(leftElement, rightElement)) {
                source[left] = leftElement;
                i++;
            } else {
                source[left] = rightElement;
                j++;
            }
            left++;
        }
        while (i < leftPartSize) {
        	T leftElement = (T) leftPart[i];
            source[left] = leftElement;
            i++;
            left++;
        }
        while (j < rightPartSize) {
        	T rightElement = (T) rightPart[j];
            source[left] = rightElement;
            j++;
            left++;
        }
    }
 
    private <T extends Comparable<T>> void sort(T source[], int left, int right)
    {
        if (left < right) {
            int mmiddle = left + (right - left) / 2;
 
            sort(source, left, mmiddle);
            sort(source, mmiddle + 1, right);
 
            merge(source, left, mmiddle, right);
        }
    }
	@Override
	public <T extends Comparable<T>> T[] sort(T[] input) {
		T[] output = Arrays.copyOf(input, input.length);
		
		sort(output, 0 , output.length - 1);
		
		return output;
	}
}
