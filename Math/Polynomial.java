package Math;

import java.util.ArrayList;

public class Polynomial {

    /**
     * Evaluates a simplified polynomial expression using Horner's Rule.
     * This function cannot currently evaluate fractional and negative exponents.
     * <p>
     * Formatted as follows: <b>5x^2 + 2x - 3</b>.
     * </p>
     * <ol>
     * <li>Add a plus (+) or minus (-) to separate each part.</li>
     * <li>Add the caret <i>(^)</i> symbol to signify power</li>
     * <li>Exponents should be in decreasing order</li>
     * </ol>
     * <br>
     * 
     * @param expression a simplified polynomial expression
     * @param x
     * 
     * @return evaluated expression
     */
    public static double evaluate(String expression, double x) throws ArithmeticException, NumberFormatException {

        if (expression.equals("") || expression == null) {
            throw new NumberFormatException("Expression is empty");
        }

        ArrayList<String> separated_list = splitExpression(expression);
        String[] separated = new String[separated_list.size()];
        separated = separated_list.toArray(separated);

        // checks how many iterations should be done
        int max_power = 0; // default
        if (separated[0].contains("x")) {
            if (separated[0].contains("^"))
                max_power = Integer.parseInt(separated[0].split("\\^")[1]);
            else
                max_power = 1;
        }

        // the evaluation part
        double out = 0;
        for (int i = max_power, j = 0, power = max_power; i >= 0; i--) {
            if (power == i) {
                if (i > 0) {
                    String num_str = separated[j].split("x")[0];
                    if (!num_str.equals("") && num_str != null) {
                        out = (out + Double.parseDouble(num_str)) * x;
                    } else {
                        out = (out + 1) * x;
                    }
                } else {
                    out += Double.parseDouble(separated[j]);
                }

                // fixes the exponent
                j = (j + 1 == separated.length) ? j : j + 1; // stops possible overflow
                if (separated[j].contains("x")) {
                    if (separated[j].contains("^")) {
                        power = Integer.parseInt(separated[j].split("\\^")[1]);
                    } else
                        power = 1;
                } else if (!separated[j].contains("x")) {
                    power = 0;
                }

                // System.out.printf("Evaluate step: %.2f \n", out);
            } else {
                if (i > 0)
                    out *= x;
                // System.out.printf("Evaluate step skip: %.2f \n", out);
            }
        }

        return out;
    }

    private static ArrayList<String> splitExpression(String expression) {
        ArrayList<String> separated_list = new ArrayList<>();

        for (String part : expression.split("\\s*[+]\\s*")) {
            if (part.contains("-")) {
                String[] negatives = part.split("\\s*[-]\\s*");

                if (!negatives[0].equals("") && negatives[0] != null)
                    separated_list.add(negatives[0]); // this should always be positive

                for (int i = 1; i < negatives.length; i++)
                    separated_list.add("-" + negatives[i]);

            } else {
                separated_list.add(part);
            }
        }

        return separated_list;
    }

    public static void main(String[] args) {
        double exp = Polynomial.evaluate("3x^2 + 3", 2);
        System.out.println(exp);
    }
}
