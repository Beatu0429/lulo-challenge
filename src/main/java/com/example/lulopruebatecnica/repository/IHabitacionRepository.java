package com.example.lulopruebatecnica.repository;

import com.example.lulopruebatecnica.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IHabitacionRepository extends JpaRepository<Habitacion, Long> {

    @Query(value = "select * from habitaciones where id not in (select habitacion_id" +
            " from reservas where greatest(fecha_ingreso, :fechaIngreso) <= " +
            "least(fecha_salida, :fechaSalida)" +
            "or :fechaActual < fecha_ingreso)",
            nativeQuery = true)
    public List<Habitacion> getHabitacionesDisponibles(LocalDate fechaIngreso, LocalDate fechaActual, LocalDate fechaSalida);

}
