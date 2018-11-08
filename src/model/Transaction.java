package model.transaction;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

// Represents some kind of transfer of money - a payment, repayment, etc
public abstract class Transaction {

    // The amount of money (in cents) that the transaction consists of
    protected int value;

    Transaction(int value){
        this.value = value;
    }

    // EFFECTS: Performs the transaction
    protected abstract void execute();

    // EFFECTS: Given a time, prints out a record of the transaction at that time
    protected abstract void print(String time);
}
