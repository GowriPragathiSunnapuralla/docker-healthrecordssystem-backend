package com.healthcare.sdp.service;

import com.healthcare.sdp.model.Patient;
import java.util.List;

public interface PatientService {
    Patient addPatient(Patient patient);
    Patient updatePatient(Patient patient);
    void deletePatient(Long id);
    Patient getPatientById(Long id);
    List<Patient> getAllPatients();
    List<Patient> getPatientsByDoctorId(Long doctorId);
    Long getPatientCountByDoctorId(Long doctorId);
    Patient checkPatientLogin(String username, String password);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}