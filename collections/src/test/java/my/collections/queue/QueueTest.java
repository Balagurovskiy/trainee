package my.collections.queue;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;


public class QueueTest {
	private void loadData(Queue<String> a, Queue<String> b) {
		String testData = "QUEUE";
		for (int i = 1; i <= testData.length(); i++) {
			a.add(testData.substring(i - 1, i));
			b.add(testData.substring(i - 1, i));
		}
	}
	@Test
	public void queueTest_Size_SizeEquals(){
		Queue<String> list = new TraineeQueue<>();
		Assert.assertEquals(0, list.size());
	}
	@Test
	public void queueTest_isEmpty_True(){
		Queue<String> list = new TraineeQueue<>();
		Assert.assertTrue(list.isEmpty());
	}
	@Test
	public void queueTest_isEmpty_False(){
		Queue<String> list = new TraineeQueue<>();
		list.add("");
		Assert.assertFalse(list.isEmpty());
	}
	@Test
	public void queueTest_Add_toArray_ArrayEquals(){
		Queue<String> list = new TraineeQueue<>();
		Queue<String> expected = new LinkedList<>();
		loadData(list, expected);		
		Assert.assertArrayEquals(expected.toArray(), list.toArray());
	}
	@Test
	public void queueTest_Contains_Equals(){
		Queue<String> list = new TraineeQueue<>();
		Queue<String> expected = new LinkedList<>();
		loadData(list, expected);	
		Assert.assertEquals(list.contains("U"), expected.contains("U"));
	}
	@Test
	public void queueTest_notContains_Equals(){
		Queue<String> list = new TraineeQueue<>();
		Queue<String> expected = new LinkedList<>();
		loadData(list, expected);	
		Assert.assertEquals(list.contains("X"), expected.contains("X"));
	}
	
	@Test
	public void queueTest_removeByObject_Equals(){
		Queue<String> list = new TraineeQueue<>();
		Queue<String> expected = new LinkedList<>();
		loadData(list, expected);
		list.remove("Q");
		expected.remove("Q");	
		Assert.assertArrayEquals(expected.toArray(), list.toArray());
	}
	@Test
	public void queueTest_remove_Equals(){
		Queue<String> list = new TraineeQueue<>();
		Queue<String> expected = new LinkedList<>();
		loadData(list, expected);
		Assert.assertEquals(expected.remove(), list.remove());
	}
	@Test
	public void queueTest_remove_ArrayEquals(){
		Queue<String> list = new TraineeQueue<>();
		Queue<String> expected = new LinkedList<>();
		loadData(list, expected);
		list.remove();
		expected.remove();	
		Assert.assertArrayEquals(expected.toArray(), list.toArray());
	}
	@Test(expected = NoSuchElementException.class)
	public void queueTest_removeFromEmpty_NoSuchElementException(){
		Queue<String> list = new TraineeQueue<>();
		list.remove();
	}
	@Test
	public void queueTest_clear_Equals(){
		Queue<String> list = new TraineeQueue<>();
		Queue<String> expected = new LinkedList<>();
		loadData(list, expected);
		list.clear();
		expected.clear();	
		Assert.assertArrayEquals(expected.toArray(), list.toArray());
	}
	@Test
	public void queueTest_poll_ArrayEquals(){
		Queue<String> list = new TraineeQueue<>();
		Queue<String> expected = new LinkedList<>();
		loadData(list, expected);
		list.poll();
		expected.poll();	
		Assert.assertArrayEquals(expected.toArray(), list.toArray());
	}
	@Test
	public void queueTest_poll_Equals(){
		Queue<String> list = new TraineeQueue<>();
		Queue<String> expected = new LinkedList<>();
		loadData(list, expected);
		Assert.assertEquals(expected.poll(), list.poll());
	}
	@Test
	public void queueTest_element_Equals(){
		Queue<String> list = new TraineeQueue<>();
		Queue<String> expected = new LinkedList<>();
		loadData(list, expected);
		Assert.assertEquals(expected.element(), list.element());
	}
	@Test
	public void queueTest_element_ArrayEquals(){
		Queue<String> list = new TraineeQueue<>();
		Queue<String> expected = new LinkedList<>();
		loadData(list, expected);
		list.element();
		expected.element();	
		Assert.assertArrayEquals(expected.toArray(), list.toArray());
	}
	@Test(expected = NoSuchElementException.class)
	public void queueTest_elementFromEmpty_NoSuchElementException(){
		Queue<String> list = new TraineeQueue<>();
		list.element();
	}
	@Test
	public void queueTest_peek_Equals(){
		Queue<String> list = new TraineeQueue<>();
		Queue<String> expected = new LinkedList<>();
		loadData(list, expected);
		Assert.assertEquals(expected.peek(), list.peek());
	}
	@Test
	public void queueTest_peek_ArrayEquals(){
		Queue<String> list = new TraineeQueue<>();
		Queue<String> expected = new LinkedList<>();
		loadData(list, expected);
		list.peek();
		expected.peek();	
		Assert.assertArrayEquals(expected.toArray(), list.toArray());
	}
}
