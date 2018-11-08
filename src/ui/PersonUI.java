package ui;
import model.house.HouseList;
import model.person.ObservingPerson;
import model.person.PayingPerson;
import ui.exceptions.DuplicateException;

import java.io.IOException;
import java.util.Scanner;

// The section of the UI which deals with creating Person objects
public class PersonUI {

    // Used to scan input strings
    private static Scanner scanner = new Scanner(System.in);
    private static String cmd = "";

    // MODIFIES: scanner, cmd, houses
    // EFFECTS:  Takes an input for the person's name and then adds them as an inhabitant
    //           of the current house if a further "y" or "yes" is entered. Then, calls insideHouse
    public static void newPerson(HouseList houses, int house) throws IOException {
        System.out.println("Enter the person's name:");
        String name = scanner.nextLine();
        if (houses.get(house).checkPerson(new PayingPerson(name, 0)) || houses.get(house).checkPerson(new ObservingPerson(name))) {
            System.out.println("Each house member must have a different name.");
            newPerson(houses, house);
        }
        makeChoice(houses, house, name);
    }

    private static void makeChoice(HouseList houses, int house, String name) throws IOException {
        System.out.println("Do you want to add " + name + " as a paying or observing person?");
        cmd = scanner.nextLine();
        cmd = cmd.toLowerCase();
        if (cmd.contains("pay")) {
            System.out.println("You have chosen to add a new paying person.");
            newPayingPerson(houses, house, name);
        } else if (cmd.contains("observ")) {
            System.out.println("You have chosen to add a new observing person.");
            newObservingPerson(houses, house, name);
        } else {
            makeChoice(houses, house, name);
        }
    }

    private static void newPayingPerson(HouseList houses, int house, String name) throws IOException {
        System.out.println("Do you want to add " + name + "? Type Yes if so.");
        cmd = scanner.nextLine();
        cmd = cmd.toLowerCase();
        if (cmd.equals("y") || cmd.equals("yes")){
            try {
                houses.get(house).add(new PayingPerson(name, 0));
                System.out.println(name + " has been added.");
            } catch (DuplicateException e) {
                System.out.println("Each house member must have a different name.");
                newPayingPerson(houses, house, name);
            }
        }
        else {
            System.out.println(name + " was not added.");
        }
        HouseUI.insideHouse(houses, house);
    }

    private static void newObservingPerson(HouseList houses, int house, String name) throws IOException {
        System.out.println("Do you want to add " + name + "? Type Yes if so.");
        cmd = scanner.nextLine();
        cmd = cmd.toLowerCase();
        if (cmd.equals("y") || cmd.equals("yes")){
            try {
                houses.get(house).add(new ObservingPerson(name));
                System.out.println(name + " has been added.");
            } catch (DuplicateException e) {
                System.out.println("Each house member must have a different name.");
                newObservingPerson(houses, house, name);
            }
        }
        else {
            System.out.println(name + " was not added.");
        }
        HouseUI.insideHouse(houses, house);
    }

}
