package com.example.demo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicineRowMapper implements RowMapper<Medicine> {


    @Override
    public Medicine mapRow(ResultSet resultSet, int i) throws SQLException {
        Medicine medicine = new Medicine();

        medicine.setId(resultSet.getInt("id"));
        medicine.setPatientId(resultSet.getInt("patient_id"));
        medicine.setMedicine(resultSet.getString("medicine"));
        medicine.setTotal(resultSet.getInt("total"));
        return medicine;
    }
}
