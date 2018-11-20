
import org.junit.Before;
import org.junit.Test;
import org.junit.*;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * requires: employee is a non-null string
 * ensures:
 * if starttime < 0 or endtime >= size or starttime > endtime then
 *  returns false and the schedule is unchanged
 *  otherwise
 *      if for any hour in the interval starttime to endtime the length of workingEmployees is equal to requiredNumber then
 *       returns false and the schedule is unchanged
 *       otherwise
 *          if for any hour in the interval starttime to endtime there is a string in workingEmployees which equal employee then
 *           returns false and the schedule is unchanged
 *           otherwise
 *           returns true,
 *           for i between starttime and endtime, workingEmployees contain a string equal to employee and
 *           the rest of the schedule is unchanged
 */
public class AddWorkingPeriodTests {


    WorkSchedule ws;
    int size;

    String[] empty;
    String[] jesper;
    String[] johan;
    String[] jesperjohan;


    @Before
    public void setUp(){
        size = 24;
        ws = new WorkSchedule(24);

        empty = new String[]{};
        jesper = new String[]{"Jesper"};
        johan = new String[]{"Johan"};
        jesperjohan = new String[]{"Jesper", "Johan"};
    }

    @Test
    public void addWorkingPeriod_AddWorkerWhenNoneIsNeeded_DoNotAdd(){
        ws.addWorkingPeriod(jesper[0], 3, 6);

        for (int i = 0; i < size; i++){
            assertArrayEquals(empty, ws.readSchedule(i).workingEmployees);
        }
    }

    @Test
    public void addWorkingPeriod_AddWorkerWhenNoneIsNeeded_ReturnFalse(){
        assertFalse(ws.addWorkingPeriod(jesper[0], 3, 6));
    }

    @Test
    public void addWorkingPeriod_AddWorkerOnePerson_AddWorker(){
        ws.setRequiredNumber(1, 1, 6);
        ws.addWorkingPeriod(jesper[0], 2, 5);

        for (int i = 0; i < size; i++){
            if (i >=2 && i<= 5) {
                assertArrayEquals(jesper, ws.readSchedule(i).workingEmployees);
            }
            else {
                assertArrayEquals(empty, ws.readSchedule(i).workingEmployees);
            }
        }

    }

    @Test
    public void addWorkingPeriod_AddWorkerOnePerson_ReturnTrue(){
        ws.setRequiredNumber(1, 1, 6);
        assertTrue(ws.addWorkingPeriod(jesper[0], 2, 5));
    }

    @Test
    public void addWorkingPeriod_StartTimeLessThanZero_DoNotAddWorker(){
        ws.setRequiredNumber(1, 1, 3);
        ws.addWorkingPeriod(jesper[0], -1, 3);

        for (int i = 0; i < size; i++){
            assertArrayEquals(empty, ws.readSchedule(i).workingEmployees);
        }
    }

    @Test
    public void addWorkingPeriod_StartTimeLessThanZero_returnFalse(){
        ws.setRequiredNumber(1, 3, 6);
        assertFalse(ws.addWorkingPeriod(jesper[0], -3, 6));
    }

    @Test
    public void addWorkingPeriod_EndTimeIsGreaterOrEqualToSize_DoNotAddWorker(){
        ws.setRequiredNumber(1, 3, 6);
        ws.addWorkingPeriod(jesper[0], 6, 24);

        for (int i = 0; i < size; i++){
            assertArrayEquals(empty, ws.readSchedule(i).workingEmployees);
        }
    }

    @Test
    public void addWorkingPeriod_EndTimeIsGreaterOrEqualToSize_returnFalse(){
        ws.setRequiredNumber(1, 3, 6);
        assertFalse(ws.addWorkingPeriod(jesper[0], 6, 24));
    }


    @Test
    public void addWorkingPeriod_StarttimeIsGreaterThanEndTime_DoNotAddWorker(){
        ws.setRequiredNumber(1, 3, 6);
        ws.addWorkingPeriod(jesper[0], 6, 3);

        for (int i = 0; i < size; i++){
            assertArrayEquals(empty, ws.readSchedule(i).workingEmployees);
        }
    }

    //BUG: returns true when it should return false!
    @Test
    public void addWorkingPeriod_StarttimeGreaterThanEndTime_returnFalse(){
        ws.setRequiredNumber(1, 3, 6);
        assertFalse(ws.addWorkingPeriod(jesper[0], 6, 3));
    }

    @Test
    public void addWorkingPeriod_AddMoreWorkersThanNeeded_DoNotAddSecondWorker(){
        ws.setRequiredNumber(1,3,6);
        ws.addWorkingPeriod(jesper[0], 3, 6);
        ws.addWorkingPeriod(johan[0], 3, 6);

        for (int i = 0; i < size; i++){
            if (i >= 3 && i <= 6){
                assertArrayEquals(jesper, ws.readSchedule(i).workingEmployees);
            } else {
                assertArrayEquals(empty, ws.readSchedule(i).workingEmployees);
            }
        }
    }

    @Test
    public void addWorkingPeriod_AddMoreWorkersThanNeeded_ReturnFalse(){
        ws.setRequiredNumber(1,3,6);
        ws.addWorkingPeriod(jesper[0], 3, 6);
        assertFalse(ws.addWorkingPeriod(johan[0], 3, 6));
    }

    @Test
    public void addWorkingPeriod_AddLessWorkersThanNeeded_AddWorkers(){
        ws.setRequiredNumber(3,3,6);
        ws.addWorkingPeriod(jesper[0], 3, 6);
        ws.addWorkingPeriod(johan[0], 3, 6);

        for (int i = 0; i < size; i++){
            if (i >= 3 && i <= 6){
                assertArrayEquals(jesperjohan, ws.readSchedule(i).workingEmployees);
            } else {
                assertArrayEquals(empty, ws.readSchedule(i).workingEmployees);
            }
        }
    }

    @Test
    public void addWorkingPeriod_AddLessWorkersThanNeeded_ReturnTrue(){
        ws.setRequiredNumber(3,3,6);
        ws.addWorkingPeriod(jesper[0], 3, 6);
        assertTrue(ws.addWorkingPeriod(johan[0], 3, 6));
    }

    @Test
    public void addWorkingPeriod_AddWorkerToLargerPeriodThanNeeded_DoNotAddWorker(){
        ws.setRequiredNumber(1, 3, 6);
        ws.addWorkingPeriod(jesper[0], 1, 6);

        for (int i = 0; i < size; i++){
            assertArrayEquals(empty, ws.readSchedule(i).workingEmployees);
        }
    }

    @Test
    public void addWorkingPeriod_AddWorkerToLargerPeriodThanNeeded_ReturnFalse(){
        ws.setRequiredNumber(1, 3, 6);
        assertFalse(ws.addWorkingPeriod(jesper[0], 1, 6));
    }

    @Test
    public void addWorkingPeriod_StartTimeEqualToEndTime_AddWorker(){
        ws.setRequiredNumber(1, 3, 3);
        ws.addWorkingPeriod(jesper[0],3, 3);

        for (int i = 0; i < size; i++){
            if (i == 3){
                assertArrayEquals(jesper, ws.readSchedule(i).workingEmployees);
            } else {
                assertArrayEquals(empty, ws.readSchedule(i).workingEmployees);
            }
        }
    }

    @Test
    public void addWorkingPeriod_StartTimeEqualToEndTime_ReturnTrue(){
        ws.setRequiredNumber(1, 3, 3);
        assertTrue(ws.addWorkingPeriod(jesper[0],3, 3));
    }

    @Test
    public void addWorkingPeriod_AddSameWorkerMultipleTimes_DoNotAddWorkerSecondTime(){
        ws.setRequiredNumber(1, 3, 6);
        ws.addWorkingPeriod(jesper[0], 3, 6);
        ws.addWorkingPeriod(jesper[0], 3, 6);

        for (int i = 0; i < size; i++) {
            if (i >= 3 && i <= 6){
                assertArrayEquals(jesper, ws.readSchedule(i).workingEmployees);
            } else {
                assertArrayEquals(empty, ws.readSchedule(i).workingEmployees);
            }
        }
    }

    @Test
    public void addWorkingPeriod_AddSameWorkerMultipleTimes_ReturnFalse(){
        ws.setRequiredNumber(1,3,6);
        ws.addWorkingPeriod(jesper[0], 3, 6);
        assertFalse(ws.addWorkingPeriod(jesper[0],3,6));
    }

}
