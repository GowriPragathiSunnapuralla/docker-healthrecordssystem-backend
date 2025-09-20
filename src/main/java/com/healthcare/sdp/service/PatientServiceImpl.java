package com.healthcare.sdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthcare.sdp.model.Patient;
import com.healthcare.sdp.repository.PatientRepository;
import com.healthcare.sdp.service.PatientService;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient checkPatientLogin(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new RuntimeException("Username is required");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new RuntimeException("Password is required");
        }
        return patientRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public Patient addPatient(Patient patient) {
        // Validate required fields
        if (patient.getEmail() == null || patient.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email is required");
        }
        if (patient.getPassword() == null || patient.getPassword().trim().isEmpty()) {
            throw new RuntimeException("Password is required");
        }
        if (patient.getUsername() == null || patient.getUsername().trim().isEmpty()) {
            throw new RuntimeException("Username is required");
        }
        if (patient.getName() == null || patient.getName().trim().isEmpty()) {
            throw new RuntimeException("Name is required");
        }

        // Check for unique constraints
        if (existsByEmail(patient.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (existsByUsername(patient.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        Patient existingPatient = patientRepository.findById(patient.getId())
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        // Check if email is being changed and if it already exists
        if (!existingPatient.getEmail().equals(patient.getEmail()) && 
            existsByEmail(patient.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        // Check if username is being changed and if it already exists
        if (!existingPatient.getUsername().equals(patient.getUsername()) && 
            existsByUsername(patient.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found");
        }
        patientRepository.deleteById(id);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> getPatientsByDoctorId(Long doctorId) {
        if (doctorId == null) {
            throw new RuntimeException("Doctor ID is required");
        }
        return patientRepository.findByDoctorId(doctorId);
    }

    @Override
    public Long getPatientCountByDoctorId(Long doctorId) {
        if (doctorId == null) {
            throw new RuntimeException("Doctor ID is required");
        }
        return patientRepository.countByDoctorId(doctorId);
    }

    @Override
    public boolean existsByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new RuntimeException("Username is required");
        }
        return patientRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new RuntimeException("Email is required");
        }
        return patientRepository.existsByEmail(email);
    }
}