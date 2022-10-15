package com.example.lulopruebatecnica.model.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDto{

    private Long id;
    private String nombreHuesped;
    private String documentoIdentidad;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private HabitacionDto habitacion;

}
