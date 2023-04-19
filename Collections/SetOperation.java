package Collections;

import java.util.*;

public class SetOperation {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        set.add("deez");
        HashSet<String> set1 = new HashSet<>();
        set.add("deez");
        set.add("nutz");

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
        // essentially the merge algorithm in merge sort
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
}
