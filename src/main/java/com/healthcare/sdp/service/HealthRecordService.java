package com.healthcare.sdp.service;

import com.healthcare.sdp.model.HealthRecord;
import java.util.List;

public interface HealthRecordService {
    HealthRecord addHealthRecord(HealthRecord healthRecord);
    List<HealthRecord> getHealthRecordsByDoctorId(Long doctorId);
    HealthRecord getHealthRecordById(Long recordId);
    Long getHealthRecordCountByDoctorId(Long doctorId);
    List<HealthRecord> getHealthRecordsByPatientId(Long patientId);
}