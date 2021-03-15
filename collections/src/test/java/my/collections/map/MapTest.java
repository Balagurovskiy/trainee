package my.collections.map;

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
}
