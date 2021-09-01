package com.example.constructora.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@ToString
@Setter
@Getter
@Entity
@Table(name="trabajador")
public class Trabajador {

//    @Id
//    // @GeneratedValue(strategy=GenerationType.AUTO)
//    @Column(name="trabajador_id")
//    private Long id;

    @Id
    @Column(name="trabajador_dni")
    @Size(min = 9, max = 9)
    private String trabajador_dni;

    @NotBlank(message = "name must be not empty")
    private String nombre;


    @NotBlank(message = "telefono must be not empty")
    private int telefono;

    @NotBlank(message = "email must be not empty")
    @Email
    private String email;

    @OneToOne
    @JoinColumn(name = "genero", referencedColumnName = "nombreGenero")
    private Genero genero;

    private LocalDate fechaNacimiento;
    private String direccion;

    @OneToOne
    @JoinColumn(name = "cat_laboral", referencedColumnName = "nombreCategoria")
    private CategoriaLaboral catLaboral;

    public Trabajador() {
    }

    public Trabajador(String trabajador_dni, String nombre, Genero genero, int telefono, String email, LocalDate fechaNacimiento, String direccion, CategoriaLaboral catLaboral) {
//        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.trabajador_dni = trabajador_dni;
        this.nombre = nombre;
        this.genero = genero;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.catLaboral = catLaboral;
    }

//    public Trabajador(Long id, String trabajador_dni, String nombre, Genero genero, int telefono, String email, LocalDate fechaNacimiento, String direccion, CategoriaLaboral catLaboral) {
//        this.id = id;
//        this.trabajador_dni = trabajador_dni;
//        this.nombre = nombre;
//        this.genero = genero;
//        this.telefono = telefono;
//        this.email = email;
//        this.fechaNacimiento = fechaNacimiento;
//        this.direccion = direccion;
//        this.catLaboral = catLaboral;
//    }



}