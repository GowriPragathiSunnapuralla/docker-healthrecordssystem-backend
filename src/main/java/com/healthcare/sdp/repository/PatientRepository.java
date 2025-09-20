package com.healthcare.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.healthcare.sdp.model.Patient;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    
    @Query("SELECT p FROM Patient p JOIN p.healthRecords h WHERE h.doctor.id = :doctorId")
    List<Patient> findByDoctorId(@Param("doctorId") Long doctorId);
    
    @Query("SELECT COUNT(DISTINCT p) FROM Patient p JOIN p.healthRecords h WHERE h.doctor.id = :doctorId")
    Long countByDoctorId(@Param("doctorId") Long doctorId);
}