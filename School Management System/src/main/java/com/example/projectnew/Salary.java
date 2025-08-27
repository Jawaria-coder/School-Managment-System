package com.example.projectnew;

public class Salary {
    private double base_salary_of_teacher = 50000.0;
    private double base_salary_of_junior_staff = 20000.0;

    private JuniorStaff staffs;
    private Teacher teachers;

    public Salary() {
        this.teachers = teachers;
        this.staffs = staffs;
    }

    public double calculateSalaryOfTeacher(Teacher teacher) {
        double totalSalary = base_salary_of_teacher;

        int experience = teacher.getExperience_years();
        String qualification = teacher.getQualification();
        double bonus = 0.0;

        if (experience > 5 && experience < 10)
        {
            bonus += 10000.0;
        }
        else if (experience > 10 && experience < 15)
        {
            bonus += 15000.0;
        }
        else if (experience > 15)
        {
            bonus += 20000.0;
        }

        if (qualification.equalsIgnoreCase("PhD"))
        {
            bonus += 15000.0;
        }
        else if (qualification.equalsIgnoreCase("Masters"))
        {
            bonus += 10000.0;
        }
        else if (qualification.equalsIgnoreCase("Bachelors"))
        {
            bonus += 5000.0;
        }

        totalSalary += bonus;
        return totalSalary;
    }

    public double calculateSalaryOfJuniorStaff(JuniorStaff staff) {
        double totalSalary = base_salary_of_junior_staff;

        int experience = staff.getExperience();
        double bonus = 0.0;

        if (experience > 2 && experience < 5)
        {
            bonus += 2000.0;
        }
        else if (experience > 5 && experience < 10)
        {
            bonus += 6000.0;
        }
        else if (experience > 10)
        {
            bonus += 10000.0;
        }

        totalSalary += bonus;
        return totalSalary;
    }
}