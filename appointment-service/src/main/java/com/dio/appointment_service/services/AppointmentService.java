package com.dio.appointment_service.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dio.appointment_service.config.ServiceConfig;
import com.dio.appointment_service.dtos.AppointmentDto;
import com.dio.appointment_service.entities.Appointment;
import com.dio.appointment_service.entities.Patient;
import com.dio.appointment_service.repositories.AppointmentRepository;

@Service
public class AppointmentService {
    @Autowired
    private final AppointmentRepository appointmentRepository;
    private final RestTemplate restTemplate;
    private final ServiceConfig serviceConfig;
    private final ModelMapper modelMapper;

    public AppointmentService(AppointmentRepository appointmentRepository, RestTemplate restTemplate, ServiceConfig serviceConfig, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.restTemplate = restTemplate;
        this.serviceConfig = serviceConfig;
        this.modelMapper = modelMapper;
    }

    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Appointment appointment) {
        appointmentRepository.delete(appointment);
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> searchAppointmentsByDate(Date date) {
        return appointmentRepository.findByDate(date);
    }

    public AppointmentDto getAppointmentDetails(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        String patientUrl = serviceConfig.getPatientUrl() + "/" + appointment.getIdPatient();
        Patient patient = restTemplate.getForObject(patientUrl, Patient.class);
        
        AppointmentDto appointmentDto = modelMapper.map(appointment, AppointmentDto.class);
        appointmentDto.setPatient(patient);
        return appointmentDto;
    }
}