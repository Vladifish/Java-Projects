// Vladimir Gray P. Velazco 1-CSC
package Exceptions;

import java.util.Scanner;

class outOfRangeError extends Error {
    outOfRangeError() {
        System.out.println("Error: Values Not In Range!");
    }

    outOfRangeError(String message) {
        System.out.println(message);
    }
}

public class ExceptionAveGrade {
    public static void main(String[] args) throws outOfRangeError {
        Scanner console = new Scanner(System.in);
        double sum = 0;
        System.out.println("Enter 5 Scores : Values Should be Between 0 and 100 (inclusive)");
        for (int i = 0; i < 5; i++) {
            double x = console.nextDouble();
            if (x < 0 || x > 100)
                throw new outOfRangeError("Error: Numbers Should Be Between 0-100");
            sum += x;
        }
        System.out.println("Average = " + sum / 5);
    }
}