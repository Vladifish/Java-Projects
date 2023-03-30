// Vladimir Gray P. Velazco 1CSC, I could have learned JTables but the documentation was heavy
package File_Handling; // maybe some GUI manipulation

import javax.swing.*;
import java.io.*;

public class FileClassRecord extends JOptionPane {

    static final int NUM_STUDENTS = 3; // scalable, will change columns and rows of CSV
    static final int NUM_QUIZZES = 3;

    public static void main(String[] args) {
        char choice;
        while ((choice = optionMessage()) != 'C') {

            String className = "";
            className = showInputDialog(null, "Enter Class Name");

            if (className == null) // null is the exit and cancel button
                System.exit(0);

            String path = "File_Handling/Handleables/" + className + ".csv"; // edit your path, mine is like this so it
                                                                             // might
            // not
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
                    writeToFile(csvEditor); // body of the entire choice A
                    csvEditor.close();
                } else if (choice == 'B') {
                    try {
                        displayCSVFile(classRecord);
                    } catch (EmptyFileException e) {
                        showMessageDialog(null, "Empty File: File Cannot Be Read", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (IOException e) {
                showMessageDialog(null, "404: File Not Found", "ERROR", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } // End of Try-Catch Block
        } // End of While-Loop
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
        char letterInput = 'x';
        try {
            letterInput = showInputDialog(openingMessage).toUpperCase().charAt(0);
        } catch (NullPointerException e) { // the cancel button crashes otherwise
            System.exit(0);
        }
        while (letterInput != 'A' && letterInput != 'B' && letterInput != 'C') {
            showMessageDialog(null, "Unrecognized Input", "Input Error", JOptionPane.ERROR_MESSAGE);
            letterInput = showInputDialog(openingMessage).toUpperCase().charAt(0);
        }
        return letterInput;
    }

    public static double validateQuizScore(int studentNum, int quizNum) {
        double score = -1;
        while (true) {
            try {
                String message = String.format("Input Score for Quiz # %d of Student %d: (Must be between 0 and 100)",
                        quizNum, studentNum);
                score = Double.parseDouble(showInputDialog(null, message));
                if (score >= 0 && score <= 100)
                    return score;
                throw new NumberNotInRangeException(); // unreachable with correct input

            } catch (NumberNotInRangeException e) {
                showMessageDialog(null, "Unrecognized Input, must be a number between 0 and 100", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                showMessageDialog(null, "Unrecognized Input, must be a number between 0 and 100", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException e) {
                System.exit(0);
            } catch (Exception e) {
                System.out.println("Something Went Wrong");
            }
        }
    }

    private static void writeToFile(FileWriter csvEditor) throws IOException {
        // creates heading
        String heading[] = { "Student", "Quiz 1", "Quiz 2", "Quiz 3", "Average" };
        for (int i = 0; i < heading.length - 1; i++) {
            csvEditor.append(heading[i] + ",");
        }
        csvEditor.append(heading[heading.length - 1] + "\n");

        // writes the scores of each student
        double[][] scores = new double[NUM_STUDENTS][NUM_QUIZZES];
        for (int i = 0; i < NUM_STUDENTS; i++) {
            csvEditor.append((i + 1) + ",");

            double sum = 0;
            for (int j = 0; j < NUM_QUIZZES; j++) {
                scores[i][j] = validateQuizScore(i + 1, j + 1);
                sum += scores[i][j];
                csvEditor.append(scores[i][j] + ",");
            }
            csvEditor.append(sum / NUM_QUIZZES + "\n");
        }

        // Writes Average, Max and Min
        csvEditor.append("Average,");
        for (int i = 0; i < NUM_QUIZZES; i++) {
            double sum = 0;
            for (int j = 0; j < NUM_STUDENTS; j++)
                sum += scores[j][i];
            csvEditor.append(sum / NUM_STUDENTS + "");
            if (i != NUM_QUIZZES - 1) {
                csvEditor.append(",");
            }
        }
        csvEditor.append("\n");

        csvEditor.append("Max,");
        for (int i = 0; i < NUM_QUIZZES; i++) {
            double max = scores[1][i];
            for (int j = 1; j < NUM_STUDENTS; j++)
                if (max < scores[j][i])
                    max = scores[j][i];
            csvEditor.append(max + "");
            if (i != NUM_QUIZZES - 1) {
                csvEditor.append(",");
            }
        }
        csvEditor.append("\n");

        csvEditor.append("Min,");
        for (int i = 0; i < NUM_QUIZZES; i++) {
            double min = scores[1][i];
            for (int j = 1; j < NUM_STUDENTS; j++)
                if (min > scores[j][i])
                    min = scores[j][i];
            csvEditor.append(min + "");
            if (i != NUM_QUIZZES - 1) {
                csvEditor.append(",");
            }
        }
        csvEditor.append("\n");
    }

    private static void displayCSVFile(File csvFile) throws EmptyFileException, IOException {
        if (csvFile.length() == 0) {
            throw new EmptyFileException("ERROR: Empty File Cannot Be Read");
        }
        // Collated C x R from CSV file
        final int COLUMNS = NUM_QUIZZES + 2; // 2 Additional Columns for Average and Student #
        final int ROWS = NUM_STUDENTS + 4; // 4 additional Rows from Max, Min, Headings, and Average

        FileReader fr = new FileReader(csvFile);
        BufferedReader csvReader = new BufferedReader(fr);

        String fullOutput = "";
        for (int i = 0; i < ROWS; i++) {
            String line = csvReader.readLine();
            String[] values = line.split(",");

            for (int j = 0; j < values.length; j++) {
                fullOutput += String.format("%7s ", values[j]);
            }
            fullOutput += "\n";
        }

        showMessageDialog(null, fullOutput, "Message", JOptionPane.INFORMATION_MESSAGE);

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

class EmptyFileException extends Exception {
    EmptyFileException() {
        System.out.println("ERROR: File Empty");
    }

    EmptyFileException(String message) {
        super(message);
    }
}