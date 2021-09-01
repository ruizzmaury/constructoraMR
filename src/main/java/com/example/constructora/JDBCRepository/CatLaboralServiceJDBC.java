package com.example.constructora.JDBCRepository;

import com.example.constructora.domain.CategoriaLaboral;
import com.example.constructora.domain.Genero;

import java.util.List;

public interface CatLaboralServiceJDBC {

    CategoriaLaboral create(CategoriaLaboral categoriaLaboral);

    List<CategoriaLaboral> createAll(List<CategoriaLaboral> categoriaLaborales);

    List<CategoriaLaboral> getCatLaborales();

    List<String> getCatLaboralNames();

    CategoriaLaboral getCatLaboral(String nombreCategoria);

    float getPrecioHora(String nombreCategoria);

    CategoriaLaboral update(CategoriaLaboral categoriaLaboral);

    CategoriaLaboral delete(String nombreCategoria);
}
