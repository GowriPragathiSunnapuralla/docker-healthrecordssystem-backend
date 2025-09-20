package com.healthcare.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.healthcare.sdp.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByUsernameAndPassword(String username, String password);
}