package com.example.constructora.JDBCRepository;

import com.example.constructora.domain.Obra;
import com.example.constructora.domain.Pago;

import java.time.LocalDate;
import java.util.List;

public interface ObrasServiceJDBC {

    Obra create(Obra obra);

    List<Obra> getObras();

    List<String> getObrasDescriptor();

    List<String> getObrasDescriptors();

    Obra getObra(String descriptor);

    List<Obra> findByUbicacionStartingWith(String ubicacion);

    List<Obra> findByDescriptorStartingWith(String descriptor);

    List<Obra> findByDateForward(LocalDate fechaInicioFilter);

    List<Obra> findByDateBackward(LocalDate fechaFinFilter);

    List<Obra> findByNameAndDate(String obraDescriptor, LocalDate fechaInicioFilter, LocalDate fechaFinFilter);

    List<Obra> findBetweenDates(LocalDate fechaInicioFilter, LocalDate fechaFinFilter);

    Obra update(Obra obra);

    void delete(String descriptor);

}
