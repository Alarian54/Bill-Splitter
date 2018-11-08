package model.house;
import model.person.PayingPerson;
import model.person.Person;
import model.transaction.Transaction;
import ui.exceptions.DuplicateException;
import ui.exceptions.PersonDuplicateException;

import java.util.*;

// Represents a collection of people who split bills amongst each other
public class House {

    // The name of the house
    private String name;

    // The inhabitants of the house
    private ArrayList<Person> people;

    // A number keeping track of which person is due to be billed for any extra cents
    private int iterator;

    //Used for obtaining dates for the transaction record
    private Calendar cal;

    // Used to record all the transactions that takes place in this house
    private HashMap<Date, Transaction> record;

    // MODIFIES: this
    // EFFECTS:  Constructs a new House with the given name and a currently empty list of inhabitants
    public House(String name) {
        this.name = name;
        people = new ArrayList<>();
        cal = Calendar.getInstance();
        record = new HashMap<>();
    }

    // EFFECTS: Prints out the name of the house, followed by a dashed (-) list of its inhabitants,
    //          or "No inhabitants yet" if it does not yet have any.
    public void print(){
        System.out.println(name);
        for (Person person: people){
            System.out.println(" - " + person.string());
        }
        if (people.size()==0) {
            System.out.println(" - No inhabitants yet");
        }
    }

    // EFFECTS: Prints out a numbered list of the houses inhabitants, or
    //          "No inhabitants yet" if it does not yet have any.
    public void printInhabitants() {
        int i=0;
        for (Person person: people){
            i++;
            System.out.println(Integer.toString(i) + " - " + person.string());
        }
        if (people.size()==0) {
            System.out.println(" - No inhabitants yet");
        }
    }

    // EFFECTS: Returns the name of the house
    public String getName() {
        return name;
    }

    // EFFECTS: Returns a specific person in the house
    public Person getPerson(int i) {
        return people.get(i);
    }

    // EFFECTS: Returns the list of people in the house
    public ArrayList<Person> getPeople() {
        return people;
    }

    // EFFECTS: Returns the number of people in the house
    public int size() {
        return people.size();
    }

    // REQUIRES: A Person that does not share their name with a current inhabitant
    // MODIFIES: this, person
    // EFFECTS:  Adds a new person to the house's list of inhabitants
    public void add(Person person) {
        if (!people.contains(person)) {
            people.add(person);
            person.setHouse(this);
        }
    }

    // REQUIRES: A Person with that name in the house's list of inhabitants
    // MODIFIES: this, person
    // EFFECTS:  Removes a given Person from the house's list of inhabitants
    public void remove(String person) {
        for (Person p : people) {
            if (p.getName().equals(person)){
                people.remove(p);
                p.removeHouse();
                break;
            }
        }
        System.out.println(person + " is not in this house.");
    }

    // EFFECTS: Checks if a house already includes a person
    public boolean checkPerson(Person person) {
        for (Person p : people) {
            if (p.equals(person)){
                return true;
            }
        }
        return false;
    }

    // REQUIRES: One or more Person objects in the people list
    // EFFECTS: Returns True if the combined balance of residents sums to 0
    public boolean checkBalance() {
        int total = 0;
        for (Person person : people) {
            if (person instanceof PayingPerson) {
                total += Integer.parseInt(person.balanceString());
            }
        }
        return total == 0;
    }

    // MODIFIES: this
    // EFFECTS:  Records the given transaction in the house's record and returns
    //           the time the transaction took place for use in printing
    public String recordTransaction(Transaction transaction) {
        Date time = cal.getTime();
        record.put(time, transaction);
        return time.toString();
    }

    // Override of equals so that 2 houses with the same name are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(name.toLowerCase(), house.name.toLowerCase());
    }

    // Necessary with the override of equals()
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void printRecord() {
        System.out.println(record);
    }
}
