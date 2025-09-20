package com.healthcare.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.healthcare.sdp.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByUsernameAndPassword(String username, String password);
}