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
            int weekCount = 0;

            while ((week = csvReader.readLine()) != null) {
                String[] hoursWorkedPerDay = week.split(",");
                weeklySalary[weekCount] = computeWeekSalary(hoursWorkedPerDay);
                weekCount++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error 404: File not Found");
        } catch (IOException e) {
            System.out.println();
        }
        System.out.println("Jenny's Weekly Salary");
        for (int i = 0; i < weeklySalary.length; i++) {
            System.out.printf("Week %d: $%.2f\n", i + 1, weeklySalary[i]);
        }

    }

    /**
     * Computing for a person's salary in a week
     * important to note: A Week is Sunday, ..., Saturday
     * Meaning that weekdays are at indices 1 to 5
     * 
     * @param hoursWorkedPerDay
     * @return the total salary for the weekdays
     */
    static double computeWeekSalary(String[] hoursWorkedPerDay) {
        int hoursWorkedWeekends = 0;
        double salaryForWeek = 0;

        for (int i = 0; i < 7; i++) {
            double weekendBonus = 1;
            if (i == 0) {
                weekendBonus = Salary.SUNDAY_BONUS.getRate();
            } else if (i == 6) {
                weekendBonus = Salary.SATURDAY_BONUS.getRate();
            }

            int hoursWorked = Integer.parseInt(hoursWorkedPerDay[i]);
            // Base
            salaryForWeek += hoursWorked * Salary.BASE_RATE.getRate() * weekendBonus;
            // Overtime
            salaryForWeek += Math.max(hoursWorked - 8, 0) * Salary.OT_RATE.getRate() * weekendBonus;
            if (i != 0 && i != 6)
                hoursWorkedWeekends += hoursWorked;
        }
        if (hoursWorkedWeekends > 40) {
            salaryForWeek += (hoursWorkedWeekends - 40) * Salary.HOURS_BEYOND_40.getRate();
        }
        return salaryForWeek;
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
