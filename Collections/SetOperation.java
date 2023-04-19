package Collections;

import java.util.*;

public class SetOperation { // TODO: Input Validation

    private static String MENU = """
            Choose from one of the given operations
            Given Sets: A, B
            ---------------------------------------
            1.  Add element to a set
            2.  Remove element from a set
            3.  Display a set
            4.  Display Union of A and B
            5.  Display Intersection of A and B
            6.  Display A - B
            7.  Display B - A
            8.  Check if A is a subset of B
            9.  Check if B is a subset of A
            10. Quit
            ---------------------------------------
                """;

    public static void main(String[] args) {
        HashSet<String> A = new HashSet<>();
        HashSet<String> B = new HashSet<>();
        Scanner console = new Scanner(System.in);
        while (true) {
            System.out.println(MENU);
            int menuDecision = console.nextInt();
            if (menuDecision == 1) {
                System.out.println("Set A or B?");
                char chosenSet = console.next().toUpperCase().charAt(0);
                System.out.println("Input element to add:");
                String el = console.next();

                boolean added;
                if (chosenSet == 'A')
                    added = A.add(el); // returns true if the value is unique
                else
                    added = B.add(el);

                if (added)
                    System.out.println("Successfully Added Element to Set");
                else
                    System.out.println("Element Already in Set");
            } // end of menuDecision 1
            else if (menuDecision == 2) {
                System.out.println("Set A or B?");
                char chosenSet = console.next().toUpperCase().charAt(0);
                System.out.println("Input element to remove:");
                String el = console.next();

                boolean removed;
                if (chosenSet == 'A')
                    removed = A.remove(el); // returns true if the value is unique
                else
                    removed = B.remove(el);

                if (removed)
                    System.out.println("Successfully Removed Element in Set");
                else
                    System.out.println("Element Not in Set");
            } // end of menuDecision 2
            else if (menuDecision == 3) {
                System.out.println("Specify which set to display:");
                char chosenSet = console.next().toUpperCase().charAt(0);

                if (chosenSet == 'A')
                    System.out.println("A=" + Operations.<String>display(A));
                else
                    System.out.println("B=" + Operations.<String>display(B));
            } // end of menuDecision 3
            else if (menuDecision == 4) {
                HashSet<String> union = Operations.<String>union(A, B);
                System.out.println("A U B = " + Operations.<String>display(union));
            } // end of menu decision 4
            else if (menuDecision == 5) {
                HashSet<String> intersection = Operations.<String>intersection(A, B);
                System.out.println("A intersection B = " + Operations.<String>display(intersection));
            } // end of menu decision 5
            System.out.println();

            if (menuDecision == 10)
                break;

        } // end of the program loop
        System.out.println("EXITING: Good Bye!");
    } // end of void-main
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
}
