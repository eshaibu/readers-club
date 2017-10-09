package com.emmanuel.bookclub.models;

import java.time.LocalDateTime;

public class Student extends Member{

    private String level, subjectOfInterest;

    public Student(String idNumber, String name, String level) {
        super(idNumber, name);
        this.level = level;
    }

    public Student(String idNumber, String name, LocalDateTime registrationDate, String level) {
        super(idNumber, name, registrationDate);
        this.level = level;
    }

    public Student(String idNumber, String name, LocalDateTime registrationDate, String level, String subjectOfInterest) {
        super(idNumber, name, registrationDate);
        this.level = level;
        this.subjectOfInterest = subjectOfInterest;
    }

    public Student(String idNumber, String name, String email, String address, LocalDateTime registrationDate, Gender gender, String level, String subjectOfInterest) {
        super(idNumber, name, email, address, registrationDate, gender);
        this.level = level;
        this.subjectOfInterest = subjectOfInterest;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSubjectOfInterest() {
        return subjectOfInterest;
    }

    public void setSubjectOfInterest(String subjectOfInterest) {
        this.subjectOfInterest = subjectOfInterest;
    }
}
