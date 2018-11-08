package model.person;

import model.house.House;

// Represents a Person who is part of the House but does not participate in bill splitting
public class ObservingPerson extends Person {

    // MODIFIES: this
    // EFFECTS:  Constructs a new Person with the given name
    public ObservingPerson (String name) {
        super(name);
    }

    // EFFECTS: Returns a string consisting of the person's name
    //          followed by no balance (in $)
    public String string() {
        return (name + " (n/a)");
    }

    // EFFECTS: Returns balance, but as a String
    @Override
    public String balanceString() {
        return "n/a";
    }
}
