package com.dio.appointment_service.controllers;

import java.util.Date;
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

import com.dio.appointment_service.dtos.AppointmentDto;
import com.dio.appointment_service.entities.Appointment;
import com.dio.appointment_service.services.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<Appointment> showListAppointments() {
        return appointmentService.getAllAppointments();
    }

    @PostMapping("/add")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
        Appointment createdAppointment = appointmentService.addAppointment(appointment);
        return ResponseEntity.ok(createdAppointment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> showDetailAppointment(@PathVariable("id") Long id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        return appointment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<AppointmentDto> showAppointmentDetails(@PathVariable Long id) {
        try {
            AppointmentDto appointmentDetails = appointmentService.getAppointmentDetails(id);
            return ResponseEntity.ok(appointmentDetails);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null); // Ou ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public List<Appointment> getMethodName(@RequestParam("date") Date date) {
        return appointmentService.searchAppointmentsByDate(date);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        if (appointment.isPresent()) {
            appointmentService.deleteAppointment(appointment.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
