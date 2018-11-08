package ui;
import model.house.HouseList;
import model.person.PayingPerson;
import model.transaction.SinglePayment;
import model.transaction.Transfer;

import java.io.IOException;
import java.util.Scanner;

// The section of the UI which deals with performing transactions
public class TransactionUI {

    // Used to scan input strings
    private static Scanner scanner = new Scanner(System.in);
    private static String cmd = "";

    // MODIFIES: houses
    // EFFECTS:  Prints the list of inhabitants then takes an input representing which
    //           inhabitant made the payment and how much they paid and calls makePayment()
    public static void transaction(HouseList houses, int house) throws IOException {
        System.out.println("Which type of transaction is it? Enter a number.");
        System.out.println("1 - Single Payment");
        System.out.println("2 - Transfer");
        cmd = scanner.nextLine();
        int type = 0;
        try {
            type = Integer.parseInt(cmd);
        } catch (NumberFormatException e) {
            transaction(houses, house);
        }
        if (type==1) {
            System.out.println("You have chosen 1 - Single Payment");
            singlePayment(houses, house);
        } else if (type==2) {
            System.out.println("You have chosen 2 - Transfer");
            transferFirst(houses, house);
        } else {
            transaction(houses, house);
        }
    }

    // MODIFIES: houses
    // EFFECTS:  Prints a numbered list of inhabitants and asks for a number representing
    //           which inhabitant makes the payment. Then calls makeSinglePayment
    private static void singlePayment(HouseList houses, int house) {
        System.out.println("Who made the payment? Enter a number.");
        houses.get(house).printInhabitants();
        cmd = scanner.nextLine();
        try {
            int person = Integer.parseInt(cmd)-1;
            PayingPerson payer = (PayingPerson) houses.get(house).getPerson(person);
            System.out.println("You have chosen " + houses.get(house).getPerson(person).getName());
            makeSinglePayment(houses, house, person);
        } catch (NumberFormatException e) {
            singlePayment(houses, house);
        } catch (ClassCastException e) {
            System.out.println("The person chosen cannot be an observer.");
            transferFirst(houses, house);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The number must be between 1 and " + houses.get(house).size());
        }
    }

    // REQUIRES: An int index that is >=0 and <people.size(), a positive int amount (of cents) and
    //           for people to include at least two Person objects
    // MODIFIES: this
    // EFFECTS:  Subtracts the amount from that person's balance, then splits the payment between
    //           everyone's account and then adds the split payment to everyone's account
    private static void makeSinglePayment(HouseList houses, int house, int person) {
        System.out.println("How much did " + houses.get(house).getPerson(person).getName() + " pay? Enter an amount (in cents)");
        cmd = scanner.nextLine();
        try {
            int amount = Integer.parseInt(cmd);
            PayingPerson payer = (PayingPerson) houses.get(house).getPerson(person);
            SinglePayment payment = new SinglePayment(amount, payer);
            payment.execute();
            String time = houses.get(house).recordTransaction(payment);
            payment.print(time);
        } catch (NumberFormatException e) {
            makeSinglePayment(houses, house, person);
        }
    }

    private static void transferFirst(HouseList houses, int house) {
        System.out.println("Who made the payment? Enter a number.");
        houses.get(house).printInhabitants();
        cmd = scanner.nextLine();
        try {
            int first = Integer.parseInt(cmd);
            PayingPerson payer = (PayingPerson) houses.get(house).getPerson(first-1);
            System.out.println("You have chosen " + houses.get(house).getPerson(first-1).getName());
            transferSecond(houses, house, first);
        } catch (NumberFormatException e) {
            transferFirst(houses, house);
        } catch (ClassCastException e) {
            System.out.println("The person chosen cannot be an observer.");
            transferFirst(houses, house);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The number must be between 1 and " + houses.get(house).size());
        }
    }

    private static void transferSecond(HouseList houses, int house, int first) {
        System.out.println("Who received the payment? Enter a second number");
        houses.get(house).printInhabitants();
        cmd = scanner.nextLine();
        try {
            int second = Integer.parseInt(cmd);
            PayingPerson payee = (PayingPerson) houses.get(house).getPerson(second-1);
            if (second == first) {
                System.out.println("You cannot choose the same person again");
                transferSecond(houses, house, first);
            } else {
                System.out.println("You have chosen " + houses.get(house).getPerson(second - 1).getName());
                makeTransfer(houses, house, first, second);
            }
        } catch (NumberFormatException e) {
            transferSecond(houses, house, first);
        } catch (ClassCastException e) {
            System.out.println("The person chosen cannot be an observer.");
            transferSecond(houses, house, first);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The number must be between 1 and " + houses.get(house).size());
        }
    }

    private static void makeTransfer(HouseList houses, int house, int first, int second) {
        System.out.println("How much was transferred? Enter an amount (in cents)");
        cmd = scanner.nextLine();
        try {
            int amount = Integer.parseInt(cmd);
            PayingPerson payer = (PayingPerson) houses.get(house).getPerson(first-1);
            PayingPerson payee = (PayingPerson) houses.get(house).getPerson(second-1);
            Transfer transfer = new Transfer(amount, payer, payee);
            transfer.execute();
            String time = houses.get(house).recordTransaction(transfer);
            transfer.print(time);
        } catch (NumberFormatException e) {
            makeTransfer(houses, house, first, second);
        }
    }

}
