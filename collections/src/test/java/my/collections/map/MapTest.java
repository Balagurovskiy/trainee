package my.collections.map;

import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


public class MapTest {

	@Test
	public void mapTest_PutSize_Equals() {
		Map<Integer, String> map = new TraineeMap<Integer, String>();
		map.put(1, "FIRST");
		map.put(111, "SECOND");
		Assert.assertEquals(2, map.size());
	}
	
	@Test
	public void mapTest_PutGet_Equals() {
		Map<Integer, String> map = new TraineeMap<Integer, String>();
		map.put(1, "FIRST");
		map.put(111, "SECOND");
		map.put(6, "THIRD");
		Assert.assertEquals("THIRD", map.get(6));
	}
	@Test
	public void mapTest_Clear_SizeEquals() {
		Map<Integer, String> map = new TraineeMap<Integer, String>();
		map.put(1, "FIRST");
		map.put(111, "SECOND");
		map.put(6, "THIRD");
		map.clear();
		Assert.assertEquals(0, map.size());
	}
	@Test
	public void mapTest_resize_collision_NoException() {
		try {
			Map<String, String> map = new TraineeMap<String, String>();
			for (Integer i = 0; i < 100; i++) {
				map.put(String.valueOf(i), String.valueOf(i));
			}
			for (Integer i = 0; i < 10; i++) {
				map.put(String.valueOf(i), String.valueOf(i));
			}
		}catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void mapTest_Remove_ZeroSize() {
		Map<String, String> map = new TraineeMap<String, String>();
		for (Integer i = 0; i < 1000; i++) {
			map.put(String.valueOf(i), String.valueOf(i));
		}
		
		for (Integer i = 0; i < 1000; i++) {
			map.remove(String.valueOf(i));
		}
		Assert.assertEquals(0, map.size());
	}
	
	@Test
	public void mapTest_containsKey_True() {
		Map<String, String> map = new TraineeMap<String, String>();
		for (Integer i = 0; i < 1000; i++) {
			map.put(String.valueOf(i), String.valueOf(i));
		}
		Assert.assertTrue(map.containsKey("777"));
	}
	
	@Test
	public void mapTest_containsKey_False() {
		Map<String, String> map = new TraineeMap<String, String>();
		for (Integer i = 0; i < 1000; i++) {
			map.put(String.valueOf(i), String.valueOf(i));
		}
		Assert.assertFalse(map.containsKey("1111"));
	}
	@Test
	public void mapTest_containsValue_True() {
		Map<String, String> map = new TraineeMap<String, String>();
		for (Integer i = 0; i < 1000; i++) {
			map.put(String.valueOf(i), String.valueOf(i));
		}
		Assert.assertTrue(map.containsValue("777"));
	}
	@Test
	public void mapTest_containsValue_False() {
		Map<String, String> map = new TraineeMap<String, String>();
		for (Integer i = 0; i < 1000; i++) {
			map.put(String.valueOf(i), String.valueOf(i));
		}
		Assert.assertFalse(map.containsValue("1111"));
	}
}
