package model.house;
import ui.exceptions.HouseDuplicateException;
import java.util.ArrayList;

// Represents an ArrayList of houses, with the added
// functionality of not adding duplicate names
public class HouseList {

    // The list of houses
    private ArrayList<House> houses;

    // MODIFIES: this
    // EFFECTS: Constructs a new HouseList with an empty ArrayList
    public HouseList(){
        houses = new ArrayList<>();
    }

    // EFFECTS: Returns the house at index i
    //          i.e. equivalent to ArrayList.get(i)
    public House get(int i) {
        return houses.get(i);
    }

    // EFFECTS: Returns the ArrayList
    public ArrayList<House> getHouses() {
        return houses;
    }

    // EFFECTS: Sets the ArrayList
    public void setHouses(ArrayList<House> houses) {
        this.houses = houses;
    }

    // EFFECTS: Returns the size of the ArrayList
    //          i.e. equivalent to ArrayList.size()
    public int size() {
        return houses.size();
    }

    // EFFECTS: Adds a new house to the list of houses, and throws an
    //          exception if its name matches one already there
    public void add(House house) throws HouseDuplicateException {
        for (House h : this.houses) {
            if (h.getName().equals(house.getName())) {
                throw new HouseDuplicateException();
            }
        }
        houses.add(house);
    }

    // EFFECTS: Checks if a HouseList already includes a house
    public boolean checkHouse(House house) {
        for (House h : houses) {
            if (h.equals(house)){
                return true;
            }
        }
        return false;
    }
}
