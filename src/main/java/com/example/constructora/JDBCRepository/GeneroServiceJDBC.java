package com.example.constructora.JDBCRepository;

import com.example.constructora.domain.Genero;

import java.util.List;

public interface GeneroServiceJDBC {

    Genero create(Genero genero);

    List<Genero> createAll(List<Genero> generos);

    List<Genero> getGeneros();

    Genero getGenero(String nombreGenero);

    Genero update(Genero genero);

    Genero delete(String nombreGenero);

}
