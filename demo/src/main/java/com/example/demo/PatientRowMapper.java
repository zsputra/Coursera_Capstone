package com.example.demo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientRowMapper implements RowMapper<Patient> {


    @Override
    public Patient mapRow(ResultSet resultSet, int i) throws SQLException {
        Patient patient = new Patient();

        patient.setId(resultSet.getInt("patient_id"));
        patient.setFirstName(resultSet.getString("firstname"));
        patient.setLastName(resultSet.getString("lastname"));
        patient.setGender(resultSet.getString("gender"));
        patient.setAge(resultSet.getInt("age"));

        return patient;
    }
}
