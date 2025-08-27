package com.example.projectnew;

import java.util.ArrayList;

public class JuniorStaff extends Person{
    private int experience;

    public JuniorStaff(String name, int age, int id, long cnic, boolean gender, long contact_info, String email, String address, int experience) {
        super(name, age, id, cnic, gender, contact_info, email, address);
        this.experience = experience;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n  Experience: " + experience +
                "\n";
    }
}

