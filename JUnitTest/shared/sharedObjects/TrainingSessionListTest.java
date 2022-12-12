package shared.sharedObjects;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TrainingSessionListTest {

    private TrainingSessionList testList;

    @Before
    public void setUp() throws Exception {
        testList = new TrainingSessionList();
    }

    @Test
    public void constructor() {
        TrainingSessionList obj = new TrainingSessionList();
        assertNotNull(obj);
    }

    @Test
    public void testAddingOneSessionToTrainingSession() {
        //Creating the temp training session
        TrainingSession testTrainingSession = new TrainingSession("Yoga", "12:00", 10, new Account(3, "Chris", "Hunt", "@", "4545", "chris", "chris"), LocalDate.now());

        //Add a temp session to the training session list
        testList.addSession(testTrainingSession);

        //Asserting true if it contains the added session
        assertTrue(testList.contain(testTrainingSession));
    }
    @Test
    public void testAddingMultipleSessionsToTrainingSession() {
        //Creating the temp training sessions
        TrainingSession testTrainingSession1 = new TrainingSession("Yoga", "12:00", 10, new Account(3, "Chris", "Hunt", "@", "4545", "chris", "chris"), LocalDate.now());
        TrainingSession testTrainingSession2 = new TrainingSession("Yoga", "13:00", 10, new Account(3, "Chris", "Hunt", "@", "4545", "chris", "chris"), LocalDate.now());
        TrainingSession testTrainingSession3 = new TrainingSession("Yoga", "14:00", 10, new Account(3, "Chris", "Hunt", "@", "4545", "chris", "chris"), LocalDate.now());

        //Adding created training sessions
        testList.addSession(testTrainingSession1);
        testList.addSession(testTrainingSession2);
        testList.addSession(testTrainingSession3);

        //Checking if the training sessions were added
        assertTrue(testList.contain(testTrainingSession1));
        assertTrue(testList.contain(testTrainingSession2));
        assertTrue(testList.contain(testTrainingSession3));
    }

    @Test
    public void testAddingNullObjectToTrainingSessionListResultsInIllegalArgumentException() {
        //Throws an exception if the object is null
        assertThrows(IllegalArgumentException.class,()-> testList.addSession(null));
    }
    @Test
    public void testRemovingTrainingSession() {
        //Creating the temp training session
        TrainingSession testTrainingSession1 = new TrainingSession("Yoga", "12:00", 10, new Account(3, "Chris", "Hunt", "@", "4545", "chris", "chris"), LocalDate.now());

        //Adding a session to make sure there is something to remove
        testList.addSession(testTrainingSession1);

        //Checking if the added session is in the list
        assertEquals(1, testList.size());

        //Removing the session
        testList.removeTrainingSession(testTrainingSession1);

        //Checking if the training session was removed correctly
        assertEquals(0, testList.size());

    }

    @Test
    public void testGettingElementByIndexOutOfBoundResultsInIndexOutOfBoundsException() {
        //Testing the method to get training session with a value out of bound
        assertThrows(IndexOutOfBoundsException.class, ()-> testList.getTrainingSessionByIndex(3));
    }

    @Test
    public void testAddingNullObjectToParticipantsResultsInIllegalArgumentException() {
        //Testing the method to add participant with a null object
        assertThrows(IllegalArgumentException.class,()-> testList.addParticipant(null, null));
    }
}