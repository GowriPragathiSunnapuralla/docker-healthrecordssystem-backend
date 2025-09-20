package com.healthcare.sdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthcare.sdp.model.HealthRecord;
import com.healthcare.sdp.repository.HealthRecordRepository;
import com.healthcare.sdp.service.HealthRecordService;
import java.util.List;

@Service
public class HealthRecordServiceImpl implements HealthRecordService {

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    @Override
    public HealthRecord addHealthRecord(HealthRecord healthRecord) {
        return healthRecordRepository.save(healthRecord);
    }

    @Override
    public List<HealthRecord> getHealthRecordsByDoctorId(Long doctorId) {
        return healthRecordRepository.findByDoctorId(doctorId);
    }

    @Override
    public HealthRecord getHealthRecordById(Long recordId) {
        return healthRecordRepository.findById(recordId).orElse(null);
    }

    @Override
    public Long getHealthRecordCountByDoctorId(Long doctorId) {
        return healthRecordRepository.countByDoctorId(doctorId);
    }

    @Override
    public List<HealthRecord> getHealthRecordsByPatientId(Long patientId) {
        return healthRecordRepository.findByPatientId(patientId);
    }
}