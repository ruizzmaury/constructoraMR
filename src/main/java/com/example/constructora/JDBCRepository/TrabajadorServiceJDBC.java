package com.example.constructora.JDBCRepository;

import com.example.constructora.domain.Trabajador;

import java.util.List;

public interface TrabajadorServiceJDBC {

    Trabajador create(Trabajador trabajador);

    List<Trabajador> getTrabajadores();

    List<String> getTrabajadoresDNIs();

    List<String> getTrabajadoresNames();

    Trabajador getTrabajador(String DNI);

    String getCategoriaLaboral(String DNI);

    Trabajador findByDNI(String DNI);

    List<Trabajador> findByNombreStartingWith(String nombre);

    List<Trabajador> findByEmailStartingWith(String email);

    Trabajador update(Trabajador trabajador);

    void delete(String DNI);

}
