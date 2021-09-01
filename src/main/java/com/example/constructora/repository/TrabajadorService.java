package com.example.constructora.repository;


import com.example.constructora.domain.Trabajador;

import java.util.List;

public interface TrabajadorService {

    Trabajador save(Trabajador trabajador);
    List<Trabajador> getTrabajadores();
    Trabajador getTrabajador(String DNI);
    List<Trabajador> findByNombreStartingWith(String nombre);
    List<Trabajador> findByEmailStartingWith(String email);
    Trabajador update(Trabajador trabajador);
    Trabajador delete(String DNI);

}

