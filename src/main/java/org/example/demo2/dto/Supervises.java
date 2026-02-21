package org.example.demo2.dto;

public class Supervises {
    private int SupervisesId ;
    private int AdvisorId ;
    private int StudyId ;
    private String Student;
    private int Performance ;


    public Supervises() {}

    public Supervises(int SupervisesId, int AdvisorId, int StudyId, String Student, int Performance)
    {
        this.SupervisesId = SupervisesId;
        this.AdvisorId = AdvisorId;
        this.StudyId = StudyId;
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
    public int GetSupervisesId() {
        return SupervisesId;
    }
    public void SetSupervisesId(int SupervisesId) {
        this.SupervisesId = SupervisesId;
    }
    public int GetAdvisorId() {
        return AdvisorId;
    }
    public void SetAdvisorId(int AdvisorId) {
        this.AdvisorId = AdvisorId;
    }
    public int GetStudyId() {
        return StudyId;
    }
    public void SetStudyId(int StudyId) {
        this.StudyId = StudyId;
    }
}
