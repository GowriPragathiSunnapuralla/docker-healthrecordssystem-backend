package com.healthcare.sdp.service;

import com.healthcare.sdp.model.Appointment;
import java.util.List;

public interface AppointmentService {
    Appointment addAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    List<Appointment> getAppointmentsByPatientId(Long patientId);
    List<Appointment> getAppointmentsByDoctorId(Long doctorId);
    void deleteAppointment(Long id);
}