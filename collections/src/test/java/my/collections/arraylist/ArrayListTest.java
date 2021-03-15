package my.collections.arraylist;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ArrayListTest {

	@Test
	public void arrayListTest_ConstructorWithSize_SizeEquals(){
		List<String> list = new TraineeArrayList<>(5);
		Assert.assertEquals(list.size(), 0);
	}
	@Test(expected=IllegalArgumentException.class)
	public void arrayListTest_ConstructorWithSize_IllegalArgumentException(){
		List<String> list = new TraineeArrayList<>(-1);
	}
	
	@Test
	public void arrayListTest_isEmpty_EqualsTrue(){
		List<String> list = new TraineeArrayList<>();
		Assert.assertTrue(list.isEmpty());
	}
	@Test
	public void arrayListTest_ConstructorWithCollection_SizeEquals(){
		int expectedSize = 11;
		List<String> expected = new ArrayList<>(expectedSize);
		List<String> list = new TraineeArrayList<>( expected );
		Assert.assertEquals(list.size(), expected.size());
	}
	private void loadData(List<String> l) {
		String testData = "ABC";
		for (int i = 1; i <= testData.length(); i++) {
			l.add(testData.substring(i - 1, i));
		}
	}
	@Test
	public void arrayListTest_Add_ArrayEquals(){
		List<String> expected = new ArrayList<>();
		List<String> list = new TraineeArrayList<>();
		loadData(list);
		loadData(expected);
		Assert.assertArrayEquals(list.toArray(), expected.toArray());
	}
	
	@Test
	public void arrayListTest_AddWithIndex_ArrayEquals(){
		List<String> expected = new ArrayList<>();
		List<String> list = new TraineeArrayList<>();
		loadData(list);
		loadData(expected);
		list.add(1, "X");
		expected.add(1, "X");
		Assert.assertArrayEquals(list.toArray(), expected.toArray());
	}
	
	@Test
	public void arrayListTest_AddGet_Equals(){
		int expIndex = 1;
		List<String> expected = new ArrayList<>();
		List<String> list = new TraineeArrayList<>();
		loadData(list);
		loadData(expected);
		Assert.assertEquals(list.get(expIndex), list.get(expIndex));
	}
	@Test
	public void arrayListTest_removeByIndex_ArrayEquals(){
		int expIndex = 1;
		List<String> expected = new ArrayList<>();
		List<String> list = new TraineeArrayList<>();
		loadData(list);
		loadData(expected);
		list.remove(expIndex);
		expected.remove(expIndex);
		Assert.assertArrayEquals(list.toArray(), expected.toArray());
	}
	@Test(expected=IndexOutOfBoundsException.class)
	public void arrayListTest_GetFromEmpty_IndexOutOfBoundsException(){
		 new TraineeArrayList<>().get(0);
	}
	@Test
	public void arrayListTest_clear_ArrayEquals(){
		int expIndex = 1;
		List<String> expected = new ArrayList<>();
		List<String> list = new TraineeArrayList<>();
		loadData(list);
		loadData(expected);
		list.clear();
		expected.clear();
		Assert.assertArrayEquals(list.toArray(), expected.toArray());
	}
	@Test
	public void arrayListTest_indexOf_IndexEquals(){
		int expIndex = 1;
		List<String> expected = new ArrayList<>();
		List<String> list = new TraineeArrayList<>();
		loadData(list);
		loadData(expected);
		String target = "B";
		Assert.assertEquals(list.indexOf(target), expected.indexOf(target));
	}
	@Test
	public void arrayListTest_LastIndexOf_IndexEquals(){
		int expIndex = 1;
		List<String> expected = new ArrayList<>();
		List<String> list = new TraineeArrayList<>();
		loadData(list);
		loadData(expected);
		String target = "A";
		Assert.assertEquals(list.lastIndexOf(target), expected.lastIndexOf(target));
	}
	@Test
	public void arrayListTest_RemoveByObject_ArrayEquals(){
		int expIndex = 1;
		List<String> expected = new ArrayList<>();
		List<String> list = new TraineeArrayList<>();
		loadData(list);
		loadData(expected);
		String target = "A";
		list.remove(target);
		expected.remove(target);
		Assert.assertArrayEquals(list.toArray(), expected.toArray());
	}
	@Test
	public void arrayListTest_contains_True(){
		List<String> list = new TraineeArrayList<>();
		loadData(list);
		String target = "A";
		Assert.assertTrue(list.contains(target));
	}
    
}
