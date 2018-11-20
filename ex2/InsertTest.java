import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class InsertTest {

	private Set set;

	@Before
	public void init(){
	   set = new Set();
	}
	
	@Test
	public void test_AddToSet() {
		int [] expected  = new int [] {1};
		set.insert(1);
		assertArrayEquals(expected,set.toArray());
	}
	
	@Test
	public void test_AlreadyInSet() {
		int [] expected  = new int [] {1};
		set.insert(1);
		set.insert(1);
		assertArrayEquals(expected, set.toArray());
	}
	
	@Test
	public void test_InsertAsc() {
		int [] expected  = new int [] {1,2,3};
		set.insert(1);
		set.insert(2);
		set.insert(3);
		assertArrayEquals(expected, set.toArray());
	}

	
	@Test
	public void test_InsertDec() {
		int [] expected  = new int [] {1,2,3};
		set.insert(3);
		set.insert(2);
		set.insert(1);
		assertArrayEquals(expected, set.toArray());
	}


}
