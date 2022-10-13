package com.example.lulopruebatecnica.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HabitacionDto {

    private Long id;
    private String nombre;
    private Integer personasMaximas;
    private Double precio;
    private List<ReservaDto> reservas;

}
