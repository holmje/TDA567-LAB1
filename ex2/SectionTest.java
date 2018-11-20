import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SectionTest {

	private Set set;

	@Before
	public void init(){
	   set = new Set();
	}
	
	@Test
	public void test_RemoveItemsFromSet() {
		Set removeSet = new Set();
		Set expected = new Set();
		removeSet.insert(5);
		set.insert(5);
		set.section(removeSet);		
		assertArrayEquals(expected.toArray(), set.toArray());
	}
	
	@Test
	public void test_RemoveDoNotRemoveRandom_ASC() {
		Set removeSet = new Set();
		Set expected = new Set();
		removeSet.insert(3);
		set.insert(5);
		expected.insert(5);
		set.section(removeSet);		
		assertArrayEquals(expected.toArray(), set.toArray());
	}
	
	@Test
	public void test_RemoveDoNotRemoveRandom_DESC() {
		Set removeSet = new Set();
		Set expected = new Set();
		removeSet.insert(5);
		set.insert(3);
		expected.insert(3);
		set.section(removeSet);		
		assertArrayEquals(expected.toArray(), set.toArray());
	}

}
