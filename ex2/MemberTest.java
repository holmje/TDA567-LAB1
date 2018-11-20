import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class MemberTest {

	private Set set;

	@Before
	public void init(){
	   set = new Set();
	}
	
	
	/*  public boolean member(int x) {
    for (int i = 0; i < a.size(); i++) {
      if (a.get(i) > x) {					//IF array contains a value greater than x return false
        return false;		
      } else {
        if (a.get(i) == x) {				//IF array contains value of x return true;
          return true;
        }
      }
    }
    return false;							//IF array does not contain a value greater or equals to x return false
  }*/
	
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
	public void test_DoesNotContainsValueGraterOrEqualsToX_ReturnFalse() {
		set.insert(2);
		set.insert(3);
		assertFalse(set.member(1));
	}
}
