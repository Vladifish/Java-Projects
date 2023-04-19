package Collections;

import java.util.*;

public class SetOperation {
    public static void main(String[] args) {

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
    public static <T> String union(HashSet<T> a, HashSet<T> b) {
        // essentially the merge algorithm in merge sort
        StringBuilder output = new StringBuilder("[ ");
        for (T el : a) {
            output.append(el.toString() + " ");
        }
        for (T el : b) {
            if (!a.contains(el))
                output.append(el + " ");
        }
        output.append(" ]");
        return output.toString();
    }
}
