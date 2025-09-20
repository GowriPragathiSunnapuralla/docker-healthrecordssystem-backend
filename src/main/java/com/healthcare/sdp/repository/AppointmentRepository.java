package com.healthcare.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.healthcare.sdp.model.Appointment;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorId(Long doctorId);
}