package com.example.constructora.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class RepWorkerDate {
    @Id
    private Long id;
    private String descriptor;

    private LocalDate fechaPago;

    @Column(name="trabajador_dni")
    @Size(min = 9, max = 9)
    private String trabajador_dni;

    @NotBlank(message = "name must be not empty")
    private String nombre;

    private int horas;
    private float cantidad;

    public RepWorkerDate(String descriptor, LocalDate fechaPago, String trabajador_dni, String nombre, int horas, float cantidad) {
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.descriptor = descriptor;
        this.fechaPago = fechaPago;
        this.trabajador_dni = trabajador_dni;
        this.nombre = nombre;
        this.horas = horas;
        this.cantidad = cantidad;
    }
}
