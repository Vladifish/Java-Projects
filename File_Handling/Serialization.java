// Vladimir Gray P. Velazco 1CSC
package File_Handling;

import java.util.*;
import java.io.*;

// ➢ When 1 is chosen, ask the user to enter the number of students and allow the user to
// enter the required data. The entered data must be saved permanently in a file(use
// relative path). A Student class must be created with the following attributes : Name,
// ID#(4-digit number), Quiz1, Quiz2, and Quiz3.

// 
// ➢ When 2 is chosen, ask the user for the ID# and quiz number that he/she
// wants to edit then allow the user to edit the score. If Student ID# is not in the record, 
// display the appropriate prompt and ask the user to enter an ID# again.

// ➢ When 3 is chosen, read the Class Record file and display it on the scr
// en in tabular form.
// Display also the following information:
// 
// a. Average grade per student
// b. Highest score per quiz
// c. Lowest score per quiz
// 
// d. Average score per quiz

// ➢ When 4 is chosen, ask the user for the ID# then display name, scores, and the
// corresponding average.

// ➢ When 5 is chosen, end the program.

// 

public class Serialization { // TODO: Formatting
    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        File classRecord = new File("");
        while (true) {
            System.out.println("MENU:");
            System.out.println("1 - Select Class Record");
            System.out.println("2 - Edit Quiz Score");
            System.out.println("3 - Select Class Record");
            System.out.println("4 - Select Class Record");
            System.out.println("5 - Exit Program");
            int input = validator(/* min: */1, /* max: */ 5);
            if (input == 5)
                break;

            if (input == 1) {
                // specifies the file
                System.out.print("Input the file name of the class record to be edited:");
                String section = console.next();
                StringBuilder filePath = new StringBuilder();
                filePath.append("File_Handling/Handleables/"); // comment out later
                filePath.append(section + ".txt");
                classRecord = new File(filePath.toString());

                // checks how many students to add
                System.out.println("How many students records will you write (max 100)?");
                int numStudents = validator(1, 100);
                System.out.println("Will you Append or Overwrite the file? Input 0 to Overwrite, 1 to Append:");
                boolean append = validator(0, 1) == 1;
                if (append) { // overwriting
                    System.out.println(
                            "This action could delete all the information in the file? Press 0 to go back to menu");
                    if (console.next().charAt(0) == '0')
                        continue; // goes back to the menu
                }

                createRecord(classRecord, numStudents, append);
            } // end of input == 1
              // File classRecord = new File();
        }
    }

    private static int validator(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(console.next());
                if (input >= min && input <= max)
                    return input;
                throw new NumberNotInRangeException();
            } catch (NumberNotInRangeException e) {
                System.out.printf("ERROR: Must be a number between %d and %d\n", min, max);
            } catch (NumberFormatException e) {
                System.out.printf("ERROR: Must be a number between %d and %d\n", min, max);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * <h1>Create Record</h1>
     * Creates the record of a whole class of students
     * Needed Info: name, 4-digit ID#, 3 quiz scores
     * 
     * @param classRecord
     * @param numStudents could be from 1 to 100
     * @param appending   0 if overriding, 1 if appending
     */
    private static void createRecord(File classRecord, int numStudents, boolean append) {
        Student[] students = new Student[numStudents];
        for (int i = 0; i < numStudents; i++) {
            students[i] = new Student();

            // Student Name
            System.out.print("Student Name: ");
            console.nextLine(); // eats up trailing new-lines
            students[i].name = console.nextLine();

            // Handling the 4 digit ID
            System.out.println("4-digit ID#: ");
            try {
                students[i].setIDNumber(validator(0, 9999));
            } catch (NumberNotInRangeException e) {
                System.out.println(e); // should be unreachable, since the validator handles it already
            }

            // Quiz Scores
            System.out.println("Input the 3 Quiz Scores of Student:");
            for (int j = 0; j < 3; j++) {
                students[i].setQuizScore(j, validator(0, 100));
            }

            // Check if the inputted information is correct
            System.out.println("Is this satisfactory? Input 0 to redo operation");
            System.out.println(students[i]);
            if (console.next().charAt(0) == '0') {
                i--;
                continue;
            }
        }

        // I handled this outside of the other for-loop to minimize the nesting
        try {
            FileOutputStream fOut = new FileOutputStream(classRecord, append);
            ObjectOutputStream objOut = new ObjectOutputStream(fOut);
            for (int i = 0; i < numStudents; i++) {
                // Writes the student's serialized info to the record
                objOut.writeObject(students[i]);
            }
            objOut.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File might be a directory, or does not exist");
        } catch (IOException e) {
            System.out.println("Error with handled file");
        }

    }

}

class Student implements Serializable {
    String name = "Juan Dela Cruz";
    private int IDNumber;
    private double[] quizzes;

    Student() {
        quizzes = new double[3];
    }

    Student(String name, int id, int quiz1, int quiz2, int quiz3) {
        quizzes = new double[3];
        this.name = name;
        IDNumber = id;
    }

    public double getQuizScore(int quizNum) {
        return quizzes[quizNum];
    }

    public void setQuizScore(int quizNum, double score) {
        quizzes[quizNum] = score;
    }

    @Override
    public String toString() {
        String temp = String.format("NAME = %s :: ID# = %s :: ", name, getIDString());
        StringBuilder output = new StringBuilder(temp);
        for (int i = 0; i < quizzes.length; i++) {
            output.append("Quiz " + (i + 1) + ": " + quizzes[i] + " :: ");
        }
        output.append("\n");
        return output.toString();
    }

    public String getIDString() {
        return String.format("%04d", IDNumber);
    }

    public void setIDNumber(int id) throws NumberNotInRangeException {
        if (id < 10_000 && id > -1)
            IDNumber = id;
        else
            throw new NumberNotInRangeException("ERROR: ID# Must be a positive integer with at most 4 digits");
    }
}

class NumberNotInRangeException extends Exception { // this is defined twice in my file system so...
    NumberNotInRangeException() {
    }

    NumberNotInRangeException(String message) {
        super(message);
    }
}