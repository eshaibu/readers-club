package com.emmanuel.bookclub.models;

import java.time.LocalDateTime;

public abstract class Member implements Comparable<Member>{
    private String idNumber, name, email, address;
    private LocalDateTime registrationDate;

    private Gender gender;

    public Member(String idNumber, String name) {
        try{
            this.registrationDate = LocalDateTime.now();
            Thread.sleep(100);
        } catch(Exception e){

        }
        this.idNumber = idNumber;
        this.name = name;
    }

    public Member(String idNumber, String name, LocalDateTime registrationDate) {
        this.idNumber = idNumber;
        this.name = name;
        this.registrationDate = registrationDate;
    }

    public Member(String idNumber, String name, String email, String address, LocalDateTime registrationDate, Gender gender) {
        this.idNumber = idNumber;
        this.name = name;
        this.email = email;
        this.address = address;
        this.registrationDate = registrationDate;
        this.gender = gender;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean equals(Member member){
        return this.getRegistrationDate().isEqual(member.getRegistrationDate());
    }

    @Override
    public int compareTo(Member member) {
        if(this.getClass() == member.getClass()){
            return this.getRegistrationDate().compareTo(member.getRegistrationDate());
        } else if(member instanceof Student) {
            return -1;
        } else {
            return 1;
        }
    }
}
