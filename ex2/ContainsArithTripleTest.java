import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ContainsArithTripleTest {

	private Set set;
	
	@Before
	public void init(){
	   set = new Set();
	}
	
	@Test
	public void test_ContainsArithTriple() {
		set.insert(1);
		set.insert(2);
		set.insert(3);
		assertTrue(set.containsArithTriple());
	}
	
	@Test
	public void test_NoValue() {
		assertFalse(set.containsArithTriple());
	}
	
	@Test
	public void test_DoNotContainArithTriple() {
		set.insert(89);
		set.insert(44);
		set.insert(56);
		assertFalse(set.containsArithTriple());
	}
	
}
