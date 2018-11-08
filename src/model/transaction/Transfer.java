package model.transaction;
import model.person.PayingPerson;
import model.person.Person;

import java.util.Date;

// Represents one person in the house paying another person, typically
// because the first person owes the second money
public class Transfer extends Transaction {

    // The person who is making the payment
    private PayingPerson payer;
    // The person the payment is being made to
    private PayingPerson payee;

    public Transfer(int value, PayingPerson payer, PayingPerson payee){
        super(value);
        this.payer = payer;
        this.payee = payee;
    }

    @Override
    public void execute() {
        payer.deductBalance(value);
        payee.addBalance(value);
    }

    @Override
    public void print(String time) {
        String minus = "";
        if (this.value<0) {
            minus = "-";
        }
        int dollars = Math.abs(this.value/100);
        String cents = Integer.toString(Math.abs(this.value%100));
        if (Math.abs(this.value%100)==0) {
            cents = "00";
        }
        System.out.println(payer.getName() + " transferred " + minus + "$" + dollars + "." + cents + " to " + payee.getName() + " at " + time);
    }
}
