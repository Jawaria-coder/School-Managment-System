package com.example.projectnew;

public class Principle extends Person{

    public Principle(String name, int age, int id, long cnic, boolean gender, long contact_info, String email,
                      String address) {
        super(name, age, id, cnic, gender, contact_info, email, address);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
