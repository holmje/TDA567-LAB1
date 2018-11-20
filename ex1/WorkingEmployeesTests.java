import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/*
requires:
	  starttime >= 0 and endtime < size
	ensures:
	  if starttime <= endtime then
	    returns an array with distinct strings -- a string appears in the return array if and only if
	    it appears in the workingEmployees of at least one hour in the interval starttime to endtime
	  otherwise
	    returns an empty array
	  and in either case the schedule is unchanged
	  */

public class WorkingEmployeesTests {

	private int work_hours;
	private WorkSchedule schedule;
	private String [] empty_array;
	private String [] distinct_array;
	
	@Before
	public void setUp() {
		work_hours = 24;
		schedule = new WorkSchedule(work_hours);
		empty_array = new String [] {};
		distinct_array = new String [] {"Jesper"};
	}
	
	//Should be added due to StartTime < EndTime
	@Test
	public void StartTimeLessThanEndTime_ReturnDistinctArray(){
		schedule.setRequiredNumber(1, 0, 23);
		schedule.addWorkingPeriod("Jesper",0,23);
		assertArrayEquals(distinct_array, schedule.workingEmployees(0,6));
	}
	
	//Should be added due to StartTime = EndTime
	@Test
	public void StartTimeEqualsEndTime_ReturnDistinctArray(){		
		schedule.setRequiredNumber(1, 6, 6);
		schedule.addWorkingPeriod("Jesper",6,6);
		assertArrayEquals(distinct_array,schedule.workingEmployees(6,6));
		
	}
	
	//Should not be added due to StartTime > EndTime
	@Test
	public void StartTimeGreaterThanEndTime_ReturnEmptyArray(){		
		schedule.setRequiredNumber(1, 0, 23);
		schedule.addWorkingPeriod("Jesper",7,2);
		for(int i = 0; i < work_hours; i++)
			assertArrayEquals(empty_array, schedule.readSchedule(i).workingEmployees);
					
	}
	
	//BUG: No workers are added due to different work hours
	@Test
	public void AllWorkersAtDifferentTimes_ReturnDistinctArray(){		
		schedule.setRequiredNumber(4, 0, 23);
		schedule.addWorkingPeriod("Jesper",2,4);
		schedule.addWorkingPeriod("Johan",6,8);
		schedule.addWorkingPeriod("Kasper",10,12);
		schedule.addWorkingPeriod("Dan",14,18);
		
		String [] expected = new String [] {"Jesper","Johan","Kasper","Dan"};
		assertArrayEquals(expected, schedule.workingEmployees(0,23));

	}

	//BUG: No workers are added due to different work hours
	@Test
	public void AllWorkersAtSameTimes_ReturnDistinctArray(){		
		schedule.setRequiredNumber(4, 0, 23);
		schedule.addWorkingPeriod("Jesper",6,14);
		schedule.addWorkingPeriod("Johan",6,14);
		schedule.addWorkingPeriod("Kasper",6,14);
		schedule.addWorkingPeriod("Dan",6,14);
		
		String [] expected = new String [] {"Jesper","Johan","Kasper","Dan"};
		assertArrayEquals(expected, schedule.workingEmployees(0,23));

	}

}
