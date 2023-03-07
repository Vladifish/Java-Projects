// Vladimir Gray P. Velazco 1-CSC
package Exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionFacultySalary {
    private static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            createLine();
            System.out.println("Good Day! What is Your Position?");
            System.out.println("A - Instructor");
            System.out.println("B - Assistant Professor");
            System.out.println("C - Professor");
            System.out.println("Enter the letter of your position: ");
            // Input Validation
            String position = console.next().toUpperCase();
            while (!(position.equals("A") || position.equals("B") || position.equals("C"))) {
                System.out.println("Invalid Input : Input Again");
                position = console.next().toUpperCase();
            }
            createLine();

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

            System.out.println("Input number of lecture units: ");
            int lectureUnits = validateUnits();
            System.out.println("Input number of lab units: ");
            int labUnits = validateUnits();
            System.out.printf("Input Number of Preparations (can only be less than or equal to %d):\n",
                    member.getAllowablePreparations());
            int preparations = validatePreparations(member.getAllowablePreparations());

            System.out.println("Input faculty evaluation score: ");
            double evalScore = validateEvalScore();
            createLine();

            // salary = number of lecture units*lecture rate + number of lab units*lab rate
            // SB = number of preparations*PR*Salary + CMR*Salary
            double salary = lectureUnits * member.getLectureRate() + labUnits * member.getLabRate();
            double SB = preparations * member.getPreparationRate() * salary
                    + member.getCompetencyRate(evalScore) * salary;
            displayEndMessage(member, salary, SB);
            createLine();
        } while (run());
    }

    private static boolean run() {
        System.out.println("Continue? Press Any Key to Continue, 0 to Exit");
        return console.nextDouble() != 0;
    }

    private static void displayEndMessage(Faculty member, double salary, double SB) {
        System.out.println("Hello " + member.getPositionName());
        System.out.printf("Your calculated salary for this month is: %.2f. While Your Semestral Bonus is: %.2f\n",
                salary, SB);
    }

    private static int validateUnits() {
        int units;
        while (true) {
            try {
                units = Integer.parseInt(console.next());
                if (units > 0)
                    return units;
                // Unreachable if correct value
                System.out.println("Value must be greater than 0");
            } catch (NumberFormatException e) {
                System.out.println("Error : Value Must be a Number");
            } catch (InputMismatchException e) {
                System.out.println("Error : Value Must be an Integer");
            }
        }
    }

    private static int validatePreparations(int max) {
        int preparations;
        while (true) {
            try {
                preparations = Integer.parseInt(console.next());
                if (preparations > 0 && preparations <= max)
                    return preparations;
                // Unreachable if correct value
                System.out.println("Value must be greater than 0 but less than " + max);
            } catch (NumberFormatException e) {
                System.out.println("Error : Value Must be a Number");
            } catch (InputMismatchException e) {
                System.out.println("Error : Value Must be an Integer");
            }
        }
    }

    private static double validateEvalScore() {
        double evalScore;
        while (true) {
            try {
                evalScore = Double.parseDouble(console.next());
                if (evalScore >= 0 && evalScore <= 30)
                    return evalScore;
                // Unreachable if correct value
                throw new InvalidScoreError("Error : Value must be between 0 and 30 (inclusive)");
            } catch (NumberFormatException e) {
                System.out.println("Error : Value Must be a Number");
            } catch (InvalidScoreError e) {
                System.out.println(e);
            }
        }
    }

    private static void createLine() {
        System.out.println("--------------------------------------------");
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

    String getPositionName();

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

    public String getPositionName() {
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

    public String getPositionName() {
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

    public String getPositionName() {
        return "Professor";
    }

}