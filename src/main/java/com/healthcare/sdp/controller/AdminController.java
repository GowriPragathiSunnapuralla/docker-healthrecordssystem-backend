package com.healthcare.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.healthcare.sdp.model.Admin;
import com.healthcare.sdp.model.Doctor;
import com.healthcare.sdp.model.HealthRecord;
import com.healthcare.sdp.model.Patient;
import com.healthcare.sdp.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Admin Login
    @PostMapping("/checkadminlogin")
    public ResponseEntity<?> checkAdminLogin(@RequestBody Admin admin) {
        try {
            Admin a = adminService.checkAdminLogin(admin.getUsername(), admin.getPassword());
            if (a != null) {
                return ResponseEntity.ok(a);
            } else {
                return ResponseEntity.status(401).body("Invalid Username or Password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Login failed: " + e.getMessage());
        }
    }

    // Add Doctor
    @PostMapping("/adddoctor")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor) {
        try {
            String result = adminService.addDoctor(doctor);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to Add Doctor: " + e.getMessage());
        }
    }

    // View All Doctors
    @GetMapping("/viewalldoctors")
    public ResponseEntity<List<Doctor>> viewAllDoctors() {
        try {
            List<Doctor> doctors = adminService.viewAllDoctors();
            return ResponseEntity.ok(doctors);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // View All Patients
    @GetMapping("/viewallpatients")
    public ResponseEntity<List<Patient>> viewAllPatients() {
        try {
            List<Patient> patients = adminService.viewAllPatients();
            return ResponseEntity.ok(patients);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // View All Medical Records
    @GetMapping("/viewallmedicalrecords")
    public ResponseEntity<List<HealthRecord>> viewAllMedicalRecords() {
        try {
            List<HealthRecord> records = adminService.viewAllHealthRecords();
            return ResponseEntity.ok(records);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Get Patient Count
    @GetMapping("/patientcount")
    public ResponseEntity<Long> getPatientCount() {
        try {
            long count = adminService.getPatientCount();
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(0L);
        }
    }

    // Get Doctor Count
    @GetMapping("/doctorcount")
    public ResponseEntity<Long> getDoctorCount() {
        try {
            long count = adminService.getDoctorCount();
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(0L);
        }
    }

    // Get Record Count
    @GetMapping("/recordcount")
    public ResponseEntity<Long> getRecordCount() {
        try {
            long count = adminService.getHealthRecordCount();
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(0L);
        }
    }

    // Delete Doctor
    @DeleteMapping("/deletedoctor")
    public ResponseEntity<String> deleteDoctor(@RequestParam Long doctorid) {
        try {
            String result = adminService.deleteDoctor(doctorid);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to delete doctor: " + e.getMessage());
        }
    }

    // Delete Patient
    @DeleteMapping("/deletepatient")
    public ResponseEntity<String> deletePatient(@RequestParam Long patientid) {
        try {
            String result = adminService.deletePatient(patientid);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to delete patient: " + e.getMessage());
        }
    }
}