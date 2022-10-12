package com.example.lulopruebatecnica.model.dto;

import java.util.List;


public class HabitacionDto {

    private Long id;
    private String nombre;
    private Integer personasMaximas;
    private Double precio;
    private List<ReservaDto> reservas;

}
