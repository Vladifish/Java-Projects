// Vladimir Gray P. Velazco
package Exceptions;

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
            char type = validate();
            createLine();
            switch (type) {
                case 'A':
            }
        } // end of main loop
    }

    private static char validate() {
        char validatedInput = console.next().toUpperCase().charAt(0);
        while (validatedInput != 'A' && validatedInput != 'B' && validatedInput != 'C' &&
                validatedInput != 'D' && validatedInput != 'E' && validatedInput != 'F') {
            System.out.println("Unrecognized Input : Input Again!");
            validatedInput = console.next().toUpperCase().charAt(0);
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

}