package com.example.lulopruebatecnica.repository;

import com.example.lulopruebatecnica.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva, Long> {
}
