package ui;
import model.house.House;
import model.house.HouseList;

import java.io.IOException;
import java.util.Scanner;

// The "main menu" equivalent for the UI
public class StartUI {

    // Used to scan input strings
    private static Scanner scanner = new Scanner(System.in);

    // MODIFIES: scanner, cmd
    // EFFECTS:  Displays the list of houses and all their inhabitants, then asks the user
    //           to choose to either create a new house or enter an existing house. If neither
    //           is chosen, it runs itself again
    public static void start(HouseList houses) throws IOException {
        System.out.println("");
        if (houses.size() > 0) {
            System.out.println("List of houses:");
            for (House house : houses.getHouses()) {
                house.print();
            }
            System.out.println("");
        }
        System.out.println("Enter \"New House\", \"Enter House\" or \"Quit\":");
        String cmd = scanner.nextLine();
        cmd = cmd.toLowerCase();
        if (cmd.contains("new")) {
            HouseUI.newHouse(houses);
        }
        if (cmd.contains("ent")) {
            HouseUI.enterHouse(houses);
        }
        if (cmd.equals("quit")) {
            Main m = new Main();
            m.save(houses.getHouses(), "houses.txt");
            java.lang.System.exit(0);
        }
        start(houses);
    }


}
