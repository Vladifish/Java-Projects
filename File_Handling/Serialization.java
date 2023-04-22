// Vladimir Gray P. Velazco 1CSC
// Beware this code might be overengineered
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

public class Serialization { // TODO: Text Formatting
    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        File classRecord = new File("");
        String section = "";
        boolean fileChosen = false;
        while (true) {
            System.out.println("MENU:");
            System.out.println("1 - Select/Create Class Record");
            System.out.println("2 - Edit Quiz Scores of a Student ID#");
            System.out.println("3 - Display Class Record");
            System.out.println("4 - Display Specific Student Record");
            System.out.println("5 - Exit Program");
            int input = (int) validator(/* min: */1, /* max: */ 5);

            if (input == 5) {
                System.out.println("EXITING: GOOD BYE! :D");
                break;
            }

            if (input == 1) { // Either Points or Edits a Whole File
                // specifies the file
                System.out.print("Input the section of the class record to be selected / edited:");
                section = console.next();
                StringBuilder filePath = new StringBuilder();
                filePath.append("File_Handling/Handleables/"); // comment out later
                filePath.append(section + ".txt");
                classRecord = new File(filePath.toString());

                // Check if Ovewriting
                System.out.println("Will you Overwrite the file? Input 0 to go back to the menu, 1 to Overwrite:");
                boolean overwrite = validator(0, 1) == 1;
                fileChosen = true;
                if (!overwrite)
                    continue; // going back to menu

                // Re-Confirm Decision
                System.out.println(
                        "This action could delete all the information in the file? Input 0 to go back to menu or any other key to continue");
                if (console.next().charAt(0) == '0')
                    continue; // goes back to the menu

                // checks how many students to add
                System.out.println("How many students records will you write (max 100)?");
                int numStudents = (int) validator(1, 100);

                createRecord(classRecord, numStudents);
            } // end of input == 1

            else if (input == 2) { // Edit Quiz Scores of a Specific ID
                if (!fileChosen) {
                    System.out.println("No File Chosen, Returning to Menu");
                    continue;
                }
                editSpecificID(classRecord);
            } // end of input == 2

            else if (input == 3) {
                if (!fileChosen) {
                    System.out.println("No File Chosen, Returning to Menu");
                    continue;
                }
                displayClassRecord(classRecord, section);
            } // end of input == 3

            else if (input == 4) {
                if (!fileChosen) {
                    System.out.println("No File Chosen, Returning to Menu");
                    continue;
                }
            } // end of input == 4
        }
    }

    // Appears to need more
    private static void editSpecificID(File classRecord) {
        while (true) {
            System.out.println("Input 4-digit ID# or -1 to return to menu");
            int id = (int) validator(-1, 9999);
            if (id == -1) // guard-clause
                return;

            String studID = String.format("%04d", id);
            boolean found = false;
            int foundIndex = 0;
            try {
                FileInputStream fInStream = new FileInputStream(classRecord);
                ObjectInputStream objInStream = new ObjectInputStream(fInStream);
                Student[] editedRecord = (Student[]) objInStream.readObject();
                for (int i = 0; i < editedRecord.length; i++) { // reads until end of line
                    Student tempStudent = (Student) editedRecord[i];
                    found = studID.equals(tempStudent.getIDString());
                    if (found)
                        foundIndex = i;
                }
                if (!found)
                    continue; // goes back to start of loop

                // Unreachable if the ID is not in the file
                System.out.println("Input Quiz #:");
                int quizNumber = (int) validator(1, 3);
                System.out.println("Input New Quiz Score:");
                double score = validator(0, 100);
                editedRecord[foundIndex].setQuizScore(quizNumber, score);

                fInStream.close();
                objInStream.close();

                // Updates the Record in the given file
                ObjectOutputStream fOutStream = new ObjectOutputStream(new FileOutputStream(classRecord, false)); // appends
                fOutStream.writeObject(editedRecord);
                fOutStream.close();
            } catch (FileNotFoundException e) {
                System.out.println("ERROR: File not found");
            } catch (IOException e) {
                System.out.println("An error occured with the read file, file might be empty");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static double validator(int min, int max) {
        while (true) {
            try {
                double input = Double.parseDouble(console.next());
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
     * <p>
     * Creates the record of a whole class of students
     * </p>
     * <p>
     * Needed Info: name, 4-digit ID#, 3 quiz scores
     * </p>
     * 
     * @param classRecord the file to edit/create
     * @param numStudents could be from 1 to 100
     * @param append      will override file if false
     */
    private static void createRecord(File classRecord, int numStudents) {
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
                students[i].setIDNumber((int) validator(0, 9999));
            } catch (NumberNotInRangeException e) {
                System.out.println(e); // should be unreachable, since the validator handles it already
            }

            // Quiz Scores
            System.out.println("Input the 3 Quiz Scores of Student:");
            for (int j = 0; j < 3; j++) {
                students[i].setQuizScore(j, validator(0, 100));
            }

            // Check if the inputted information is correct
            System.out.println("Is this satisfactory? Input any key to continue or input 0 to redo operation");
            System.out.println(students[i]);
            if (console.next().charAt(0) == '0') {
                i--;
                continue;
            }
        }

        // I handled this outside of the other for-loop to minimize the nesting
        try {
            FileOutputStream fOut = new FileOutputStream(classRecord, false);
            ObjectOutputStream objOut = new ObjectOutputStream(fOut);
            objOut.writeObject(students); // writes the entire array, so we get a more convenient read.
            objOut.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File might be a directory, or does not exist");
        } catch (IOException e) {
            System.out.println("Error with handled file");
        }
    }

    private static void displayClassRecord(File classRecord, String section) {
        Student[] students = null; // the record of each student

        // gets the student array from the file
        try {
            FileInputStream fStream = new FileInputStream(classRecord);
            ObjectInputStream objStream = new ObjectInputStream(fStream);
            students = (Student[]) objStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found, returning to menu");
            return;
        } catch (IOException e) {
            System.out.println("An error occured with the read file, file might be empty. Returning to menu");
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // added values to the table
        double[] averageQuiz = { 0, 0, 0 };
        double[] highestQuiz = { 0, 0, 0 }; // all zeroes, you can only go up
        double[] lowestQuiz = { 100, 100, 100 }; // all 100s, so that we can only go down

        for (int i = 0; i < lowestQuiz.length; i++) {
            lowestQuiz[i] = 100; // you can only go down from here
        }

        // The Table
        createLine();
        System.out.printf("%25s\n", section);
        createLine();
        System.out.printf("%-15s %-5s %6s %6s %6s %6s\n", "Name", "ID#", "Quiz1", "Quiz2", "Quiz3", "Ave");
        for (int i = 0; i < students.length; i++) {
            Student s = students[i];
            String line = String.format("%-15s #%4s %6.2f %6.2f %6.2f %6.2f", s.name, s.getIDString(),
                    s.getQuizScore(0), s.getQuizScore(1), s.getQuizScore(2), s.getAverage());
            System.out.println(line);

            for (int j = 0; j < lowestQuiz.length; j++) {
                averageQuiz[j] += s.getQuizScore(j);

                if (highestQuiz[j] < s.getQuizScore(j))
                    highestQuiz[j] = s.getQuizScore(j);

                if (lowestQuiz[j] > s.getQuizScore(j))
                    lowestQuiz[j] = s.getQuizScore(j);
            }
        }
        createLine();
        System.out.printf("%21s %6.2f %6.2f %6.2f\n", "Average",
                averageQuiz[0] / 3, averageQuiz[1] / 3, averageQuiz[2] / 3);
        System.out.printf("%21s %6.2f %6.2f %6.2f\n", "Highest",
                highestQuiz[0], highestQuiz[1], highestQuiz[2]);
        System.out.printf("%21s %6.2f %6.2f %6.2f\n", "Lowest",
                lowestQuiz[0], lowestQuiz[1], lowestQuiz[2]);
        createLine();
    }

    // a. Average grade per student
    // b. Highest score per quiz
    // c. Lowest score per quiz
    //
    // d. Average score per quiz

    private static void createLine() {
        System.out.println("-------------------------------------------------------");
    }

}

class Student implements Serializable {
    String name = "Juan Dela Cruz";
    private int IDNumber;
    private double[] quizzes;
    private double average = 0;

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

    public double getAverage() {
        // could be faster if it was a multiplication operation rather than division
        return (quizzes[0] + quizzes[1] + quizzes[2]) / 3;
    }

    @Override
    public String toString() {
        String temp = String.format("NAME=%s::ID#=%s::", name, getIDString());
        StringBuilder output = new StringBuilder(temp);
        for (int i = 0; i < quizzes.length; i++) {
            String num_out = String.format("Quiz %d=%.2f::", (i + 1), quizzes[i]);
            output.append(num_out);
        }
        output.append(String.format("Average=%.2f::", getAverage()));
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