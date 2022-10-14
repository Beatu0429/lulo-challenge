package com.example.lulopruebatecnica.controller;

import com.example.lulopruebatecnica.model.dto.ReservaDto;
import com.example.lulopruebatecnica.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reservas")
public class ReservaController {

    @Autowired
    private IReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaDto> createReserva(@RequestBody ReservaDto reservaDto){
        return new ResponseEntity(reservaService.createReserva(reservaDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDto> readReserva(@PathVariable Long id){
        return new ResponseEntity(reservaService.readReserva(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ReservaDto> updateReserva(@RequestBody ReservaDto reservaDto){
        return new ResponseEntity(reservaService.updateReserva(reservaDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteReserva(@PathVariable Long id){
        reservaService.deleteReserva(id);
    }

    @GetMapping
    public ResponseEntity<List<ReservaDto>> listReservas(){
        return new ResponseEntity(reservaService.listReservas(), HttpStatus.OK);
    }
}
