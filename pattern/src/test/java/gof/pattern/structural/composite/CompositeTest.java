package gof.pattern.structural.composite;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CompositeTest {

	private static Box rootBox;
	
	@BeforeClass
	public static void init() {
		rootBox = new Box("Root", 0);
		rootBox.add( new Item("Item in the root box", 1) );
		Box innerBox = new Box("Inner", 0);
		rootBox.add(innerBox);
		innerBox.add(new Item("First item from inner box", 1) );
		innerBox.add(new Item("Second item from inner box", 1) );
		Box secretBox = new Box("Secret", 0);
		innerBox.add(secretBox);
		secretBox.add(new Item("Item from secret box", 1) );
	}
	
	public int recursiveSum(Box box) {
		List<Composite> stash = box.getStash();
		int value = 0;
		for(Composite s : stash) {
			value += s.getValue();
			if (s instanceof Box) {
				value += recursiveSum((Box)s);
			}
		}
		return value;
	}
	
	@Test
	public void checkSumFromCompositeTree_ExpectedEqual() {
		Assert.assertEquals(recursiveSum(rootBox), 4);
	}
}
