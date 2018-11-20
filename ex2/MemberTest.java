import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class MemberTest {

	private Set set;

	@Before
	public void init(){
	   set = new Set();
	}
	
	@Test
	public void test_EmptyArray_ReturnFalse() {
		assertFalse(set.member(1));
	}
	
	@Test
	public void test_MemberGreaterThanMembers_ReturnFalse() {
		set.insert(2);
		set.insert(3);
		assertFalse(set.member(5));
	}

	@Test
	public void test_MembersContainsMember_ReturnTrue() {
		set.insert(1);
		assertTrue(set.member(1));
	}
	
	@Test
	public void test_DoesNotContainValueGraterOrEqualsToX_ReturnFalse() {
		set.insert(2);
		set.insert(3);
		assertFalse(set.member(1));
	}
}
