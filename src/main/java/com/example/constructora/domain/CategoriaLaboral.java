package com.example.constructora.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Data
@Entity
@Table(name="categoriaLaboral")
public class CategoriaLaboral {

    @Id
    @NotBlank(message = "categoria must be not empty")
    private String nombreCategoria;

    private float precioHora;

    public CategoriaLaboral() {
    }

    public CategoriaLaboral(String nombreCategoria, float precioHora) {
        this.nombreCategoria = nombreCategoria;
        this.precioHora = precioHora;
    }
}