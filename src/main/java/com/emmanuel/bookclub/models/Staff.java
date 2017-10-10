package com.emmanuel.bookclub.models;

import java.time.LocalDateTime;

public class Staff extends Member {
    private double salary;
    private int workHour;

    public Staff(String idNumber, String name, LocalDateTime registrationDate, double salary, int workHour) {
        super(idNumber, name, registrationDate);
        this.salary = salary;
        this.workHour = workHour;
    }

    public Staff(String idNumber, String name, String email, String address, LocalDateTime registrationDate, Gender gender, double salary, int workHour) {
        super(idNumber, name, email, address, registrationDate, gender);
        this.salary = salary;
        this.workHour = workHour;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getWorkHour() {
        return workHour;
    }

    public void setWorkHour(int workHour) {
        this.workHour = workHour;
    }
}
