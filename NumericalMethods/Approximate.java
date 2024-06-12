package NumericalMethods;

public class Approximate {
    private static double newtonEstimate(String fx, String dfx, double xprev) {
        double derivative = Polynomial.evaluate(dfx, xprev);
        double function = Polynomial.evaluate(fx, xprev);
        if (derivative == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return xprev - (function / derivative);
    }

    public static double repeatedNewton(String fx, String dfx, double guess, double error) {
        double xprev = guess;
        double xcurr = Double.MAX_VALUE;
        int compute_limit = 100, i = 0; // fail safe in case it gets stuck

        while (i < compute_limit) {
            xcurr = newtonEstimate(fx, dfx, xprev);

            if (Math.abs(xcurr - xprev) <= error)
                return xcurr;
            else
                xprev = xcurr;
            i++;
        }

        System.out.println("Computational Limit Reached");
        return xcurr;
    }

    public static void main(String[] args) {

        // Sample Usage of Newton's Method
        String function = "5x^5 - 3x^3";
        String derivative = "25x^4 - 9x^2";
        double guess = -0.712;
        double approx = repeatedNewton(function, derivative, guess, 0.05);

        System.out.println("---------------------------------------------------\n");
        System.out.printf("Given the function: \"%s\" and its derivative: \"%s\" \n", function, derivative);
        System.out.printf("With %.4f as our initial guess,\nWe reach %.4f as one of its roots \n\n", guess, approx);
        System.out.println("---------------------------------------------------");
    }
}
