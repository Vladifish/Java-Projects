// Vladimir Gray P. Velazco 1-CSC
package Exceptions;

public class ExceptionFacultySalary {
    public static void main(String[] args) {
        Faculty member = new Instructor();

        System.out.println(member.getCompetencyRate(5));
    }
}

interface Faculty {
    int getLectureRate();

    int getLabRate();

    int getAllowablePreparations();

    double getPreparationRate();

    default double getCompetencyRate(double score) {
        if (score < 22)
            return 0;
        else if (score <= 24)
            return 0.05;
        else if (score < 27)
            return 0.065;
        else
            return 0.075;
    }
}

class Instructor implements Faculty {

    @Override
    public int getLectureRate() {
        return 2100;
    }

    @Override
    public int getLabRate() {
        return 2450;
    }

    @Override
    public int getAllowablePreparations() {
        return 3;
    }

    @Override
    public double getPreparationRate() {
        return 0.023;
    }

}

class AsstProfessor implements Faculty {

    @Override
    public int getLectureRate() {
        return 2550;
    }

    @Override
    public int getLabRate() {
        return 2950;
    }

    @Override
    public int getAllowablePreparations() {
        return 4;
    }

    @Override
    public double getPreparationRate() {
        return 0.03;
    }

}

class Professor implements Faculty {

    @Override
    public int getLectureRate() {
        return 3160;
    }

    @Override
    public int getLabRate() {
        return 3500;
    }

    @Override
    public int getAllowablePreparations() {
        return 4;
    }

    @Override
    public double getPreparationRate() {
        return 0.04;
    }

}