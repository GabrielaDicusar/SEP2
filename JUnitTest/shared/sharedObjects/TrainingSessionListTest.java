package shared.sharedObjects;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TrainingSessionListTest {

    private ArrayList<String > a;

    @Before
    public void setUp() throws Exception {
        a = new ArrayList<>();
    }

    @Test
    public void addSession() {
        //arrange
        // ArrayList<String> a = new ArrayList<>();
        //act
        a.add("A");
        a.add("B");
        a.add("C");
        a.add("D");
        //assert
        assertTrue(a.contains("A"));
        assertTrue(a.contains("B"));
        assertTrue(a.contains("C"));
        assertTrue(a.contains("D"));
    }

    @Test
    public void removeTrainingSession() {
        assertFalse(a.contains("A"));
    }

    @Test
    public void getTrainingSession() {
        assertThrows(IndexOutOfBoundsException.class, ()-> a.get(6));
    }

    @Test
    public void addParticipant() {
        //arrange
        //ArrayList<String> a = new ArrayList<>();
        //act
        a.add("A");
        //assert
        assertTrue(a.contains("A"));
    }

    @Test
    public void getTrainingSessionByIndex() {
        assertThrows(IndexOutOfBoundsException.class, ()-> a.get(6));
    }
}