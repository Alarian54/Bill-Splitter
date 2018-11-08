package model.person;

import model.house.House;

// Represents a Person who contributes their share to house costs
public class PayingPerson extends Person {

    // How much money the person has (or owes), in cents
    // Positive amount -> the person owes the other inhabitants of their house money
    // Negative amount -> the person is owed money by the other inhabitants of their house
    private int balance;

    // MODIFIES: this
    // EFFECTS:  Constructs a new PayingPerson with the given name, house and balance
    public PayingPerson(String name, int balance) {
        super(name);
        this.balance = balance;
    }

    // EFFECTS: Returns balance
    public int getBalance() {
        return balance;
    }

    // EFFECTS: Returns balance, but as a String
    @Override
    public String balanceString() {
        return Integer.toString(balance);
    }

    // REQUIRES: A positive integer amount (of cents)
    // MODIFIES: this
    // EFFECTS:  Adds the specified amount to the person's balance
    public void addBalance(int amount) {
        balance += amount;
    }

    // REQUIRES: A positive integer amount (of cents)
    // MODIFIES: this
    // EFFECTS:  Deducts the specified amount from the person's balance
    public void deductBalance(int amount) {
        balance -= amount;
    }

    // EFFECTS: Returns a string consisting of the person's name
    //          followed by their balance (in $)
    public String string() {
        String minus = "";
        if (balance<0) {
            minus = "-";
        }
        int dollars = Math.abs(balance/100);
        String cents = Integer.toString(Math.abs(balance%100));
        if (Math.abs(balance%100)==0) {
            cents = "00";
        }
        return (name + " (" + minus + "$" + dollars + "." + cents + ")");
    }
}
