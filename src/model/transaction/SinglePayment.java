package model.transaction;
import model.house.House;
import model.person.PayingPerson;
import model.person.Person;

// Represents a payment made by one person. The cost of the payment is deducted from
// their balance, and divided up and added to the balance of the other inhabitants
public class SinglePayment extends Transaction {

    // The person who is making the payment
    private PayingPerson person;
    private int splitAmong;
    private int splitAmount;

    public SinglePayment(int value, PayingPerson person){
        super(value);
        this.person = person;
    }

    @Override
    public void execute() {
        House house = person.getHouse();
        int splitAmong = 0;
        for (Person p : house.getPeople()) {
            if (p instanceof PayingPerson) {
                splitAmong++;
            }
        }
        int splitAmount = value/splitAmong;
        for (Person p : house.getPeople()) {
            if (p.equals(person)) {
                ((PayingPerson) p).deductBalance(splitAmount*(splitAmong-1));
            } else if (p instanceof PayingPerson) {
                ((PayingPerson) p).addBalance(splitAmount);
            }
        }
        this.splitAmong = splitAmong;
        this.splitAmount = splitAmount;
    }

    @Override
    public void print(String time) {
        int paid = splitAmount*(splitAmong-1);
        String minus = "";
        if (paid<0) { minus = "-"; }
        int dollars = Math.abs(paid/100);
        String cents = Integer.toString(Math.abs(paid%100));
        if (Math.abs(paid%100)==0) { cents = "00"; }
        String str = (person.getName() + " paid " + minus + "$" + dollars + "." + cents + " at " + time);
        if (splitAmount<0) { minus = "-"; }
        dollars = Math.abs(splitAmount/100);
        cents = Integer.toString(Math.abs(splitAmount%100));
        if (Math.abs(splitAmount%100)==0) { cents = "00"; }
        for (Person p : person.getHouse().getPeople()){
            if (p instanceof PayingPerson && !p.equals(person)) {
                str += ("\n" + p.getName() + " now owes " + person.getName() + " " + minus + "$" + dollars + "." + cents + " more.");
            }
        }
        System.out.println(str);
    }
}
