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
                    showMessageDialog(null, "Opening Existing File");
                }
                if (choice == 'A') {
                    FileWriter csvEditor = new FileWriter(classRecord);

                    String heading[] = { "Student", "Quiz 1", "Quiz 2", "Quiz 3", "Average" };
                    for (String word : heading)
                        csvEditor.append(word + ",");
                    csvEditor.append("\n");

                    // writes the scores of each student
                    final int NUM_STUDENTS = 3;
                    final int NUM_QUIZZES = 3;
                    double[][] scores = new double[NUM_STUDENTS][NUM_QUIZZES];
                    for (int i = 0; i < NUM_STUDENTS; i++) {
                        csvEditor.append((i + 1) + ",");

                        int sum = 0;
                        for (int j = 0; j < NUM_QUIZZES; j++) {
                            scores[i][j] = validateQuizScore(i + 1, j + 1);
                            sum += scores[i][j];
                            csvEditor.append(scores[i][j] + ",");
                        }
                        csvEditor.append(sum / 3 + ",");
                        csvEditor.append("\n");
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

    public static int validateQuizScore(int studentNum, int quizNum) {
        int score = -1;
        while (true) {
            try {
                String message = String.format("Input Score for Quiz # %d of Student %d: (Must be between 0 and 100)",
                        quizNum, studentNum);
                score = Integer.parseInt(showInputDialog(null, message));
                if (score >= 0 && score <= 100)
                    return score;
                throw new NumberNotInRangeException(); // unreachable with correct input

            } catch (NumberNotInRangeException e) {
                showMessageDialog(null, "Unrecognized Input, must be a number between 0 and 100", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                showMessageDialog(null, "Unrecognized Input, must be a number between 0 and 100", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                System.out.println("Something Went Wrong");
            }
        }
    }
}

class NumberNotInRangeException extends Exception {
    NumberNotInRangeException() {
        System.out.println("ERROR: Number not in range");
    }

    NumberNotInRangeException(String message) {
        super(message);
    }
}