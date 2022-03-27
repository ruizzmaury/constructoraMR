package com.example.constructora.repository;

import com.example.constructora.domain.Trabajador;

import com.example.constructora.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.sis.internal.jaxb.metadata.EX_Extent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TrabajadorServiceImpl implements TrabajadorService {

    @Autowired
    private final TrabajadorRepository trabajadorRepository;


    @Override
    @Transactional
    public Trabajador save(@NotNull @Valid final Trabajador trabajador) {
        log.debug("Creating {}", trabajador);

//      COMO EL ID ES AUTO-GENERADO NUNCA SERÁ EL MISMO
//        if (trabajadorRepository.existsById(trabajador.getId())){
//            System.out.println("KFUE YA EXISTE MAMA HUEVO");
//            return null;
//        } else {
//            return trabajadorRepository.save(trabajador);
//        }
        return trabajadorRepository.save(trabajador);
    }

    @Override
    public List<Trabajador> getTrabajadores() {
        log.info("getTrabajadores was called");
        return trabajadorRepository.findAll();
    }

    @Override
    public Trabajador getTrabajador(String DNI) {
        return trabajadorRepository
                .getTrabajador(DNI);

    }


    @Override
    public List<Trabajador> findByNombreStartingWith(String nombre) {
        log.debug("Retrieving the list of all workers with name started by {} " + nombre);

        return (List<Trabajador>) trabajadorRepository.findByNombreStartingWithIgnoreCase(
                nombre.toUpperCase(),
                Sort.by(Sort.Direction.ASC, "nombre")
        );
    }

    @Override
    public List<Trabajador> findByEmailStartingWith(String email) {
        log.debug("Retrieving the list of all workers with email started by {} " + email);

        return (List<Trabajador>) trabajadorRepository.findByEmailStartingWithIgnoreCase(
                email.toUpperCase(),
                Sort.by(Sort.Direction.ASC, "nombre")
        );
    }


    @Override
    public Trabajador update(@NotNull @Valid final Trabajador trabajador) {
        log.debug("Updating {}", trabajador);

        Trabajador existing = trabajadorRepository.getTrabajador(trabajador.getTrabajador_dni());

        if (existing == null) {
            throw new NotFoundException("Worker with DNI '" + trabajador.getTrabajador_dni() + "' not found...");
        }

        return trabajadorRepository.save(trabajador);

    }

    @Override
    public Trabajador delete(@NotNull @Valid final String DNI) {
        log.debug("Deleting worker with DNI '{}'", DNI);

        Trabajador existing = trabajadorRepository.getTrabajador(DNI);

        if (existing == null) {
            throw new NotFoundException("Worker with DNI '" + DNI + "' not found...");
        }

        trabajadorRepository.delete(existing);
        return existing;
    }
}
