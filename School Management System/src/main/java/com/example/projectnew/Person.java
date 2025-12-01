package com.example.projectnew;

import java.io.Serializable;

public class Person implements Serializable {
    //Attributes
    private String name;
    private int age;
    private int id;
    private long cnic;
    private boolean gender;
    private long contact_info;
    private String email;
    private String address;

    //constructor
    public Person(String name, int age, int id, long cnic, boolean gender, long contact_info, String email, String address) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.cnic = cnic;
        this.gender = gender;
        this.contact_info = contact_info;
        this.email = email;
        this.address = address;
    }

    public long getContact_info() {
        return contact_info;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCnic() {
        return cnic;
    }
    public boolean isGender() {
        return gender;
    }
    public void setContact_info(long contact_info) {
        this.contact_info = contact_info;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return
                "\n  Name: '" + name + '\'' +
                "\n  Age: " + age +
                "\n  Id: " + id +
                "\n  Cnic: " + cnic +
                "\n  Gender: " + (gender ? "Male" : "Female") +
                "\n  Contact Info: " + contact_info +
                "\n  Email: '" + email + '\'' +
                "\n  Address: '" + address + '\'' +
                "\n";
    }
}

