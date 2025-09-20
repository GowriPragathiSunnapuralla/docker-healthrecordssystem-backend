package com.healthcare.sdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.healthcare.sdp.model.Doctor;
import com.healthcare.sdp.model.Patient;
import com.healthcare.sdp.model.HealthRecord;
import com.healthcare.sdp.service.DoctorService;
import com.healthcare.sdp.service.PatientService;
import com.healthcare.sdp.service.HealthRecordService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private HealthRecordService healthRecordService;

    // Authentication
    @PostMapping("/login")
    public ResponseEntity<?> checkDoctorLogin(@RequestBody Doctor doctor) {
        try {
            Doctor authenticatedDoctor = doctorService.checkDoctorLogin(doctor.getUsername(), doctor.getPassword());
            if (authenticatedDoctor != null) {
                return ResponseEntity.ok(authenticatedDoctor);
            } else {
                return ResponseEntity.status(401).body("Invalid Username or Password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Login failed: " + e.getMessage());
        }
    }

    // View Patients by Doctor
    @GetMapping("/viewpatientsbydoctor/{doctorId}")
    public ResponseEntity<?> getPatientsByDoctor(@PathVariable Long doctorId) {
        try {
            List<Patient> patients = patientService.getPatientsByDoctorId(doctorId);
            return ResponseEntity.ok(patients);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving patients: " + e.getMessage());
        }
    }

    // View Records by Doctor
    @GetMapping("/viewrecordsbydoctor/{doctorId}")
    public ResponseEntity<?> getHealthRecordsByDoctor(@PathVariable Long doctorId) {
        try {
            List<HealthRecord> records = healthRecordService.getHealthRecordsByDoctorId(doctorId);
            return ResponseEntity.ok(records);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving health records: " + e.getMessage());
        }
    }

    // Add Health Record
    @PostMapping("/addhealthrecord")
    public ResponseEntity<?> addHealthRecord(@RequestBody HealthRecord healthRecord) {
        try {
            HealthRecord savedRecord = healthRecordService.addHealthRecord(healthRecord);
            return ResponseEntity.ok("Health Record Added Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to add health record: " + e.getMessage());
        }
    }

    // Doctor Profile
    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable Long id) {
        try {
            Doctor doctor = doctorService.getDoctorById(id);
            if (doctor != null) {
                return ResponseEntity.ok(doctor);
            } else {
                return ResponseEntity.status(404).body("Doctor not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving doctor: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDoctor(@RequestBody Doctor doctor) {
        try {
            Doctor updatedDoctor = doctorService.updateDoctor(doctor);
            if (updatedDoctor != null) {
                return ResponseEntity.ok(updatedDoctor);
            } else {
                return ResponseEntity.status(404).body("Doctor not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to update doctor: " + e.getMessage());
        }
    }

    // Dashboard Statistics
    @GetMapping("/dashboard/{doctorId}")
    public ResponseEntity<?> getDoctorDashboardStats(@PathVariable Long doctorId) {
        try {
            Map<String, Object> stats = Map.of(
                "totalPatients", patientService.getPatientCountByDoctorId(doctorId),
                "totalRecords", healthRecordService.getHealthRecordCountByDoctorId(doctorId)
            );
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving dashboard stats: " + e.getMessage());
        }
    }
}