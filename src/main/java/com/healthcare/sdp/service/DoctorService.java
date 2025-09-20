package com.healthcare.sdp.service;

import com.healthcare.sdp.model.Doctor;
import java.util.List;

public interface DoctorService {
    Doctor checkDoctorLogin(String username, String password);
    Doctor addDoctor(Doctor doctor);
    Doctor updateDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long id);
    void deleteDoctor(Long id);
}