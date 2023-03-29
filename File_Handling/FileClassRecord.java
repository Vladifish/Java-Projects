// Vladimir Gray P. Velazco 1CSC
package File_Handling; // maybe also some GUI manipulation

import javax.swing.*;

// I will not write the entire thing in GUI, just trying out buttons rn
public class FileClassRecord {
    public static void main(String[] args) {
        while (optionMessage() != 'C') {

        }
        // GOOD BYE!
    }

    public static char optionMessage() {
        String openingMessage = "Menu\n" +
                "A. Add Record\n" +
                "B. Display Class Record\n" +
                "C. Quit";
        // magical incantation
        // sends a message, capitalizes then gets the 1st letter of the input
        char letterInput = JOptionPane.showInputDialog(openingMessage).toUpperCase().charAt(0);
        while (letterInput != 'A' && letterInput != 'B' && letterInput != 'C') {
            JOptionPane.showMessageDialog(null, "Error: Unrecognized Input");
            letterInput = JOptionPane.showInputDialog(openingMessage).toUpperCase().charAt(0);
        }
        return letterInput;
    }
}
