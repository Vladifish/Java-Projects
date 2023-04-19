// Vladimir Gray P. Velazco 1CSC
package File_Handling;

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

    public static void main(String[] args) {
        
    }

}

class Student {
    private String name = "Juan Dela Cruz";
    final byte ID_NUMBER; // went with bits, since the ID_NUMBER would only be 4 bits
    private int[] quizzes;

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
