//package test;
//import model.house.House;
//import model.other.Loadable;
//import model.other.Saveable;
//import model.person.PayingPerson;
//import model.person.Person;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ui.*;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//// Consists of tests for saving a list of houses to a file and loading the same list
//public class SaveLoadTest {
//    ArrayList<House> houses;
//    Saveable s;
//    Loadable l;
//
//    @BeforeEach
//    public void runBefore() {
//        Saveable s = new Main();
//        Loadable l = new Main();
//        houses = new ArrayList<>();
//        House house1 = new House("house1");
//        House house2 = new House("house2");
//        House house3 = new House("house3");
//        Person person1 = new PayingPerson("person1", 0);
//        Person person2 = new PayingPerson("person2", 0);
//        Person person3 = new PayingPerson("person3", 0);
//        Person person4 = new PayingPerson("person4", 0);
//        Person person5 = new PayingPerson("person5", 0);
//        house1.add(person1);
//        house1.add(person2);
//        house2.add(person3);
//        house2.add(person4);
//        house2.add(person5);
//        houses.add(house1);
//        houses.add(house2);
//        houses.add(house3);
//    }
//
//    @Test
//    public void saveAndLoadTest() throws IOException {
//        s.save(houses, "test.txt");
//        ArrayList<House> loadedHouses;
//        loadedHouses = l.load("test.txt");
//        assertEquals(loadedHouses.size(), 3);
//        assertEquals(loadedHouses.get(0).size(), 2);
//        assertEquals(loadedHouses.get(1).size(), 3);
//        assertEquals(loadedHouses.get(2).size(), 0);
//    }
//
//}
