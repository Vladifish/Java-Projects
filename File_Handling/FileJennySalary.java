// Vladimir Gray P. Velazco
package File_Handling;

// Conditions:
// Base Rate $10
// Overtime $1.50 >8 hrs
// Bonus: +12% on Saturday, +50% on Sunday
// Saturday and Sunday are not counted in the 40hr week

// Given:
// hrs. worked each day in a week Format (Sunday, ..., Saturday)
// Compute:
// Salary for the week
// Input:
// File, positive integers <= 24
// Output:
// dollars rounded up to the nearest penny
// sample: $2.41666 = $2.42
// 5 sets of data

import java.io.*;

public class FileJennySalary {

    public static void main(String[] args) {
        File jennyFile = new File("File_Handling\\Handleables\\Jenny_WeeklyHours.csv");
        double[] weeklySalary = new double[5];
        try {
            FileReader fr = new FileReader(jennyFile);
            BufferedReader csvReader = new BufferedReader(fr);
            String week;
            while ((week = csvReader.readLine()) != null) {
                String[] hoursWorked = week.split(",");

            }
        } catch (FileNotFoundException e) {
            System.out.println("Error 404: File not Found");
        } catch (IOException e) {
            System.out.println();
        }

    }

    // important to note: week is Sunday, ..., Saturday
    // Meaning that weekdays are at indices 1 to 5
    static double computeWeekdaySalary(String[] hoursWorked) {

        // for (int i=1; i<6; i++) {
        // hoursWorked
        // }
        return 1;
    }
}

enum Salary {
    BASE_RATE(10), OT_RATE(1.50), HOURS_BEYOND_40(2.50),
    SATURDAY_BONUS(1.12), SUNDAY_BONUS(1.12);

    private double rate;

    Salary(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
