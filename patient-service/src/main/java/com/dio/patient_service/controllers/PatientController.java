package com.dio.patient_service.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dio.patient_service.entities.Patient;
import com.dio.patient_service.services.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> showListPatients() {
        return patientService.getAllPatients();
    }

    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient createdPatient = patientService.addPatient(patient);
        return ResponseEntity.ok(createdPatient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> showDetailPatient(@PathVariable("id") Long id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        return patient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Patient> getMethodName(@RequestParam("keyword") String keyword) {
        return patientService.searchPatientsByFullName(keyword);
    }
    

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        if (patient.isPresent()) {
            patientService.deletePatient(patient.get());
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
