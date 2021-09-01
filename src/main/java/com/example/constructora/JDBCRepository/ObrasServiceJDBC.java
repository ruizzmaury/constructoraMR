package com.example.constructora.JDBCRepository;

import com.example.constructora.domain.Obra;

import java.time.LocalDate;
import java.util.List;

public interface ObrasServiceJDBC {

    Obra create(Obra obra);

    List<Obra> getObras();

    List<Long> getObrasIDs();

    List<String> getObrasDescriptors();

    Obra getObra(long id);

    List<Obra> findByUbicacionStartingWith(String ubicacion);

    List<Obra> findByDescriptorStartingWith(String descriptor);

    List<Obra> findBetweenDates(LocalDate fechaInicioFilter, LocalDate fechaFinFilter);

    Obra update(Obra obra);

    void delete(long obra_id);

}
