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

    Pago update(Pago pago);

    void delete(long pago_id);

}
