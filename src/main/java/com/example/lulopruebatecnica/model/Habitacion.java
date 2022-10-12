package com.example.lulopruebatecnica.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "habitaciones")
@NoArgsConstructor
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer personasMaximas;
    private Double precio;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "habitacion_id")
    private List<Reserva> reservas;

}
