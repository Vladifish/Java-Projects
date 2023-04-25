// Vladimir Gray P. Velazco 1-CSC
package Collections;

import java.util.*;

// Define a class called Account that contains attributes such as account number, name of the
// account holder, identification number of the account holder, and year of account opening.
// Provide get/set methods in this class to access the attributes. Define a class called
// AccountApp, 
// which contains the main method. This class should create a few account objects
// with distinct account numbers and store it in a HashMap. The program should then ask for
// the account number as the input from the console and display the Account information
// pertaining to that account number. Hint: The account number is the key for the HashMap.
public class MapAccount {

    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        Map<Integer, Account> sample = new HashMap<Integer, Account>();

        final String[] namePool = { "Steve", "Vlad", "Clarence", "Mark", "Lawrence", "Josh" };
        for (int i = 0; i < namePool.length; i++)
            sample.put(i * 2, new Account(i * 2, namePool[i], 2023 + i, 2023));

        while (true) {
            System.out.println("Enter Account Number to be Searched or -1 to exit");
            int searchNum = validate();
            if (searchNum == -1)
                break;

            Set<Map.Entry<Integer, Account>> iterableSample = sample.entrySet();
            Iterator<Map.Entry<Integer, Account>> iter = iterableSample.iterator();
            boolean found = false;
            while (iter.hasNext()) {
                Map.Entry<Integer, Account> curr = iter.next();
                if (curr.getKey() == searchNum) {
                    System.out.println("ACCOUNT: " + curr.getValue());
                    found = true;
                    break; // unique key anw
                }
            }
            if (!found)
                System.out.println("Account Number Not in Database");
        }
    }

    private static int validate() {
        while (true) {
            try {
                int output = Integer.parseInt(console.next());
                return output;
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Must be an integer number");
            }
        }
    }

}

class Account {
    private int acctNum;
    private String holderName;
    private int IDNumber;
    private int yearOfOpening;

    public Account(int acctNum, String holderName, int IDNumber, int yearOfOpening) {
        this.acctNum = acctNum;
        this.holderName = holderName;
        this.IDNumber = IDNumber;
        this.yearOfOpening = yearOfOpening;
    }

    public void setAcctNum(int num) {
        this.acctNum = num;
    }

    public int getAcctNum() {
        return acctNum;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public int getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(int iDNumber) {
        IDNumber = iDNumber;
    }

    public int getYearOfOpening() {
        return yearOfOpening;
    }

    public void setYearOfOpening(int yearOfOpening) {
        this.yearOfOpening = yearOfOpening;
    }

    @Override
    public String toString() {
        return acctNum + ": " + holderName + " " + IDNumber + " " + yearOfOpening;
    }

}