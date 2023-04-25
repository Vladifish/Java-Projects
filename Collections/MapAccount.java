// Vladimir Gray P. Velazco 1-CSC
package Collections;

// Define a class called Account that contains attributes such as account number, name of the
// account holder, identification number of the account holder, and year of account opening.
// Provide get/set methods in this class to access the attributes. Define a class called
// AccountApp, which contains the main method. This class should create a few account objects
// with distinct account numbers and store it in a HashMap. The program should then ask for
// the account number as the input from the console and display the Account information
// pertaining to that account number. Hint: The account number is the key for the HashMap.
public class MapAccount {

}

class Account {
    private int num;
    private String holderName;
    private int IDNumber;

    private int yearOfOpening;

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
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

}