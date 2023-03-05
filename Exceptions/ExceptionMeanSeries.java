// Vladimir Gray P. Velazco
package Exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionMeanSeries {
    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {

        createLine();

        while (true) {
            System.out.println("Multi-function Calculator :D");
            createLine();
            System.out.println // creates options
            ("""
                    Select Function:
                    A - Hailstone Sequence
                    B - Harmonic Mean
                    C - Geometric mean
                    D - Taylor Polynomial of Degree n
                    E - Prime Number Test
                    F - Quit the Program""");
            createLine();
            char type = validateChoice();
            createLine();
            if (type == 'A' || type == 'B') {
                Series series = new Series();
                System.out.println("Input the first term (a, double value):");
                series.setA(validateDouble());
                System.out.println("Input the number of items (n, must be a positive integer):");
                series.setAndValidateN(console);
                if (type == 'A') {
                    series.setD(validateDouble());
                    series.arithmetic();
                } else {
                    series.setR(validateDouble());
                    series.geometric();
                }
                System.out.println("Sum = " + series.getSum());
            }

        } // end of main loop
    }

    private static char validateChoice() {
        char validatedInput = console.next().toUpperCase().charAt(0);
        while (validatedInput != 'A' && validatedInput != 'B' && validatedInput != 'C' &&
                validatedInput != 'D' && validatedInput != 'E' && validatedInput != 'F') {
            System.out.println("Unrecognized Input : Input Again!");
            validatedInput = console.next().toUpperCase().charAt(0);
        }
        return validatedInput;
    }

    private static double validateDouble() {
        double validatedInput = 0;
        while (true) {
            try {
                validatedInput = Integer.parseInt(console.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Unrecognized Input : Input a double value");
            }
        }
        return validatedInput;
    }

    private static void createLine() {
        System.out.println("------------------------------------------");
    }

}

class Series {
    private int n;
    private double a, d, r;
    private double sum = 0;

    void arithmetic() {
        System.out.print("S = ");
        for (int i = 1; i < n; i++) {
            double value = a + (i - 1) * d;
            System.out.print(value + ", ");
            sum += value;
        }
        double lastValue = a + n - 1 * d;
        System.out.println(lastValue);
    }

    void geometric() {
        System.out.print("S = ");
        for (int i = 1; i < n; i++) {
            double value = a * Math.pow(r, i - 1);
            System.out.print(value + ", ");
            sum += value;
        }
        double lastValue = a + n - 1 * d;
        System.out.println(lastValue);
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setD(double d) {
        this.d = d;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getSum() {
        return sum;
    }

    public void setAndValidateN(Scanner input) {
        while (true) {
            try {
                n = Integer.parseInt(input.next());
                if (n <= 0) {
                    System.out.println("Please input an integer greater than 0");
                    n = Integer.parseInt(input.next());
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please input an integer");
            } catch (NumberFormatException e) {
                System.out.println("Unrecognized Input : Input again");
            }
        }
    }

}