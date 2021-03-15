package my.collections.linkedlist;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LinkedListTest {

	@Test(expected=IndexOutOfBoundsException.class)
	public void linkedListGetTest_ExpectedException() {
		new TraineeLinkedList<>().get(0);
	}
	
	private void loadData(List<String> l) {
		String testData = "qwertyuiop";
		for (int i = 0; i < testData.length(); i++) {
			l.add(testData.substring(0, i));
		}
	}
	
	@Test
	@Order(1)//not working
	public void linkedListAddTest_ExpectedArrayEquals() {
		List<String> list = new TraineeLinkedList<>();
		List<String> expectedList = new LinkedList<>();
		loadData(list);
		loadData(expectedList);
		Assert.assertArrayEquals(list.toArray(), expectedList.toArray());
	}
	@Test
	public void linkedListGetTest_ExpectedDataEquals() {
		List<String> list = new TraineeLinkedList<>();
		List<String> expectedList = new LinkedList<>();
		loadData(list);
		loadData(expectedList);
		Assert.assertEquals(list.get(0), expectedList.get(0));
		Assert.assertEquals(
						list.get(list.size() - 1), 
						expectedList.get(expectedList.size() - 1)
						);
	}
	@Test
	@Order(2)
	public void linkedListSizeTest_ExpectedEquals() {
		List<String> list = new TraineeLinkedList<>();
		List<String> expectedList = new LinkedList<>();
		loadData(list);
		loadData(expectedList);
		Assert.assertEquals(list.size(), expectedList.size());
	}
	@Test
	@Order(3)
	public void linkedListClearTest_ExpectedArrayEquals() {
		List<String> list = new TraineeLinkedList<>();
		List<String> expectedList = new LinkedList<>();
		loadData(list);
		loadData(expectedList);
		list.clear();
		expectedList.clear();
		Assert.assertArrayEquals(list.toArray(), expectedList.toArray());
	}

	@Test 
	public void linkedListAddWithIndexTest_ExpectedArrayEquals() {
		List<String> list = new TraineeLinkedList<>();
		List<String> expectedList = new LinkedList<>();
		loadData(list);
		loadData(expectedList);
		list.add(0, "X");
		list.add(list.size()/2, "X");
		list.add(list.size(), "X");
		expectedList.add(0, "X");
		expectedList.add(expectedList.size()/2, "X");
		expectedList.add(expectedList.size(), "X");
		Assert.assertArrayEquals(list.toArray(), expectedList.toArray());
	}
	@Test
	public void linkedListIsEmptyTest_ExpectedEquals() {
		List<String> list = new TraineeLinkedList<>();
		List<String> expectedList = new LinkedList<>();
		Assert.assertEquals(list.isEmpty(), expectedList.isEmpty());
	}
	@Test
	public void linkedListAddAllTest_ExpectedArrayEquals_1() {
		List<String> list = new TraineeLinkedList<>();
		List<String> expectedList = new LinkedList<>();
		loadData(list);
		loadData(expectedList);
		list.addAll(list);
		expectedList.addAll(expectedList);

		Assert.assertArrayEquals(list.toArray(), expectedList.toArray());
	}
	@Test
	public void linkedListAddAllTest_ExpectedArrayEquals_2() {
		List<String> list = new TraineeLinkedList<>();
		List<String> expectedList = new LinkedList<>();
		loadData(list);
		loadData(expectedList);
		list.addAll(list.size() / 2, list);
		expectedList.addAll(expectedList.size() /2, expectedList);
		
		Assert.assertArrayEquals(list.toArray(), expectedList.toArray());
	}
	@Test
	public void linkedListIndexOfTest_ExpectedIndexEquals() {
		String target = "X";
		List<String> list = new TraineeLinkedList<>();
		List<String> expectedList = new LinkedList<>();
		loadData(list);
		loadData(expectedList);
		list.add(list.size() / 2, target);
		expectedList.add(expectedList.size() /2, target);
		
		Assert.assertEquals(list.indexOf(target), expectedList.indexOf(target));
	}
	@Test
	public void linkedListLastIndexOfTest_ExpectedIndexEquals() {
		String target = "X";
		List<String> list = new TraineeLinkedList<>();
		List<String> expectedList = new LinkedList<>();
		loadData(list);
		loadData(expectedList);
		list.add(list.size() / 2, target);
		expectedList.add(expectedList.size() /2, target);
		list.add(target);
		expectedList.add(target);
		Assert.assertEquals(list.indexOf(target), expectedList.indexOf(target));
	}
	@Test
	public void linkedListRemoveTest_ExpectedTrue() {
		String target = "X";
		List<String> list = new TraineeLinkedList<>();
		list.add(target);
		Assert.assertTrue(list.remove(target));
	}
	@Test
	public void linkedListRemoveTest_ExpectedFalse() {
		String target = "X";
		List<String> list = new TraineeLinkedList<>();
		Assert.assertFalse(list.remove(target));
	}
	@Test
	public void linkedListRemoveTest_ExpectedArrayEquals() {
		String target = "X";
		List<String> list = new TraineeLinkedList<>();
		List<String> expectedList = new LinkedList<>();
		list.add(target);
		expectedList.add(target);
		list.remove(target);
		expectedList.remove(target);
		Assert.assertArrayEquals(list.toArray(), expectedList.toArray());
	}
	@Test
	public void linkedListRemoveByIndexTest_ExpectedRemovedEquals() {
		String target = "X";
		List<String> list = new TraineeLinkedList<>();
		list.add(target);
		Assert.assertEquals(list.remove(0), target);
	}
	@Test
	public void linkedListRemoveByIndexTest_ExpectedArrayEquals() {
		String target = "X";
		List<String> list = new TraineeLinkedList<>();
		List<String> expectedList = new LinkedList<>();
		list.add(target);
		expectedList.add(target);
		list.remove(0);
		expectedList.remove(0);
		Assert.assertArrayEquals(list.toArray(), expectedList.toArray());
	}
//	for (int i = 0; i < list.size(); i++) {
//		System.out.println(list.get(i));
//	}
//	System.out.println("---------------------------------");
//	expectedList.forEach(l->System.out.println(l));

}
