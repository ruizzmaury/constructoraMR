package com.example.constructora.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name="genero")
public class Genero {

    @Id
    @NotBlank(message = "genre must be not empty")
    @Column(name="nombreGenero")
    private String nombreGenero;

    public Genero() {
    }

    public Genero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }
}