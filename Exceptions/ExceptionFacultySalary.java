// Vladimir Gray P. Velazco 1-CSC
package Exceptions;

import java.util.Scanner;

public class ExceptionFacultySalary {
    private static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println("Good Day! What is Your Position?");
            System.out.println("A - " + Instructor.getPositionName());
            System.out.println("B - " + AsstProfessor.getPositionName());
            System.out.println("C - " + Professor.getPositionName());
            System.out.println("Enter the letter of your position: ");
            // Input Validation
            String position = console.next().toUpperCase();
            while (!(position.equals("A") || position.equals("B") || position.equals("C"))) {
                System.out.println("Invalid Input : Input Again");
                position = console.next().toUpperCase();
            }
            Faculty member;
            switch (position) {
                case "A":
                    member = new Instructor();
                    break;
                case "B":
                    member = new AsstProfessor();
                    break;
                default:
                    member = new Professor();
            }

        } while (run());
    }

    private static boolean run() {
        System.out.println("Continue? Press Any Key to Continue, 0 to Exit");
        return console.nextDouble() != 0;
    }
}

class InvalidScoreError extends Error {
    InvalidScoreError() {
        System.out.println("Error : Added Score Not In Range");
    }

    InvalidScoreError(String message) {
        System.out.println(message);
    }
}

interface Faculty {
    int getLectureRate();

    int getLabRate();

    int getAllowablePreparations();

    double getPreparationRate();

    default double getCompetencyRate(double score) throws InvalidScoreError {
        if (score < 22)
            return 0;
        else if (score <= 24)
            return 0.05;
        else if (score < 27)
            return 0.065;
        else
            return 0.075;
    }
}

class Instructor implements Faculty {

    @Override
    public int getLectureRate() {
        return 2100;
    }

    @Override
    public int getLabRate() {
        return 2450;
    }

    @Override
    public int getAllowablePreparations() {
        return 3;
    }

    @Override
    public double getPreparationRate() {
        return 0.023;
    }

    public static String getPositionName() {
        return "Instructor";
    }

}

class AsstProfessor implements Faculty {

    @Override
    public int getLectureRate() {
        return 2550;
    }

    @Override
    public int getLabRate() {
        return 2950;
    }

    @Override
    public int getAllowablePreparations() {
        return 4;
    }

    @Override
    public double getPreparationRate() {
        return 0.03;
    }

    public static String getPositionName() {
        return "Assistant Professor";
    }

}

class Professor implements Faculty {

    @Override
    public int getLectureRate() {
        return 3160;
    }

    @Override
    public int getLabRate() {
        return 3500;
    }

    @Override
    public int getAllowablePreparations() {
        return 4;
    }

    @Override
    public double getPreparationRate() {
        return 0.04;
    }

    public static String getPositionName() {
        return "Professor";
    }

}