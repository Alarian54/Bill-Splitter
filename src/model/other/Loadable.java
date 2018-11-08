package model.other;
import model.house.House;
import java.util.ArrayList;
import java.io.IOException;

// Represents a class that can load a list of houses from a file
public interface Loadable {

    ArrayList<House> load(String fileName) throws IOException;

}
