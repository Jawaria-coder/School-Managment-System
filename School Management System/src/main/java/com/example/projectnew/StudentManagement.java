package com.example.projectnew;
import java.util.ArrayList;

public class StudentManagement {
    private ArrayList<Student> students;
    private Fee fee;
    ArrayList<Course1> courses;
    private Scholarship scholarship;
    public StudentManagement() {
        this.students = new ArrayList<>();
        this.fee = new Fee(0.0, 0.0, 0.0);
        this.scholarship = new Scholarship(fee);

        students.add(new Student("Asher", 12,9,123456,true,876543,
                "asher@email.com" , "104 House lahore" , "Kashif" , "Aleena",
                45678,8765432, "Sales Person", 11000.0, "father@email.com"));
    }

    public void admitStudent(Student student) {
        students.add(student);
    }
    public boolean removeStudent(int studentId)
    {
        Student studentToRemove = getStudentById(studentId);
        if (studentToRemove != null)
        {
            students.remove(studentToRemove);
            return true;
        }
        else
        {
            return false;
        }
    }
    public Student getStudentById(int id)
    {
        for (Student student : students)
        {
            if (student.getId() == id)
            {
                return student;
            }
        }
        return null;
    }
    public String DisplayingFee(int id)
    {
        Student student = getStudentById(id);
        if (student != null) {
            double studentFee = fee.StudentFee(student);
            return ("Fee for student " + student.getName()
                    + ": " + studentFee);}
        else {
            return "Student not found with the specified ID.";}
    }
    public String UpdatingInfoById(int id, String newEmail, long newContactInfo,
                                   long new_father_contact, long new_mother_contact,
                                   String new_father_email) {
        Student student = getStudentById(id);
        if (student != null) {
            return student.UpdateInfo(newEmail,
                    newContactInfo, new_father_contact,
                    new_mother_contact, new_father_email);}
        else {
            return "Student not found with the specified ID.";}
    }
    public String ScholarShip(int id)
    {
        Student student = getStudentById(id);

        if (student != null)
        {
            return scholarship.StudentscholarShip(student);
        }
        else
        {
            return "Student not found with the specified ID.";
        }
    }
    public String assignCoursesToStudent(int id, Class classes) {
        Student student = getStudentById(id);

        if (student != null) {
            courses = Course1.initializeCourses(classes);
            student.assignCourses(courses);
            return "Courses assigned to student " + student.getName() + ".";
        } else {
            return "Student not found with the specified ID.";
        }
    }
    public String viewAssignedCourses(int studentId) {
        Student student = getStudentById(studentId);

        if (student != null) {
            courses = student.getCourses();
            String result = "Assigned courses for student " + student.getName() + " (ID: " + student.getId() + "):\n";

            for (Course1 course : courses) {
                result += course.getCode() + " - " + course.getTitle() + "\n";
            }

            return result;
        }
        else {
            return "Student not found with the specified ID.";
        }
    }
}

