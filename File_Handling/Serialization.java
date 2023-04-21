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

public class Serialization {
    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("MENU:");
            int input = validator(/* min: */1, /* max: */ 5);
            System.out.println(input);
            if (input == 5)
                break;

            File classRecord = new File("");
            if (input == 1) {
                // specifies the file
                System.out.print("Input which section that would be edited:");
                String section = console.next();
                StringBuilder filePath = new StringBuilder();
                filePath.append("File_Handling/Handleables/"); // comment out later
                filePath.append(section + ".txt");
                classRecord = new File(filePath.toString());
                // checks how many students to add
                System.out.println("How many students records will you write?");
                int numStudents = validator(1, 100);
                System.out.println("Will you Append or Overwrite the file? Input 0 to Overwrite, 1 to Append:");
                int append = validator(0, 1);
                if (append == 0) { // overwriting
                    System.out.println(
                            "This action could delete all the information in the file? Press 0 to go back to menu");
                    if (console.next().charAt(0) == '0')
                        break; // goes back to the menu
                }

            }
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
     * @param numStudents
     * @param appending   0 if overwriting, 1 if appending
     */

    private static void createRecord(File classRecord, int numStudents, int appending) {

    }

}

class Student {
    private String name = "Juan Dela Cruz";
    int ID_NUMBER;
    int[] quizzes;

    Student(String name, byte id, int quiz1, int quiz2, int quiz3) {
        quizzes = new int[3];
        this.name = name;
        ID_NUMBER = id;
    }

    public String getName() {
        return name;
    }

    public int getQuizScore(int quizNum) {
        return quizzes[quizNum];
    }
}

class NumberNotInRangeException extends Exception {
    NumberNotInRangeException() {
    }

    NumberNotInRangeException(String message) {
        super(message);
    }
}