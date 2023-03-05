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
                    A - Arithmetic Series
                    B - Geometric Series
                    C - Harmonic Mean
                    D - Geometric Mean
                    E - Taylor Polynomial
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
                    System.out.println("Input the common difference (d, double value):");
                    series.setD(validateDouble());
                    series.arithmetic();
                } else {
                    System.out.println("Input the common ratio (d, double value):");
                    series.setR(validateDouble());
                    series.geometric();
                }
                System.out.println("Sum = " + series.getSum());
            } else if (type == 'C' || type == 'D') {
                Mean mean = new Mean();
                System.out.println("Input numbers, enter 0 to exit");
                if (type == 'C') {
                    try {
                        mean.harmonic(console);
                    } catch (ArithmeticException e) {
                        System.out.println("Error : Division by Zero");
                    }

                }

                else if (type == 'D')
                    mean.geometric(console);
                System.out.println("Mean = " + mean.getMean());
            } else if (type == 'F') {
                System.out.println("Ending Program : GoodBye!");
                break;
            }
            createLine();

        } // end of program loop
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

    public void arithmetic() {
        System.out.print("S = ");
        for (int i = 1; i < n; i++) {
            double value = a + (i - 1) * d;
            System.out.print(value + ", ");
            sum += value;
        }
        double lastValue = a + n - 1 * d;
        System.out.println(lastValue);
    }

    public void geometric() {
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

class Mean {
    private double mean = 0;

    public void harmonic(Scanner input) throws ArithmeticException {
        double sum = 0;
        int n = 0;
        while (true) {
            try {
                double x = input.nextDouble();
                if (x == 0)
                    break;
                sum += 1 / (x);
                n++;
            } catch (InputMismatchException e) {
                System.out.println("Unrecognized Input Try Again");
            }
        }
        if (n == 0)
            throw new ArithmeticException();
        else
            mean = n / sum;
    }

    public void geometric(Scanner input) {
        double product = 1;
        int n = 0;
        while (true) {
            try {
                double x = input.nextDouble();
                if (x == 0)
                    break;
                product *= x;
                n++;
            } catch (InputMismatchException e) {
                System.out.println("Unrecognized Input Try Again");
            }
        }
        mean = Math.pow(product, 1.0 / n);
    }

    public double getMean() {
        return mean;
    }
}