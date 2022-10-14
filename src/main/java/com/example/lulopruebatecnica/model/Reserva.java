package com.example.lulopruebatecnica.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "reservas")
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreHuesped;
    private String documentoIdentidad;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habitacion_id")
    @JsonBackReference
    private Habitacion habitacion;


}
