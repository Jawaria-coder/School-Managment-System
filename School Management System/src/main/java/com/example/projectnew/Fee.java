package com.example.projectnew;
public class Fee {
    private double baseFee;
    private double securityFee;
    private double admissionFee;

    public Fee(double baseFee, double securityFee, double admissionFee) {
        this.baseFee = 8000.0;
        this.securityFee = 2000.0;
        this.admissionFee = 3000.0;
    }

    public double StudentFee(Student student) {
        if (student.isNewStudent()) {
            return baseFee + securityFee + admissionFee;
        } else {
            return baseFee + securityFee;
        }
    }
}

