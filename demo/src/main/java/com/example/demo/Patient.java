package com.example.demo;


import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Patient {

    private int id;

    private String firstName;

    private String lastName;

    private String identityNumber;

    private String gender;

    private int age;

    private boolean isChurn;

    private String result;

    private Timestamp registeredDateTime;

    private Timestamp calledTime;

    private Timestamp finishTreatmentTime;

    private Timestamp getMedicineTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public boolean isChurn() {
        return isChurn;
    }

    public void setChurn(boolean churn) {
        isChurn = churn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Timestamp getRegisteredDateTime() {
        return registeredDateTime;
    }

    public void setRegisteredDateTime(Timestamp registeredDateTime) {
        this.registeredDateTime = registeredDateTime;
    }

    public Timestamp getCalledTime() {
        return calledTime;
    }

    public void setCalledTime(Timestamp calledTime) {
        this.calledTime = calledTime;
    }

    public Timestamp getFinishTreatmentTime() {
        return finishTreatmentTime;
    }

    public void setFinishTreatmentTime(Timestamp finishTreatmentTime) {
        this.finishTreatmentTime = finishTreatmentTime;
    }

    public Timestamp getGetMedicineTime() {
        return getMedicineTime;
    }

    public void setGetMedicineTime(Timestamp getMedicineTime) {
        this.getMedicineTime = getMedicineTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
