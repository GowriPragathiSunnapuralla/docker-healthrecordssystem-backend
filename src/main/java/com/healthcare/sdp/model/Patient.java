package com.healthcare.sdp.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "patient_table")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String gender;

    @Column
    private String dob;

    @Column(length = 50, unique = true)
    private String email;

    @Column(length = 50, unique = true)
    private String username;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(length = 15)
    private String mobileno;

    @Column(length = 100)
    private String location;

    private int age;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<HealthRecord> healthRecords;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getMobileno() { return mobileno; }
    public void setMobileno(String mobileno) { this.mobileno = mobileno; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public List<HealthRecord> getHealthRecords() { return healthRecords; }
    public void setHealthRecords(List<HealthRecord> healthRecords) { this.healthRecords = healthRecords; }
}