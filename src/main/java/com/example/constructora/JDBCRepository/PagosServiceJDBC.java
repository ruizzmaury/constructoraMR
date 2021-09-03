package com.example.constructora.JDBCRepository;


import com.example.constructora.domain.Obra;
import com.example.constructora.domain.Pago;
import com.example.constructora.domain.Trabajador;

import java.time.LocalDate;
import java.util.List;

public interface PagosServiceJDBC {

    Pago create(Pago pago, String trabajador_dni);

    List<Pago> getPagos();

    Pago getPago(long id);

    List<Pago> findByTrabajadorDNI(Trabajador trabajador);

    List<Pago> findByObraDescriptor(Obra obra);

    List<Pago> findByDate(LocalDate fechaPago);

    List<Pago> findBetweenDates(LocalDate fechaInicioFilter, LocalDate fechaFinFilter);

    List<Pago> findByDateForward(LocalDate fechaInicioFilter);

    List<Pago> findByDateBackward(LocalDate fechaFinFilter);

    List<Pago> findByNameAndDate(String workerName, LocalDate fechaInicioFilter, LocalDate fechaFinFilter);

    Pago update(Pago pago);

    void delete(long pago_id);

}
