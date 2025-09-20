package com.healthcare.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.healthcare.sdp.model.HealthRecord;
import java.util.List;

public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {
    List<HealthRecord> findByDoctorId(Long doctorId);
    List<HealthRecord> findByPatientId(Long patientId);
    
    @Query("SELECT COUNT(h) FROM HealthRecord h WHERE h.doctor.id = :doctorId")
    Long countByDoctorId(@Param("doctorId") Long doctorId);
}