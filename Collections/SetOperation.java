package Collections;

import java.util.*;

public class SetOperation {

    private static String MENU = """
            Choose from one of the given operations
            Given Sets: A, B
            ---------------------------------------
            1.  Add element to a set
            2.  Remove element from a set
            3.  Display a set
            4.  Display Union of A and B
            5.  Display Intersection of A and B
            6.  Display A – B
            7. Display B – A
            8. Check if A is a subset of B
            9. Check if B is a subset of A
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
                if (chosenSet == 'A')
                    A.add(el);
                else
                    B.add(el);
            } // end of menuDecision 1
            else if (menuDecision == 13) // gives visual clarity
                break;
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
    public static <T> HashSet<T> union(HashSet<T> a, HashSet<T> b) {
        // a modified merge algorithm, since we cannot access the individual contents /
        // indices
        HashSet<T> output = new HashSet<>();
        for (T el : a) {
            output.add(el);
        }
        for (T el : b) {
            if (!a.contains(el))
                output.add(el);
        }
        return output;
    }

    public static <T> String display(HashSet<T> a) {
        StringBuilder output = new StringBuilder();
        output.append("[ ");
        for (T el : a) {
            output.append(el + " ");
        }
        output.append("]");
        return output.toString();
    }
}
