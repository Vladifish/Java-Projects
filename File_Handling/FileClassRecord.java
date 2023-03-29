// Vladimir Gray P. Velazco 1CSC
package File_Handling; // maybe also some GUI manipulation

import javax.swing.*;
import java.io.*;

public class FileClassRecord extends JOptionPane {
    public static void main(String[] args) {
        char choice;
        final int COLUMNS = 5;
        final int ROWS = 7;
        while ((choice = optionMessage()) != 'C') {
            final String className = showInputDialog(null, "Enter Class Name");
            String path = "File_Handling/" + className + ".csv"; // edit your path, mine is like this so it might not
                                                                 // work for everyone
            File classRecord = new File(path);

            try {
                if (classRecord.createNewFile()) {
                    showMessageDialog(null, "File " + classRecord.getName() + " Created Successfully"); // returns file
                    // name
                } else {
                    showMessageDialog(null, "File already in directory");
                }
                if (choice == 'A') {
                    FileWriter csvEditor = new FileWriter(classRecord);

                    String heading[] = { "Student", "Quiz 1", "Quiz 2", "Quiz 3", "Average" };
                    for (String word : heading)
                        csvEditor.append(word + ",");
                    csvEditor.append("\n");

                    final int NUM_STUDENTS = 3;
                    for (int i = 0; i < NUM_STUDENTS; i++) {
                        csvEditor.append(i + ",");
                    }
                    csvEditor.close();
                }
            } catch (IOException e) {
                showMessageDialog(null, "An unexpected error has occured");
                e.printStackTrace();
            }
        }
        showMessageDialog(null, "Exiting :: GOOD BYE!", "EXITING", JOptionPane.INFORMATION_MESSAGE);

        // GOOD BYE!
    }

    public static char optionMessage() {
        String openingMessage = "Menu\n" +
                "A. Add Record\n" +
                "B. Display Class Record\n" +
                "C. Quit";
        // magical incantation
        // sends a message, capitalizes then gets the 1st letter of the input
        char letterInput = showInputDialog(openingMessage).toUpperCase().charAt(0);
        while (letterInput != 'A' && letterInput != 'B' && letterInput != 'C') {
            showMessageDialog(null, "Unrecognized Input", "Input Error", JOptionPane.ERROR_MESSAGE);
            letterInput = showInputDialog(openingMessage).toUpperCase().charAt(0);
        }
        return letterInput;
    }

    public static void validateQuizScore() {

    }
}
