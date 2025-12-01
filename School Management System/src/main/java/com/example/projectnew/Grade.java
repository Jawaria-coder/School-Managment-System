package com.example.projectnew;

import java.util.ArrayList;

public class Grade {
    private Class classes;
    private int midtermMarks;
    private int finalTermMarks;
    private ArrayList<Integer> marks; // Stores marks dynamically

    public Grade(Class classes) {
        this.classes = classes;
        // Initialize marks with number of courses
        ArrayList<Course1> courses = Course1.initializeCourses(classes);
        this.marks = new ArrayList<>();
        for (int i = 0; i < courses.size(); i++) {
            marks.add(0); // default 0 marks
        }
    }

    public void setMark(int courseIndex, int mark) {
        if (courseIndex < 0 || courseIndex >= marks.size()) throw new IndexOutOfBoundsException("Invalid course index");
        marks.set(courseIndex, mark);
    }

    public int getMark(int courseIndex) {
        if (courseIndex < 0 || courseIndex >= marks.size()) throw new IndexOutOfBoundsException("Invalid course index");
        return marks.get(courseIndex);
    }

    public void setMidtermMarks() {
        midtermMarks = 0;
        for (int mark : marks) midtermMarks += mark;
    }

    public void setFinalTermMarks() {
        finalTermMarks = 0;
        for (int mark : marks) finalTermMarks += mark;
    }

    public double calculateMidtermPercentage() {
        ArrayList<Course1> courses = Course1.initializeCourses(classes);
        int maxMarks = courses.size() * 50; // Midterm max marks per course = 50
        return ((double) midtermMarks / maxMarks) * 100;
    }

    public double calculateFinalTermPercentage() {
        ArrayList<Course1> courses = Course1.initializeCourses(classes);
        int maxMarks = courses.size() * 100; // Final max marks per course = 100
        return ((double) finalTermMarks / maxMarks) * 100;
    }

    public String getMidtermGrade() {
        return calculateGrade(calculateMidtermPercentage());
    }

    public String getFinalTermGrade() {
        return calculateGrade(calculateFinalTermPercentage());
    }

    private String calculateGrade(double percentage) {
        if (percentage >= 90) return "A";
        else if (percentage >= 80) return "B";
        else if (percentage >= 70) return "C";
        else if (percentage >= 60) return "D";
        else return "F";
    }

    public boolean hasInvalidMidTerm() {
        for (int mark : marks) if (mark > 50) return true;
        return false;
    }

    public boolean hasInvalidFinalTerm() {
        for (int mark : marks) if (mark > 100) return true;
        return false;
    }
}
