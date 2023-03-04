package Exceptions;

public class ExceptionMeanSeries {

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

    public double getSum() {
        return sum;
    }

}