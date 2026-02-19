package org.example.demo2.dto;

public class Supervises {
    private String Student;
    private int Performance ;

    public Supervises()
    {

    }

    public Supervises(String Student, int Performance)
    {
        this.Student = Student;
        this.Performance = Performance;
    }

    public String GetStudent() {
        return Student;
    }
    public void SetStudent(String Student) {
        this.Student = Student;
    }
    public int GetPerformance() {
        return Performance;
    }
    public void SetPerformance(int Performance) {
        this.Performance = Performance;
    }
}
