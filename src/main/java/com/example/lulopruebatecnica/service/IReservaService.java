package com.example.lulopruebatecnica.service;

import com.example.lulopruebatecnica.model.dto.ReservaDto;

import java.util.List;

public interface IReservaService {

    public ReservaDto createReserva(ReservaDto reservaDto);

    public ReservaDto readReserva(Long id);

    public ReservaDto updateReserva(ReservaDto reservaDto);

    public void deleteReserva(Long id);

    public List<ReservaDto> listReservas();
}
