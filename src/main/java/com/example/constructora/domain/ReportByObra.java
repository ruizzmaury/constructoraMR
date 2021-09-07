package com.example.constructora.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Setter
@Getter
@Entity
@Immutable
//@Table(name="selected_obra")
@Subselect(
        "SELECT pago.obra_descriptor, pago.trabajador_dni, trabajador.nombre, SUM(pago.horas) AS horas, SUM(pago.cantidad) AS cantidad " +
        "FROM pago INNER JOIN trabajador ON pago.trabajador_dni = trabajador.trabajador_dni " +
        "GROUP BY pago.obra_descriptor, pago.trabajador_dni, trabajador.nombre") // sobre esto hacer query con condicional seleccionando las líneas que queramos
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
