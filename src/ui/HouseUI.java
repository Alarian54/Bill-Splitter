package ui;
import model.house.House;
import model.house.HouseList;
import ui.exceptions.HouseDuplicateException;

import java.io.IOException;
import java.util.Scanner;

// The section of the UI that deals with creating, entering
// and navigating House objects
public class HouseUI {

    // Used to scan input strings
    private static Scanner scanner = new Scanner(System.in);
    private static String cmd = "";

    // MODIFIES: scanner, cmd, houses
    // EFFECTS:  Takes an input then creates a new House with that name if a further "y"
    //           or "yes" is entered. If not, calls start again
    public static void newHouse(HouseList houses) throws IOException {
        System.out.println("\nEnter the house's name:");
        String name;
        name = scanner.nextLine();
        if (houses.checkHouse(new House(name))){
            System.out.println("Each house must have a different name.");
            newHouse(houses);
        }
        System.out.println("\nDo you want to add " + name + "? Type Yes if so.");
        cmd = scanner.nextLine();
        cmd = cmd.toLowerCase();
        if (cmd.contains("y")){
            try {
                houses.add(new House(name));
                System.out.println(name + " has been added.");
            } catch (HouseDuplicateException e) {
                System.out.println("Each house must have a different name.");
                newHouse(houses);
            }
        }
        else {
            System.out.println(name + " was not added.");
        }
        StartUI.start(houses);
    }

    // MODIFIES: scanner, cmd, houses
    // EFFECTS:  Prints the current list of houses with associated numbers
    //           Takes in an input then tries to convert it into a number from 1 to 9
    //           Then calls insideHouse with the number as a house index
    //           If invalid, or the number does not have a corresponding house, calls start
    public static void enterHouse(HouseList houses) throws IOException {
        System.out.println("\nWhich house would you like to enter?");
        int i = 1;
        for (House house : houses.getHouses()) {
            System.out.println(i + " - " + house.getName());
            i++;
        }
        cmd = scanner.nextLine();
        try {
            int house = Integer.parseInt(cmd)-1;
            houses.get(house);
            insideHouse(houses, house);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Enter a number between 1 and " + houses.size());
            enterHouse(houses);
        }
    }

    // MODIFIES: cmd, scanner, houses
    // EFFECTS:  Prints the current house name and inhabitants. Takes an input and then either:
    //           - Adds a new person to the house
    //           - Adds a payment to a person in the house
    //           - Leaves the house and calls start() again
    public static void insideHouse(HouseList houses, int house) throws IOException {
        System.out.println("");
        houses.get(house).print();
        System.out.println("\nEnter \"Add Person\", \"Transaction\", \"Show Record\" or \"Leave House\"");
        cmd = scanner.nextLine();
        cmd = cmd.toLowerCase();
        if (cmd.contains("add")) {
            if (houses.get(house).size() < 9) {
                PersonUI.newPerson(houses, house);
            } else {
                System.out.println("You cannot add another person.");
            }
        }
        else if (cmd.contains("trans")) {
            TransactionUI.transaction(houses, house);
        }
        else if (cmd.contains("leave")) {
            StartUI.start(houses);
        }
        else if (cmd.contains("show")) {
            System.out.println("Transaction record for " + houses.get(house).getName() + ":");
            houses.get(house).printRecord();
            System.out.println("\nPress any key to continue");
            cmd = scanner.nextLine();
            insideHouse(houses, house);
        }
        insideHouse(houses, house);
    }
}
