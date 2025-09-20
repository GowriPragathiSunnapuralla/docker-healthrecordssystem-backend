package com.healthcare.sdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthcare.sdp.model.Doctor;
import com.healthcare.sdp.repository.DoctorRepository;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor checkDoctorLogin(String username, String password) {
        return doctorRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        Doctor existingDoctor = doctorRepository.findById(doctor.getId())
            .orElseThrow(() -> new RuntimeException("Doctor not found"));
        
        // Update only non-null fields
        if (doctor.getName() != null) existingDoctor.setName(doctor.getName());
        if (doctor.getEmail() != null) existingDoctor.setEmail(doctor.getEmail());
        if (doctor.getUsername() != null) existingDoctor.setUsername(doctor.getUsername());
        if (doctor.getPassword() != null) existingDoctor.setPassword(doctor.getPassword());
        if (doctor.getSpecialization() != null) existingDoctor.setSpecialization(doctor.getSpecialization());
        
        return doctorRepository.save(existingDoctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}