package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class DataService extends JdbcDaoSupport{


    @Autowired
    JdbcTemplate jdbcTemplate;

//
    @Autowired
    DataSource dataSource;



    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public void insertPatient(Patient patient){


        String sql = "insert into Patient(firstname,lastname,gender,age,identityNumber, ischurn, registeredDateTime)" +
                "values(?,?,?,?,?,false,?)";

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        jdbcTemplate.update(sql, patient.getFirstName(), patient.getLastName(), patient.getGender() ,patient.getAge(), patient.getIdentityNumber(), timestamp);


    }

    public List<Patient> getAllPatient() throws Exception {
        String sql = "SELECT patient_id, firstname, lastname, gender, age from patient where ischurn = false AND calledtime is null order by registeredDateTime";


        List<Patient> patients =  jdbcTemplate.query(sql, new PatientRowMapper());
//        if(patients.isEmpty()){
//            throw new Exception("not found");
//        }
        return patients;
    }

    public void dismissPatient(int patient_id){
        String sql = "update Patient set isChurn = true where patient_id = ?";
        jdbcTemplate.update(sql, new Object[] {patient_id});
    }

    public void patientCall(int patient_id){
        String sql = "update Patient set calledtime = ? where patient_id = ?";
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Object[] params = {time, patient_id};
        jdbcTemplate.update(sql, params);
    }

    public List<Patient> getListCalledPatient() throws Exception {

        String sql = "Select patient_id, firstname, lastname, gender, age from patient where calledtime is not null and finishTreatmentTime is null order by calledtime";

        List<Patient> patients = jdbcTemplate.query(sql, new PatientRowMapper());
        return patients;
    }

    public Patient getPatientById(int patient_id){

        String sql= "select patient_id, firstname, lastname, gender, age from patient where patient_id = ?";

        Patient patient = jdbcTemplate.queryForObject(sql, new Object[]{patient_id}, new PatientRowMapper());

        return patient;
    }

    public void addResult(String result, int patient_id, int doctor_id){

        String sql = "update Patient set result = ?, finishTreatmentTime = ?, doctor_id = ? where patient_id = ?";

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        jdbcTemplate.update(sql, new Object[]{result, timestamp, doctor_id, patient_id});
    }

    public List<Medicine> getMedicineByPatientId(int patient_id){

        String sql = "select * from medicine where patient_Id = ?";

        List<Medicine> medicines = jdbcTemplate.query(sql, new Object[]{patient_id}, new MedicineRowMapper());

        return medicines;
    }

    public void addMedicine(Medicine medicine){
        String sql = "insert into medicine(patient_id, medicine, total) values(?,?,?)";

        Object[] params = {medicine.getPatientId(), medicine.getMedicine(), medicine.getTotal()};

        jdbcTemplate.update(sql, params);
    }

    public void deleteMedicine(int id){
        String sql = "delete from medicine where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Patient> getPatientforMedicine(){
        String sql = "select patient_id, firstname, lastname, gender, age from patient where finishTreatmentTime is not null and getMedicineTime is null order  by finishTreatmentTime";
        return jdbcTemplate.query(sql, new PatientRowMapper());
    }


    public void patientGetMedicine(int patient_id){
        String sql = "update patient set getMedicineTime = ? where patient_id = ?";
        Timestamp timestamp = new Timestamp((System.currentTimeMillis()));
        jdbcTemplate.update(sql, new Object[]{timestamp, patient_id});
    }


    public List<Patient> getPatientDoneTreatment(int doctor_id){
        String sql = "select patient_id, firstname, lastname, gender, age from patient where finishTreatmentTime is not null and getMedicineTime is null and doctor_id = ? order  by finishTreatmentTime";

        return jdbcTemplate.query(sql, new Object[]{doctor_id}, new PatientRowMapper());
    }

//    Docter Validation

    public boolean validationDoctorLogin(Doctor doctor){
        String sql = "SELECT EXISTS (SELECT * from doctor WHERE username = ? and password = ?)";

        boolean exist = jdbcTemplate.queryForObject(sql, new Object[] { doctor.getUsername(), doctor.getPassword()},
                Boolean.class);
        return exist;

        }

    public int getDoctorIdByUsernameAndPassword(Doctor doctor){
        String sql = "select id from doctor where username = ? and password = ?";
        Integer id =  jdbcTemplate.queryForObject(sql, new Object[]{doctor.getUsername(), doctor.getPassword()}, Integer.class);
        return id;
    }

//    Admins Validation
    public boolean validationAdminsLogin(Admins admin){
        String sql = "SELECT EXISTS (SELECT * from admins WHERE username = ? and password = ?)";

        boolean exist = jdbcTemplate.queryForObject(sql, new Object[] { admin.getUsername(), admin.getPassword()},
                Boolean.class);
        return exist;

    }

    public int getAdminsIdByUsernameAndPassword(Admins admin){
        String sql = "select id from admins where username = ? and password = ?";
        Integer id =  jdbcTemplate.queryForObject(sql, new Object[]{admin.getUsername(), admin.getPassword()}, Integer.class);
        return id;
    }


//    Apoteker Validation
    public boolean validationApotekerLogin(Apoteker apoteker){
        String sql = "SELECT EXISTS (SELECT * from apoteker WHERE username = ? and password = ?)";

        boolean exist = jdbcTemplate.queryForObject(sql, new Object[] { apoteker.getUsername(), apoteker.getPassword()},
                Boolean.class);
        return exist;

    }

    public int getApotekerIdByUsernameAndPassword(Apoteker apoteker){
        String sql = "select id from apoteker where username = ? and password = ?";
        Integer id =  jdbcTemplate.queryForObject(sql, new Object[]{apoteker.getUsername(), apoteker.getPassword()}, Integer.class);
        return id;
    }







}
