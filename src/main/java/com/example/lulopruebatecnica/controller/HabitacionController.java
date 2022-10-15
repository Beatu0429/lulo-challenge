package com.example.lulopruebatecnica.controller;

import com.example.lulopruebatecnica.exceptions.BadRequestException;
import com.example.lulopruebatecnica.exceptions.ResourceNotFoundException;
import com.example.lulopruebatecnica.model.dto.HabitacionDto;
import com.example.lulopruebatecnica.model.dto.ReservaDto;
import com.example.lulopruebatecnica.service.IHabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/habitaciones")
public class HabitacionController {

    @Autowired
    private IHabitacionService habitacionService;

    @PostMapping
    public ResponseEntity<HabitacionDto> createHabitacion(@RequestBody HabitacionDto habitacionDto){
        return new ResponseEntity(habitacionService.createHabitacion(habitacionDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitacionDto> readHabitacion(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity(habitacionService.readHabitacion(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HabitacionDto> updateHabitacion(@RequestBody HabitacionDto habitacionDto) throws BadRequestException {
        return new ResponseEntity(habitacionService.updateHabitacion(habitacionDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteHabitacion(@PathVariable Long id){
        habitacionService.deleteHabitacion(id);
    }

    @PostMapping("/disponibles")
    public ResponseEntity<List<HabitacionDto>> listHabitacionesDisponibles(@RequestBody ReservaDto reservaDto) throws BadRequestException {
        return new ResponseEntity(habitacionService.listHabitaciones(reservaDto.getFechaIngreso(), reservaDto.getFechaSalida()), HttpStatus.OK);
    }
}

