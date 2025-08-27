package com.example.projectnew;

import java.util.ArrayList;

public class Course1 {
    private int code;
    private String title;

    public Course1(int code, String title) {
        this.code = code;
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public static ArrayList<Course1> initializeCourses(Class classes) {
        ArrayList<Course1> courses = new ArrayList<>();
        if(classes.getClass_name()==5 || classes.getClass_name()==6 || classes.getClass_name()==7 || classes.getClass_name()==8)
        {
            courses.add(new Course1(101,"Computer"));
            courses.add(new Course1(102, "Maths"));
            courses.add(new Course1(103, "Science"));
            courses.add(new Course1(104, "History"));
            courses.add(new Course1(105, "Geography"));
            courses.add(new Course1(106, "Urdu"));
            courses.add(new Course1(107, "English"));
            courses.add(new Course1(108, "Islamiyat"));
        }
        else if (classes.getClass_name()==1 || classes.getClass_name()==2 || classes.getClass_name()==3 || classes.getClass_name()==4)
        {
            courses.add(new Course1(123, "Maths"));
            courses.add(new Course1(114, "Science"));
            courses.add(new Course1(156, "Urdu"));
            courses.add(new Course1(153, "English"));
            courses.add(new Course1(126, "Islamiyat"));
        }

        return courses;
    }
}

