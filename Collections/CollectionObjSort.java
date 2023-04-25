package Collections;

import java.util.*;

class Person {
    private String name;
    private int age = 0;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return getName() + " " + getAge();
    }

}

public class CollectionObjSort {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>(); // create a list
        String name;
        int age;
        Person person;
        Scanner in = new Scanner(System.in);
        System.out.println("\nMenu\n1.Add a Person\n2.Remove a Person\n3.Display Records"
                + "\n4.Delete Records\n5.Search a Person\n6.Sort by Age\n7.Sort by Name\n8.End");
        int ans = 1;
        do {
            System.out.print("\nEnter your choice: ");
            int choice = in.nextInt();
            in.nextLine(); // clear keyboard buffer
            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    name = in.nextLine();
                    System.out.print("Enter age: ");
                    age = in.nextInt();
                    person = new Person(name, age);
                    list.add(person);
                    break;
                case 2:
                    System.out.print("Enter name: ");
                    name = in.nextLine();
                    boolean flag = true;
                    for (Person p : list) {
                        if (p.getName().equals(name)) {
                            list.remove(p);
                            flag = false;
                            System.out.println(p.getName() + " is removed from the list");
                            break;
                        }
                    }
                    if (flag)
                        System.out.println("The person is not in the list");
                    break;
                case 3:
                    if (list.isEmpty())
                        System.out.println("The list is empty!");
                    else {
                        System.out.println("List of Persons: ");
                        for (Person p : list)
                            System.out.println(p);
                    }
                    break;
                case 4:
                    list.clear();
                    break;
                case 5:
                    System.out.print("Enter name to search: ");
                    name = in.next();
                    flag = true;
                    for (Person p : list) {
                        if (p.getName().equals(name)) {
                            System.out.println(p.getName() + " is in the list");
                            flag = false;
                            break;
                        }
                    }
                    if (flag)
                        System.out.println("The person is not in the list");
                    break;
                case 6:
                    List<Person> sorted = sortByAge(list);
                    Iterator<Person> iter = sorted.iterator();
                    while (iter.hasNext()) {
                        Person curr = iter.next();
                        System.out.printf("Age: %d, %s\n", curr.getAge(), curr.getName());
                    }
                    break;
                case 8:
                    ans = 0;
            }
        } while (ans != 0);
        System.out.println();
    }

    /*
     * Lambda Expressions :D
     * Reference Code: Java 67,
     * https://www.java67.com/2014/11/java-8-comparator-example-using-lambda-
     * expression.html
     */
    public static List<Person> sortByAge(List<Person> persons) {
        if (persons.size() == 0) // Avoids Any Index Errors
            return null;
        List<Person> sorted = persons;
        final Comparator<Person> AGE_COMPARATOR = new Comparator<>() {
            @Override
            public int compare(Person p1, Person p2) {
                Integer a1 = p1.getAge();
                return a1.compareTo(p2.getAge());
            }
        };
        Collections.sort(sorted, AGE_COMPARATOR);
        return sorted;
    }

    public static List<Person> sortByName(List<Person> persons) {
        if (persons.size() == 0) // Avoids Any Index Errors
            return null;

        List<Person> sorted = new ArrayList<>(persons.size());

        return sorted;
    }
}