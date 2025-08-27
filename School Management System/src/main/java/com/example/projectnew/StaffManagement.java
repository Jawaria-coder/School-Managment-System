package com.example.projectnew;

import java.util.ArrayList;
import java.util.Scanner;

public class StaffManagement {
    private ArrayList<Teacher> teachers;
    private ArrayList<JuniorStaff> staffs;
    private Salary salary;
    public StaffManagement() {
        this.teachers = new ArrayList<>();
        this.staffs = new ArrayList<>();
        this.salary = new Salary();
    }

    public ArrayList<JuniorStaff> getStaffs() {
        return staffs;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }
    public boolean removeTeacher(int Id)
    {
        Teacher teacherToRemove = getTeacherById(Id);
        if (teacherToRemove != null) {
            teachers.remove(teacherToRemove);
            return true;}
        else {
            return false;}
    }
    public void addJuniorStaff(JuniorStaff staff) {
        staffs.add(staff);
    }
    public boolean removeJuniorStaff(int id)
    {
        JuniorStaff staffToRemove = getJuniorStaffById(id);
        if (staffToRemove != null) {
            staffs.remove(staffToRemove);
            return true;}
        else {
            return false;}
    }
    public String TeacherSalary(int id) {
        Teacher teacher = getTeacherById(id);
        if (teacher != null) {
            return "Teacher with ID " + id + " has a salary of: " +
                    salary.calculateSalaryOfTeacher(teacher);}
        else {
            return "No teacher found with the specified ID.";}
    }
    public String JuniorStaffSalary(int id) {
        JuniorStaff staff = getJuniorStaffById(id);
        if (staff != null) {
            return ("Junior Staff with ID " + id + " has a salary of: " +
                    salary.calculateSalaryOfJuniorStaff(staff));}
        else {
            return "Junior Staff not found with the specified ID.";}
    }
    public String UpdateTeacherInfoById(int id, String email, long contact)
    {
        Teacher teacher = getTeacherById(id);
        if (teacher != null) {
            return teacher.updateInfo(email,contact);
        }
        else {
            return "Teacher not found with the specified ID.";
        }
    }


    public JuniorStaff getJuniorStaffById(int id) {
        for (JuniorStaff jt : staffs)
        {
            if (jt.getId() == id)
            {
                return jt;
            }
        }
        return null;
    }
    public Teacher getTeacherById(int id) {
        for (Teacher t : teachers) {
            if (t.getId() == id)
            {
                return t;
            }
        }
        return null;
    }
}
