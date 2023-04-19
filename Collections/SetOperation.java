package Collections;

import java.util.*;

public class SetOperation {

    private static String MENU = """
            ---------------------------------------
            Choose from one of the given operations
            Given Sets: A, B
            ---------------------------------------
            1.  Add element to Set A
            2.  Add element to Set B
            3.  Remove element from Set A
            4.  Remove element from Set B
            5.  Display Set A
            6.  Display Set B
            7.  Display Union of A and B
            8.  Display Intersection of A and B
            9.  Display A - B
            10. Display B - A
            11. Check if A is a subset of B
            12. Check if B is a subset of A
            13. Quit
            ---------------------------------------""";
    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        HashSet<String> A = new HashSet<>();
        HashSet<String> B = new HashSet<>();
        int[] menuOptions = new int[13];

        for (int i = 0; i < 13; i++)
            menuOptions[i] = i + 1;
        while (true) {
            System.out.println(MENU);

            int menuDecision = validate(menuOptions);
            if (menuDecision == 1) {
                System.out.println("Input element to add:");
                String el = console.next();

                if (A.add(el))
                    System.out.println("Successfully Added Element to Set");
                else
                    System.out.println("Element Already in Set");

            } else if (menuDecision == 2) {
                System.out.println("Input element to add:");
                String el = console.next();

                if (B.add(el))
                    System.out.println("Successfully Added Element to Set");
                else
                    System.out.println("Element Already in Set");

            } else if (menuDecision == 3) {
                System.out.println("Input element to remove:");
                String el = console.next();

                if (A.remove(el))
                    System.out.println("Successfully Removed Element in Set");
                else
                    System.out.println("Element Not in Set");

            } else if (menuDecision == 4) {
                System.out.println("Input element to remove:");
                String el = console.next();

                if (B.remove(el))
                    System.out.println("Successfully Removed Element in Set");
                else
                    System.out.println("Element Not in Set");

            } else if (menuDecision == 5) {
                System.out.println("A=" + Operations.<String>display(A));

            } else if (menuDecision == 6) {
                System.out.println("B=" + Operations.<String>display(B));

            } else if (menuDecision == 7) {
                HashSet<String> union = Operations.<String>union(A, B);
                System.out.println("A U B = " + Operations.<String>display(union));

            } else if (menuDecision == 8) {
                HashSet<String> intersection = Operations.<String>intersection(A, B);
                System.out.println("A intersection B = " + Operations.<String>display(intersection));

            } else if (menuDecision == 9) {
                HashSet<String> subtracted = Operations.<String>subtract(A, B);
                System.out.println("A - B = " + Operations.<String>display(subtracted));

            } else if (menuDecision == 10) {
                HashSet<String> subtracted = Operations.<String>subtract(B, A);
                System.out.println("B - A = " + Operations.<String>display(subtracted));

            } else if (menuDecision == 11) {
                if (Operations.<String>isSubsetOf(A, B))
                    System.out.println("A is a subset of B");
                else
                    System.out.println("A is not a subset of B");

            } else if (menuDecision == 12) {
                if (Operations.<String>isSubsetOf(B, A))
                    System.out.println("B is a subset of A");
                else
                    System.out.println("B is not a subset of A");

            } // end of decision 9
            System.out.println();

            if (menuDecision == 13)
                break;

        } // end of the program loop
        System.out.println("EXITING: Good Bye!");
    } // end of void-main

    private static int validate(int[] options) {
        while (true) {
            try {
                int input = Integer.parseInt(console.next());
                for (int item : options) {
                    if (item == input)
                        return item;
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Incompatible Type");
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Input must be a number");
            } catch (Error e) {
                System.out.println(e);
            }
        }
    }
}

// Menu
// 1. Add element to Set A
// 2. Add element to Set B
// 3. Remove element from Set A
// 4. Remove element from Set B
// 5. Display Set A
// 6. Display Set B
// 7. Display Union of A and B
// 8. Display Intersection of A and B
// 9. Display A – B
// 10. Display B – A
// 11. Check if A is a subset of B
// 12. Check if B is a subset of A
// 13. Quit

class Operations {
    public static <T> HashSet<T> union(HashSet<T> A, HashSet<T> B) {
        // a modified merge algorithm, since we cannot access the individual contents /
        // indices
        HashSet<T> output = new HashSet<>();
        for (T el : A) {
            output.add(el);
        }
        for (T el : B) {
            if (!A.contains(el))
                output.add(el);
        }
        return output;
    }

    public static <T> String display(HashSet<T> A) {
        if (A.isEmpty())
            return "[ ]";

        StringBuilder output = new StringBuilder();
        output.append("[ ");
        for (T el : A) {
            output.append(el + " ");
        }
        output.append("]");
        return output.toString();
    }

    public static <T> HashSet<T> intersection(HashSet<T> A, HashSet<T> B) {
        // a modified merge algorithm, since we cannot access the individual contents /
        // indices
        HashSet<T> output = new HashSet<>();
        for (T el : A) {
            if (B.contains(el))
                output.add(el);
        }
        return output;
    }

    public static <T> HashSet<T> subtract(HashSet<T> A, HashSet<T> B) {
        // a modified merge algorithm, since we cannot access the individual contents /
        // indices
        HashSet<T> output = new HashSet<>();
        for (T el : A) {
            if (!B.contains(el))
                output.add(el);
        }
        return output;
    }

    public static <T> boolean isSubsetOf(HashSet<T> A, HashSet<T> B) {
        for (T el : A) { // all elements of A must be in B, for it to be considered a subset
            if (!B.contains(el))
                return false;
        }
        return true;
    }
}
