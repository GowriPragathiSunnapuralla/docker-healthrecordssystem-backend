package com.healthcare.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.sdp.model.Admin;
import com.healthcare.sdp.model.Doctor;
import com.healthcare.sdp.model.Patient;
import com.healthcare.sdp.model.HealthRecord;
import com.healthcare.sdp.repository.AdminRepository;
import com.healthcare.sdp.repository.DoctorRepository;
import com.healthcare.sdp.repository.PatientRepository;
import com.healthcare.sdp.repository.HealthRecordRepository;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    @Override
    public Admin checkAdminLogin(String username, String password) {
        return adminRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public String addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
        return "Doctor Added Successfully";
    }

    @Override
    public List<Doctor> viewAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Patient> viewAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public List<HealthRecord> viewAllHealthRecords() {
        return healthRecordRepository.findAll();
    }

    @Override
    public long getPatientCount() {
        return patientRepository.count();
    }

    @Override
    public long getDoctorCount() {
        return doctorRepository.count();
    }

    @Override
    public long getHealthRecordCount() {
        return healthRecordRepository.count();
    }

    @Override
    public String deleteDoctor(Long doctorId) {
        try {
            // First, delete associated health records
            List<HealthRecord> records = healthRecordRepository.findByDoctorId(doctorId);
            healthRecordRepository.deleteAll(records);
            
            // Then delete the doctor
            doctorRepository.deleteById(doctorId);
            return "Doctor deleted successfully";
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete doctor: " + e.getMessage());
        }
    }

    @Override
    public String deletePatient(Long patientId) {
        try {
            // First, delete associated health records
            List<HealthRecord> records = healthRecordRepository.findByPatientId(patientId);
            healthRecordRepository.deleteAll(records);
            
            // Then delete the patient
            patientRepository.deleteById(patientId);
            return "Patient deleted successfully";
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete patient: " + e.getMessage());
        }
    }
}