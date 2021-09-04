package com.example.constructora.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@ToString
@Setter
@Getter
@Entity
@Table(name="obra")
public class Obra {
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    @Column(name="obra_id")
//    private Long id;

    @Id
    private String descriptor;

    @NotBlank(message = "location must be not empty")
    private String ubicacion;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Obra() {
    }

    public Obra(String ubicacion, String descriptor, LocalDate fechaInicio, LocalDate fechaFin) {
//        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.ubicacion = ubicacion;
        this.descriptor = descriptor;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

//    public Obra(String ubicacion, String descriptor, LocalDate fechaInicio, LocalDate fechaFin) {
////        this.id = id;
//        this.ubicacion = ubicacion;
//        this.descriptor = descriptor;
//        this.fechaInicio = fechaInicio;
//        this.fechaFin = fechaFin;
//    }
}
