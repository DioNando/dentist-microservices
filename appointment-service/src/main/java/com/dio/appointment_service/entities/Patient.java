package com.dio.appointment_service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {
    private Long id;
    private String fullName;
    private String birthDate;
    private String gender;
}
