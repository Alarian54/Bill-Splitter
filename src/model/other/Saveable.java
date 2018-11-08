package model.other;
import model.house.House;
import java.io.IOException;
import java.util.ArrayList;

// Represents a class that can save a list of houses to a file
public interface Saveable {

    void save(ArrayList<House> houses, String fileName) throws IOException;

}
