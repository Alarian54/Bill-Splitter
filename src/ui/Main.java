package ui;
import model.house.House;
import model.house.HouseList;
import model.other.Loadable;
import model.other.Saveable;
import model.person.ObservingPerson;
import model.person.PayingPerson;
import model.person.Person;
import ui.exceptions.DuplicateException;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Starts the UI
public class Main implements Loadable, Saveable {

    // MODIFIES: scanner, cmd, houses
    // EFFECTS:  Creates the scanner, input and house list objects, then starts the UI
    public static void main(String[] args) throws IOException {
        Main m = new Main();
        HouseList houses = new HouseList();
        houses.setHouses(m.load("houses.txt"));
        StartUI.start(houses);
    }

    // REQUIRES: A properly formatted file
    // MODIFIES: houses
    // EFFECTS:  Loads the list of houses, along with their inhabitants
    //           from the given file
    public ArrayList<House> load(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        ArrayList<House> houses = new ArrayList<>();
        for (String line : lines) {
            char c = line.charAt(0);
            if (c==('-')) {
                if (line.contains("n/a")) {
                    ObservingPerson person = new ObservingPerson(line.substring(2, line.length()-4));
                    try {
                        houses.get(houses.size()-1).add(person);
                    } catch (DuplicateException e) {
                        System.out.println("Format of file has been modified...");
                        System.out.println("Cannot have more than one House with the same name");
                        System.exit(0);
                    }
                } else {
                    String[] split = line.split(" ");
                    int balance = 0, size = 0;
                    try {
                        String number = split[split.length-1];
                        balance = Integer.parseInt(number);
                        size = number.length()+1;
                    } catch (NumberFormatException e) {
                        System.out.println("Format of file has been modified...");
                        System.out.println("Use format \" - John Smith 123\" for PayingPerson entries");
                        System.exit(0);
                    }
                    PayingPerson person = new PayingPerson(line.substring(2, line.length()-size), balance);
                    try {
                        houses.get(houses.size()-1).add(person);
                    } catch (DuplicateException e) {
                        System.out.println("Format of file has been modified...");
                        System.out.println("Cannot have more than one House with the same name");
                        System.exit(0);
                    }
                }
            } else {
                houses.add(new House(line));
            }
        }
        return houses;
    }

    // EFFECTS: Saves the contents of houses to the given file
    public void save(ArrayList<House> houses, String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(fileName,"UTF-8");
        for (House house : houses) {
            writer.println(house.getName());
            for (Person person : house.getPeople()) {
                writer.println("- " + person.getName() + " " + person.balanceString());
            }
        }
        writer.close();
    }

}
