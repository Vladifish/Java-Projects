// Vladimir Gray P. Velazco 1CSC
package File_Handling; // maybe also some GUI manipulation

import javax.swing.*;

public class FileClassRecord {
    public static void main(String[] args) {
        while (optionMessage() != 'C') {
            String columns[] = { "5", "3", "2" };
            int row[] = { 1, 2, 3 };
            JTable recordTable = new JTable();
        }
        JOptionPane.showMessageDialog(null, "Exiting :: GOOD BYE!", "EXITING", JOptionPane.INFORMATION_MESSAGE);

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
            JOptionPane.showMessageDialog(null, "Unrecognized Input", "Input Error", JOptionPane.ERROR_MESSAGE);
            letterInput = JOptionPane.showInputDialog(openingMessage).toUpperCase().charAt(0);
        }
        return letterInput;
    }
}
