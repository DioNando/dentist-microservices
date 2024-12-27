package com.dio.appointment_service.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dio.appointment_service.dtos.AppointmentDto;
import com.dio.appointment_service.entities.Appointment;

@Component
public class AppointmentMapper {

    @Autowired
    private ModelMapper modelMapper;

    public AppointmentDto toDto(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentDto.class);
    }

    public Appointment toEntity(AppointmentDto appointmentDto) {
        return modelMapper.map(appointmentDto, Appointment.class);
    }
}
