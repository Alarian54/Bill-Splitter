package model.person;
import model.house.House;
import ui.exceptions.DuplicateException;

import java.util.Objects;

// Represents a single person, who inhabits a House and may or may not make payments
public abstract class Person {

    // The name of the person
    protected String name;

    // The house that the person is in
    private House house;

    // MODIFIES: this
    // EFFECTS:  Constructs a new Person with the given name
    public Person(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS:  Sets name
    public void setName(String name) { this.name = name; }

    // EFFECTS: Returns name
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS:  Sets house
    public void setHouse(House house) {
        if (house!=this.house) {
            this.house = house;
            house.add(this);
        }
    }

    // MODIFIES: this
    // EFFECTS:  Resets house to null
    public void removeHouse() {
        try {
            house.remove(name);
            house = null;
        } catch (NullPointerException e) {
            System.out.println(name + " is not in the House they are recorded as being in.");
        }
    }

    // EFFECTS: Returns house
    public House getHouse() { return house; }

    // EFFECTS: Returns a string consisting of the person's name
    //          followed by their balance (in $) if they are Paying
    public abstract String string();

    // EFFECTS: Returns balance as a String (for PayingPerson)
    //          or "n/a" for ObservingPerson
    public abstract String balanceString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name.toLowerCase(), person.name.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
