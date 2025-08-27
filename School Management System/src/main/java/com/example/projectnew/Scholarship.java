package com.example.projectnew;

public class Scholarship {

    private Fee fee;
    public Scholarship(Fee fee) {
        this.fee = fee;
    }

    public String StudentscholarShip(Student student)
    {
        if(student.getFather_income()<=10000.0)
        {
            double total_fee = fee.StudentFee(student) - 5000.0;
            return "This student is eligible for scholarship.\n Adjusted Fee after Scholarship: \n" + total_fee;
        }
        else
        {
            return "This student is not eligible for scholarship.";
        }
    }
}



