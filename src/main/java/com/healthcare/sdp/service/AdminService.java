package com.healthcare.sdp.service;

import com.healthcare.sdp.model.Admin;
import com.healthcare.sdp.model.Doctor;
import com.healthcare.sdp.model.Patient;
import com.healthcare.sdp.model.HealthRecord;

import java.util.List;

public interface AdminService {
    Admin checkAdminLogin(String username, String password);

    String addDoctor(Doctor doctor);

    List<Doctor> viewAllDoctors();

    List<Patient> viewAllPatients();

    List<HealthRecord> viewAllHealthRecords();
    
    // Count methods
    long getPatientCount();
    
    long getDoctorCount();
    
    long getHealthRecordCount();
    
    // Delete methods
    String deleteDoctor(Long doctorId);
    
    String deletePatient(Long patientId);
}