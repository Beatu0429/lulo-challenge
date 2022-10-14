package com.example.lulopruebatecnica.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FechasDisponiblesDto {

    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
}
