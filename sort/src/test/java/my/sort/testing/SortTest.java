package my.sort.testing;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import my.sort.bubble.BubbleSort;
import my.sort.insertion.InsertionSort;
import my.sort.quick.QuickSort;
import my.sort.selection.SelectionSort;

public class SortTest {

	private String[] source = {"F","C","G","D","E","B","A"};
	@Test
	public void bubbleTest() {
		String[] expected = Arrays.copyOf(source, source.length);
		Arrays.sort(expected);
		
		String[] tested = new BubbleSort().sort(source);
	    
        Assert.assertArrayEquals(expected, tested);
	}
	
	@Test
	public void selectionTest() {
		String[] expected = Arrays.copyOf(source, source.length);
		Arrays.sort(expected);
		
		String[] tested = new SelectionSort().sort(source);
	    
        Assert.assertArrayEquals(expected, tested);
	}
	@Test
	public void quickTest() {
		String[] expected = Arrays.copyOf(source, source.length);
		Arrays.sort(expected);
		
		String[] tested = new QuickSort().sort(source);

        Assert.assertArrayEquals(expected, tested);
	}
	@Test
	public void insertionTest() {
		String[] expected = Arrays.copyOf(source, source.length);
		Arrays.sort(expected);
		
		String[] tested = new InsertionSort().sort(source);

        Assert.assertArrayEquals(expected, tested);
	}
//	
//    System.out.printf(" src : %s", Arrays.toString(source));
//    System.out.printf(" tested : %s", Arrays.toString(tested));
//    System.out.printf(" sorted : %s", Arrays.toString(expected));
}
