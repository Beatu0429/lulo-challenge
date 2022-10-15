package com.example.lulopruebatecnica.service.impl;

import com.example.lulopruebatecnica.exceptions.BadRequestException;
import com.example.lulopruebatecnica.exceptions.ResourceNotFoundException;
import com.example.lulopruebatecnica.model.Habitacion;
import com.example.lulopruebatecnica.model.dto.HabitacionDto;
import com.example.lulopruebatecnica.repository.IHabitacionRepository;
import com.example.lulopruebatecnica.service.IHabitacionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class HabitacionServiceImpl implements IHabitacionService {

    @Autowired
    private IHabitacionRepository habitacionRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public HabitacionDto createHabitacion(HabitacionDto habitacionDto) {
        Habitacion habitacion = mapper.map(habitacionDto, Habitacion.class);
        habitacion = habitacionRepository.save(habitacion);
        if (habitacion != null){
            HabitacionDto habitacionSaved = mapper.map(habitacion, HabitacionDto.class);
            return habitacionSaved;
        }
        return null;
    }

    @Override
    public HabitacionDto readHabitacion(Long id) throws ResourceNotFoundException {
        HabitacionDto habitacionDto = null;
        if (habitacionRepository.findById(id).isPresent()){
            Optional<Habitacion> habitacion = habitacionRepository.findById(id);
            habitacionDto = mapper.map(habitacion.get(), HabitacionDto.class);
            return habitacionDto;
        }
        throw new ResourceNotFoundException("No se pudo encontrar la habitación solicitada.");
    }

    @Override
    public HabitacionDto updateHabitacion(HabitacionDto habitacionDto) throws BadRequestException {
        if (habitacionDto.getId() != null){
            Habitacion habitacion = mapper.map(habitacionDto, Habitacion.class);
            habitacion = habitacionRepository.save(habitacion);
            if (habitacion != null){
                HabitacionDto habitacionSaved = mapper.map(habitacion, HabitacionDto.class);
                return habitacionSaved;
            }
        }
        throw new BadRequestException("Para actualizar la habitación debe ingresar el id.");
    }

    @Override
    public void deleteHabitacion(Long id) {
        habitacionRepository.deleteById(id);

    }

    @Override
    public List<HabitacionDto> listHabitaciones(LocalDate fechaIngreso, LocalDate fechaSalida) throws BadRequestException {
        if (fechaIngreso.isAfter(fechaSalida)){
            throw new BadRequestException("La fecha de salida debe ser posterior a la fecha de ingreso.");
        }
        LocalDate fechaActual = LocalDate.now();
        if (fechaIngreso.isBefore(fechaActual)){
            throw new BadRequestException("La fecha de ingreso no debe ser antes de la fecha actual.");
        }
        List<Habitacion> habitacionesDisponibles = habitacionRepository.getHabitacionesDisponibles(fechaIngreso, fechaActual, fechaSalida);
        List<HabitacionDto> listDisponibles = new ArrayList<>();

        for (Habitacion habitacionDisponible : habitacionesDisponibles) {
            listDisponibles.add(mapper.map(habitacionDisponible, HabitacionDto.class));
        }
        return listDisponibles;
    }
}
