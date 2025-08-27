package com.example.projectnew;

public class Grade
{ private Class classes;
    private int midtermMarks;
    private int finalTermMarks;
    private int marks1;
    private int marks2;
    private int marks3;
    private int marks4;
    private int marks5;
    private int marks6;
    private int marks7;
    private int marks8;

    public Grade() {
        this.classes = new Class();
    }

    public int getMarks1() {
        return marks1;
    }

    public int getMarks2() {
        return marks2;
    }

    public int getMarks3() {
        return marks3;
    }

    public int getMarks4() {
        return marks4;
    }

    public int getMarks5() {
        return marks5;
    }

    public int getMarks6() {
        return marks6;
    }

    public int getMarks7() {
        return marks7;
    }

    public int getMarks8() {
        return marks8;
    }

    public void setMarks1(int marks1) {
        this.marks1 = marks1;
    }

    public void setMarks2(int marks2) {
        this.marks2 = marks2;
    }

    public void setMarks3(int marks3) {
        this.marks3 = marks3;
    }

    public void setMarks4(int marks4) {
        this.marks4 = marks4;
    }

    public void setMarks5(int marks5) {
        this.marks5 = marks5;
    }

    public void setMarks6(int marks6) {
        this.marks6 = marks6;
    }

    public void setMarks7(int marks7) {
        this.marks7 = marks7;
    }

    public void setMarks8(int marks8) {
        this.marks8 = marks8;
    }

    public void setMidtermMarks() {
        if(classes.getClass_name()>=5 || classes.getClass_name()<=8 ) {
            this.midtermMarks = marks1 + marks2 + marks3 + marks4 + marks5 + marks6 + marks7 + marks8;
        } else if(classes.getClass_name()>=1 || classes.getClass_name()<=4){
            this.midtermMarks = marks1 + marks2 + marks3 + marks4 + marks5;
        }
    }
    public void setFinalTermMarks()
    {
        if(classes.getClass_name()>=5 || classes.getClass_name()<=8){
            this.finalTermMarks = marks1 + marks2 + marks3 + marks4 + marks5 + marks6 + marks7 + marks8;
        } else if (classes.getClass_name()>=1 || classes.getClass_name()<=4) {
            this.finalTermMarks = marks1 + marks2 + marks3 + marks4 + marks5;
        }
    }
    public double calculateMidtermPercentage() {
        if(classes.getClass_name()>=5 || classes.getClass_name()<=8){
            return ((double) midtermMarks / 400) * 100;
        }
        else if (classes.getClass_name()>=1 || classes.getClass_name()<=4) {
            return ((double) midtermMarks / 250) * 100;
        }
        return 0;
    }
    public double calculateFinalTermPercentage() {
        if(classes.getClass_name()>=5 || classes.getClass_name()<=8){
            return ((double) finalTermMarks / 800) * 100;
        }
        else if (classes.getClass_name()>=1 || classes.getClass_name()<=4) {
            return ((double) finalTermMarks / 500) * 100;
        }
        return 0;
    }

    public String getMidtermGrade() {
        double percentage = calculateMidtermPercentage();
        if (percentage >= 90) {
            return "A";
        } else if (percentage >= 80) {
            return "B";
        } else if (percentage >= 70) {
            return "C";
        } else if (percentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
    public String getFinalTermGrade() {
        double percentage = calculateFinalTermPercentage();
        if (percentage >= 90) {
            return "A";
        } else if (percentage >= 80) {
            return "B";
        } else if (percentage >= 70) {
            return "C";
        } else if (percentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}
