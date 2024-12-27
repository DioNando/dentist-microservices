package com.dio.patient_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dio.patient_service.entities.Patient;
import com.dio.patient_service.repositories.PatientRepository;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(Patient patient) {
        patientRepository.delete(patient);
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> searchPatientsByFullName(String fullName) {
        return patientRepository.findByFullNameContaining(fullName);
    } 
}
