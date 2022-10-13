package com.example.lulopruebatecnica.service;

import com.example.lulopruebatecnica.model.dto.HabitacionDto;

import java.time.LocalDate;
import java.util.List;

public interface IHabitacionService {

    public HabitacionDto createHabitacion(HabitacionDto habitacionDto);

    public HabitacionDto readHabitacion(Long id);

    public HabitacionDto updateHabitacion(HabitacionDto habitacionDto);

    public void deleteHabitacion(Long id);

    public List<HabitacionDto> listHabitaciones(LocalDate fechaIngreso, LocalDate fechaSalida);


}
