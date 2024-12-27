package com.dio.appointment_service.dtos;

import com.dio.appointment_service.entities.Appointment;
import com.dio.appointment_service.entities.Patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AppointmentDto {
    private Appointment appointment;
    private Patient patient;
}
