package com.example.projectnew;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher extends Person implements Serializable {
    private String qualification;
    private int experience_years;
    private boolean certificate;
    private boolean martial_status;

    public Teacher(String name, int age, int id, long cnic, boolean gender, long contact_info, String email, String address,
                   String qualification, int experience_years, boolean certificate, boolean martial_status) {
        super(name, age, id, cnic, gender, contact_info, email, address);
        this.qualification = qualification;
        this.experience_years = experience_years;
        this.certificate = certificate;
        this.martial_status = martial_status;
    }

    public String getQualification() {
        return qualification;
    }

    public int getExperience_years() {
        return experience_years;
    }

    public String updateInfo(String newEmail, long newContactInfo) {

        setEmail(newEmail);
        setContact_info(newContactInfo);

        return "Teacher info is updated.\nNew email is: " + newEmail + ",\nContact is: " + newContactInfo;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n  Qualification: '" + qualification + '\'' +
                "\n  Experience Years: " + experience_years +
                "\n  Certificate: " + (certificate ? "Yes" : "No") +
                "\n  Martial Status: " + (martial_status ? "Married" : "Single") +
                "\n";
    }
}