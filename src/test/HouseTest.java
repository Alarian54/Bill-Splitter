//package test;
//import org.junit.jupiter.api.AfterEach;
//import model.house.House;
//import model.person.PayingPerson;
//import model.person.Person;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//
//// Consists of tests for the main functions of the program,
//// which also include People and Transaction functions
//public class HouseTest {
//
//    private House house;
//    private Person person1;
//    private Person person2;
//    private Person person3;
//
//    // Required for testing the output of print statements
//    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
//    private final PrintStream originalOut = System.out;
//    private final PrintStream originalErr = System.err;
//
//    @BeforeEach
//    public void runBefore() {
//        house = new House("House");
//        person1 = new PayingPerson("Person 1", 0);
//        person2 = new PayingPerson("Person 2", 0);
//        person3 = new PayingPerson("Person 3", 0);
//        System.setOut(new PrintStream(outContent));
//        System.setErr(new PrintStream(errContent));
//    }
//
//    @Test
//    public void testSizeEmpty() {
//        assertEquals(house.size(), 0);
//    }
//
//    @Test
//    public void testSizeOne() {
//        house.add(person1);
//        assertEquals(house.size(), 1);
//    }
//
//    @Test
//    public void testSizeMany() {
//        house.add(person1);
//        house.add(person2);
//        house.add(person3);
//        assertEquals(house.size(), 3);
//    }
//
//    @Test
//    public void testAddOne() {
//        house.add(person1);
//        assertEquals(person1, house.getPeople().get(0));
//    }
//
//    @Test
//    public void testAddMany() {
//        house.add(person1);
//        house.add(person1);
//        house.add(person1);
//        assertEquals(house.size(), 1);
//    }
//
//    @Test
//    public void testCheckBalanceOne() {
//        house.add(person1);
//        assertTrue(house.checkBalance());
//    }
//
//    @Test
//    public void testCheckBalanceMany() {
//        house.add(person1);
//        house.add(person2);
//        house.add(person3);
//        assertTrue(house.checkBalance());
//    }
//
//    @Test
//    public void testPrintStatement() {
//        house.print();
//        assertEquals("House", outContent.toString());
//        //assertEquals("House\n - No inhabitants yet", outContent.toString());
//    }
//
//    @AfterEach
//    public void restoreStreams() {
//        System.setOut(originalOut);
//        System.setErr(originalErr);
//    }
//}
//
//// Used to test print statements:
//// https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
