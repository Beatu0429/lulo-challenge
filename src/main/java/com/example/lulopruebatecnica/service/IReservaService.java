package com.example.lulopruebatecnica.service;

import com.example.lulopruebatecnica.exceptions.BadRequestException;
import com.example.lulopruebatecnica.exceptions.ForbidenRequestException;
import com.example.lulopruebatecnica.exceptions.ResourceNotFoundException;
import com.example.lulopruebatecnica.model.dto.ReservaDto;

import java.util.List;

public interface IReservaService {

    public ReservaDto createReserva(ReservaDto reservaDto) throws ForbidenRequestException;

    public ReservaDto readReserva(Long id) throws ResourceNotFoundException;

    public ReservaDto updateReserva(ReservaDto reservaDto) throws BadRequestException;

    public void deleteReserva(Long id);

    public List<ReservaDto> listReservas();
}
