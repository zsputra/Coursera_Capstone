package com.example.demo;


import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    DataService dataService;

    public void registerPatient(Patient patient){
        dataService.insertPatient(patient);
    }

    public List<Document> getQueuePatients() throws Exception {
        List<Document> queuePatients = new ArrayList<>();
        dataService.getAllPatient().forEach(patient -> {
            Document p = new Document();
            p.append("patient_id", patient.getId());
            p.append("firstname", patient.getFirstName());
            p.append("lastname", patient.getLastName());
            p.append("gender", patient.getGender());
            queuePatients.add(p);
        });
        return queuePatients;
    }

    public void dismissPatient(int patient_id){
        dataService.dismissPatient(patient_id);
    }

    public void patientIsCalled(int patient_id){
        dataService.patientCall(patient_id);
    }

    public List<Document> getQuueCalledPatient() throws Exception {
        List<Document> queuePatientsCalled = new ArrayList<>();
        dataService.getListCalledPatient().forEach(patient -> {
            Document p = new Document();
            p.append("patient_id", patient.getId());
            p.append("firstname", patient.getFirstName());
            p.append("lastname", patient.getLastName());
            p.append("gender", patient.getGender());
            p.append("age", patient.getAge());
            queuePatientsCalled.add(p);
        });
        return queuePatientsCalled;
    }

    public Patient getPatient(int patient_id){
        Patient patient = dataService.getPatientById(patient_id);

        Document p = new Document();
        p.append("patient_id", patient.getId());
        p.append("firstname", patient.getFirstName());
        p.append("lastname", patient.getLastName());
        p.append("gender", patient.getGender());
        p.append("age", patient.getAge());

        return patient;
    }

    public void addResult(String result, int patient_id, int doctor_id){
        dataService.addResult(result, patient_id, doctor_id);
    }

    public List<Medicine> getMedicinesById(int patient_id){
        return dataService.getMedicineByPatientId(patient_id);
    }

    public void addMedicine(Medicine medicine){
        dataService.addMedicine(medicine);
    }

    public void deleteMedicine(int id){
        dataService.deleteMedicine(id);
    }

    public List<Patient> getPatientsForMedicine(){
        return dataService.getPatientforMedicine();
    }

    public void finishGetMedicine(int patient_id){
        dataService.patientGetMedicine(patient_id);
    }

    public List<Patient> getPatientDoneTreatment(int doctor_id){
        return dataService.getPatientDoneTreatment(doctor_id);
    }


//Doctor Validation
    public Boolean validationDoctorLogin(Doctor doctor){

        return dataService.validationDoctorLogin(doctor);

    }

    public int getIdDoctor(Doctor doctor){
        return dataService.getDoctorIdByUsernameAndPassword(doctor);
    }

    //Admins Validation
    public Boolean validationAdminsLogin(Admins admin){

        return dataService.validationAdminsLogin(admin);

    }

    public int getIdAdmins(Admins admin){
        return dataService.getAdminsIdByUsernameAndPassword(admin);
    }

    //Apoteker Validation
    public Boolean validationApotekerLogin(Apoteker apoteker){

        return dataService.validationApotekerLogin(apoteker);

    }

    public int getIdApoteker(Apoteker apoteker){
        return dataService.getApotekerIdByUsernameAndPassword(apoteker);
    }
}
