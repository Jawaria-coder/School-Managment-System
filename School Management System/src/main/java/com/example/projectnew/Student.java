package com.example.projectnew;

import java.io.Serializable;
import java.util.ArrayList;

public class Student extends Person implements Serializable {
    private String father_name;
    private String mother_name;
    private long father_contact;
    private long mother_contact;
    private String father_business;
    private double father_income;

    public String getFather_name() {
        return father_name;
    }

    public String getMother_name() {
        return mother_name;
    }

    public long getFather_contact() {
        return father_contact;
    }

    public long getMother_contact() {
        return mother_contact;
    }

    public String getFather_business() {
        return father_business;
    }

    public String getFather_email() {
        return father_email;
    }

    private String father_email;
    private ArrayList<Course1> courses;

    public Student(String name, int age, int id, long cnic, boolean gender, long contact_info, String email, String address,
                   String father_name, String mother_name, long father_contact, long mother_contact, String father_business,
                   double father_income, String father_email) {
        super(name, age, id, cnic, gender, contact_info, email, address);
        this.father_name = father_name;
        this.mother_name = mother_name;
        this.father_contact = father_contact;
        this.mother_contact = mother_contact;
        this.father_business = father_business;
        this.father_income = father_income;
        this.father_email = father_email;
        this.courses = new ArrayList<Course1>();
    }


    public ArrayList<Course1> getCourses() {
        return courses;
    }

    public void setFather_contact(long father_contact) {
        this.father_contact = father_contact;
    }

    public void setMother_contact(long mother_contact) {
        this.mother_contact = mother_contact;
    }

    public void setFather_email(String father_email) {
        this.father_email = father_email;
    }

    public double getFather_income() {
        return father_income;
    }


    public void assignCourses(ArrayList<Course1> coursesToAssign) {
        courses.addAll(coursesToAssign);
    }


    public String UpdateInfo(String newEmail, long newContactInfo, long new_father_contact, long new_mother_contact, String new_father_email)
    {
        setEmail(newEmail);
        setContact_info(newContactInfo);
        setFather_contact(new_father_contact);
        setMother_contact(new_mother_contact);
        setFather_email(new_father_email);

        return "Student info is updated.\nNew email is: " + newEmail + ",\nContact is: " + newContactInfo + ",\nFather contact: "
                + new_father_contact + ",\nMother contact: " + new_mother_contact + ",\nFather E-mail: " + new_father_email;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n  Father Name: '" + father_name + '\'' +
                "\n  mother Name: '" + mother_name + '\'' +
                "\n  Father Contact: " + father_contact +
                "\n  Mother Contact: " + mother_contact +
                "\n  Father Business: '" + father_business + '\'' +
                "\n  Father Income: " + father_income +
                "\n  Father Email: '" + father_email + '\'' +
                "\n  Courses: " + courses +
                "\n";
    }
}
