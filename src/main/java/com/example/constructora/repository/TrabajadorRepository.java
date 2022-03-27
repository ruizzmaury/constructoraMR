package com.example.constructora.repository;

import com.example.constructora.domain.Trabajador;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {

    List<Trabajador> findByNombreStartingWithIgnoreCase(String nombre, Sort sort);

    List<Trabajador> findByEmailStartingWithIgnoreCase(String email, Sort sort);
    
    @Query("SELECT t FROM Trabajador t WHERE t.trabajador_dni=?1")
    Trabajador getTrabajador(String DNI);


}
