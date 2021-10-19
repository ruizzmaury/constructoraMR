package com.example.constructora.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@ToString
@Setter
@Getter
@Entity
@Table(name="Pago")
public class Pago {

    @Id
    // @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="pago_id")
    private Long id;

    private int horas;
//    private int numKm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabajador_dni", referencedColumnName = "trabajador_dni")
    @Size(min = 9, max = 9)
    private Trabajador trabajadorPago;

    @ManyToOne(targetEntity = Obra.class)
    @JoinColumn(name = "obra_descriptor")
    private String obraDescriptor;

    private LocalDate fechaPago;

    private float cantidad;

    public Pago() {
    }

    public Pago(int horas, Trabajador trabajadorPago, String obraDescriptor, LocalDate fechaPago, float cantidad) {
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.horas = horas;
        this.trabajadorPago = trabajadorPago;
        this.obraDescriptor = obraDescriptor;
        this.fechaPago = fechaPago;
        this.cantidad = cantidad;
    }


    public Pago(Long id, int horas, Trabajador trabajadorPago, String obraDescriptor, LocalDate fechaPago, float cantidad) {
        this.id = id;
        this.horas = horas;
        this.trabajadorPago = trabajadorPago;
        this.obraDescriptor = obraDescriptor;
        this.fechaPago = fechaPago;
        this.cantidad = cantidad;
    }


}
