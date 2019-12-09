package com.example.demo;


import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("patient")
public class ViewController {

    @Autowired
    PatientService patientService;

    @GetMapping(value = "/home")
    public String homePage(){
        return "home";
    }

    @GetMapping(value = "/list-patient")
    public String list(Model model) throws Exception {

        List<Document> patients = patientService.getQueuePatients();
        model.addAttribute("patients",patients);
        return "list-patient";
    }

    @GetMapping(value = "/dismiss-patient/{id}")
    public String dismiss(@PathVariable("id") int id, Model model) throws Exception {
        patientService.dismissPatient(id);
        model.addAttribute("patients", patientService.getQueuePatients());
        return "redirect:/patient/list-patient";
    }

    @GetMapping(value = "/call-patient/{id}")
    public String call(@PathVariable("id") int id, Model model) throws Exception {
        patientService.patientIsCalled(id);
        model.addAttribute("patients", patientService.getQueuePatients());
        return "redirect:/patient/list-patient";
    }

    @GetMapping(value = "/register")
    public String add(Patient patient){
        return "add-patient";
    }

    @PostMapping(value = "/add-patient")
    public String addPatient(@ModelAttribute Patient patient, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "add-patient";
        }
        patientService.registerPatient(patient);

        return "redirect:/patient/list-patient";
    }

    @GetMapping(value = "/patient-treatment/{id}/{doctor_id}")
    public String patientTreatment(@PathVariable("id") int id, @PathVariable("doctor_id") int doctor_id, Model model){

        List<Medicine> medicines = patientService.getMedicinesById(id);

        Patient patient = patientService.getPatient(id);
        model.addAttribute("patient",patient);
        model.addAttribute("medicines", medicines);
        model.addAttribute("doctor_id", doctor_id);
        return "patient-treatment";
    }

    @PostMapping(value = "/add-result/{id}/{doctor_id}")
    public String addResult(@PathVariable("id") int id, @PathVariable("doctor_id") int  doctor_id, Patient patient){
        patientService.addResult(patient.getResult(), id, doctor_id);

        return "redirect:/patient/list-patient-called/"+doctor_id;
    }

    @GetMapping(value = "/medicine/{id}/{doctor_id}")
    public String medicinePate(@PathVariable("id") int id, @PathVariable("doctor_id") int doctor_id, Model model){
        Medicine medicine = new Medicine();
        medicine.setPatientId(id);
        model.addAttribute("doctor_id", doctor_id);
        model.addAttribute("medicine", medicine);
        return "add-medicine";
    }

    @PostMapping(value = "/add-medicine/{id}/{doctor_id}")
    public String addMedicine(@PathVariable("id") int id, @PathVariable("doctor_id") int doctor_id, @ModelAttribute Medicine medicine, Model model){
        medicine.setPatientId(id);
        patientService.addMedicine(medicine);
        return "redirect:/patient/patient-treatment/"+medicine.getPatientId()+"/"+doctor_id;
    }

    @GetMapping(value = "/list-patient-called/{doctor_id}")
    public String listCalledPatient(@PathVariable("doctor_id") int id, Model model) throws Exception {

        List<Document> patients = patientService.getQuueCalledPatient();
        List<Patient> patientsDoneTreatment = patientService.getPatientDoneTreatment(id);
        if(patientsDoneTreatment.size()>=15){
            String contrats = "Congrats doc, your session is over";
            model.addAttribute("congrats", contrats);
        }
        model.addAttribute("patients",patients);
        model.addAttribute("patientsDoneTreatment",patientsDoneTreatment);
        model.addAttribute("doctor_id",id);
        return "list-patient-called";
    }

    @GetMapping(value = "/delete-medicine/{patient_id}/{medicine_id}")
    public String deleteMedicine(@PathVariable("patient_id") int patient_id, @PathVariable("medicine_id") int medicine_id){
        patientService.deleteMedicine(medicine_id);
        return "redirect:/patient/patient-treatment/"+patient_id;
    }

    @GetMapping(value = "/list-patient-medicine")
    public String listPatientMedicine(Model model) throws Exception {
        List<Patient> patients = patientService.getPatientsForMedicine();
        model.addAttribute("patients",patients);
        return "list-patient-medicine";
    }

    @GetMapping(value = "/patient-medicine/{id}")
    public String patientMedicines(@PathVariable int id, Model model){

        List<Medicine> medicines = patientService.getMedicinesById(id);
        model.addAttribute("patient_id", id);
        model.addAttribute("medicines", medicines);
        return "list-medicine";
    }

    @GetMapping(value = "/finish-medicine/{patient_id}")
    public String finishGiveMedicine(@PathVariable int patient_id){

        patientService.finishGetMedicine(patient_id);
        return "redirect:/patient/list-patient-medicine";
    }

    @GetMapping(value = "/login/doctor")
    public String getLoginDoctorPage(Doctor doctor){
        return "doctor-login";
    }

    @PostMapping(value = "/validate/login-doctor")
    public String validateLoginDoctor(@ModelAttribute(value = "doctor") Doctor doctor, Model ra){
        if(patientService.validationDoctorLogin(doctor)){
            return "redirect:/patient/list-patient-called/"+patientService.getIdDoctor(doctor);
        }else{
            String message = "Invalid Login, Username or Password is invalid";
            ra.addAttribute("message", message);
            return "doctor-login";
        }
    }

    @GetMapping(value = "/login/admins")
    public String getLoginAdminsPage(Admins admins){
        return "admins-login";
    }

    @PostMapping(value = "/validate/login-admins")
    public String validateLoginAdmins(@ModelAttribute(value = "admins") Admins admins, Model ra){
        if(patientService.validationAdminsLogin(admins)){
            return "redirect:/patient/list-patient";
        }else{
            String message = "Invalid Login, Username or Password is invalid";
            ra.addAttribute("message", message);
            return "admins-login";
        }
    }

    @GetMapping(value = "/login/apoteker")
    public String getLoginApotekerPage(Apoteker apoteker){
        return "apoteker-login";
    }

    @PostMapping(value = "/validate/login-apoteker")
    public String validateLoginApoteker(@ModelAttribute(value = "apoteker") Apoteker apoteker, Model ra){
        if(patientService.validationApotekerLogin(apoteker)){
            return "redirect:/patient/list-patient-medicine";
        }else{
            String message = "Invalid Login, Username or Password is invalid";
            ra.addAttribute("message", message);
            return "apoteker-login";
        }
    }


}
