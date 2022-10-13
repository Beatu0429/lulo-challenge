package com.example.lulopruebatecnica.repository;

import com.example.lulopruebatecnica.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva, Long> {
}
