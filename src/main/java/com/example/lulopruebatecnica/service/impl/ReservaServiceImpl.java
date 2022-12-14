package com.example.lulopruebatecnica.service.impl;

import com.example.lulopruebatecnica.exceptions.BadRequestException;
import com.example.lulopruebatecnica.exceptions.ForbidenRequestException;
import com.example.lulopruebatecnica.exceptions.ResourceNotFoundException;
import com.example.lulopruebatecnica.model.Habitacion;
import com.example.lulopruebatecnica.model.Reserva;
import com.example.lulopruebatecnica.model.dto.ReservaDto;
import com.example.lulopruebatecnica.repository.IHabitacionRepository;
import com.example.lulopruebatecnica.repository.IReservaRepository;
import com.example.lulopruebatecnica.service.IReservaService;
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
public class ReservaServiceImpl implements IReservaService {

    @Autowired
    private IReservaRepository reservaRepository;

    @Autowired
    private IHabitacionRepository habitacionRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public ReservaDto createReserva(ReservaDto reservaDto) throws ForbidenRequestException {
        LocalDate fechaActual = LocalDate.now();
        List<Habitacion> habitacionesDisponibles = habitacionRepository.getHabitacionesDisponibles(reservaDto.getFechaIngreso(), fechaActual, reservaDto.getFechaSalida());
        boolean habitacionEsDisponible = this.habitacionEstaDisponible(habitacionesDisponibles, reservaDto.getHabitacion().getId());
        if (habitacionEsDisponible){
            Reserva reserva = mapper.map(reservaDto, Reserva.class);
            reserva = reservaRepository.save(reserva);
            ReservaDto reservaSaved = mapper.map(reserva, ReservaDto.class);
            return reservaSaved;
        }
        throw new ForbidenRequestException("La habitación no se encuentra disponible para reserva en las fechas solicitas.");
    }

    @Override
    public ReservaDto readReserva(Long id) throws ResourceNotFoundException {
        ReservaDto reservaDto = null;
        if(reservaRepository.findById(id).isPresent()){
            Optional<Reserva> reserva = reservaRepository.findById(id);
            reservaDto = mapper.map(reserva.get(), ReservaDto.class);
            return reservaDto;
        }
        throw new ResourceNotFoundException("No se pudo encontrar la reserva solicitada.");
    }

    @Override
    public ReservaDto updateReserva(ReservaDto reservaDto) throws BadRequestException {
        if(reservaDto.getId() != null){
            Reserva reserva = mapper.map(reservaDto, Reserva.class);
            reserva = reservaRepository.save(reserva);
            ReservaDto reservaSaved = mapper.map(reserva, ReservaDto.class);
            return reservaSaved;
        }
        throw new BadRequestException("Para actualizar la reserva debe ingresar el id.");
    }

    @Override
    public void deleteReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public List<ReservaDto> listReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        List<ReservaDto> reservaDtos = new ArrayList<>();

        for (Reserva reserva : reservas) {
            reservaDtos.add(mapper.map(reserva, ReservaDto.class));
        }
        return reservaDtos;
    }

    public boolean habitacionEstaDisponible(List<Habitacion> habitaciones, Long id){
        boolean habitacionEstaDisponible = false;
        for (Habitacion habitacion : habitaciones) {
            if (id == habitacion.getId()){
                return habitacionEstaDisponible = true;
            }
        }
        return habitacionEstaDisponible;
    }
}
