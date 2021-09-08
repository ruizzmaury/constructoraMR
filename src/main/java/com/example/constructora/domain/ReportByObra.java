package com.example.constructora.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Setter
@Getter
public class ReportByObra {
    @Id
    private Long id;
    private String descriptor;

    @Column(name="trabajador_dni")
    @Size(min = 9, max = 9)
    private String trabajador_dni;

    @NotBlank(message = "name must be not empty")
    private String nombre;

    private int horas;
    private float cantidad;

    public ReportByObra() {
    }

    public ReportByObra(String descriptor, String trabajador_dni, String nombre, int horas, float cantidad) {
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.descriptor = descriptor;
        this.trabajador_dni = trabajador_dni;
        this.nombre = nombre;
        this.horas = horas;
        this.cantidad = cantidad;
    }
}
