package com.dio.patient_service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dio.patient_service.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByFullNameContaining(String fullName);
}
