package com.example.constructora.rest;

import com.example.constructora.domain.Trabajador;
import com.example.constructora.repository.TrabajadorServiceImpl;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping(path = "api/v1/trabajadores")
@RestController
public class TrabajadorController {

    private final TrabajadorServiceImpl trabajadorServiceImpl;

    @Autowired
    public TrabajadorController(TrabajadorServiceImpl trabajadorServiceImpl) {
        this.trabajadorServiceImpl = trabajadorServiceImpl;
    }


    @GetMapping
    List<Trabajador> getTrabajadores() {
        return trabajadorServiceImpl.getTrabajadores();
    }

    // GET: http://localhost:8080/api/v1/trabajadores/1
    @GetMapping(path = "{trabajadorId}")
    Trabajador getTrabajador(@PathVariable("trabajadorId") Long id) throws NotFoundException {
//        return trabajadorServiceImpl.getTrabajador(id);
        return null;
    }

    // GET: http://localhost:8080/api/v1/trabajadores/searchName?nombre=be
    @GetMapping(path = "/searchName")
    List<Trabajador> getTrabajadorByName(@RequestParam String nombre) {
        Assert.isTrue(!nombre.isEmpty(), "name parameter must be present");
        return trabajadorServiceImpl.findByNombreStartingWith(nombre);
    }

    // GET: http://localhost:8080/api/v1/trabajadores/searchEmail?email=be
    @GetMapping(path = "/searchEmail")
    List<Trabajador> getTrabajadorByEmail(@RequestParam String email) {
        Assert.isTrue(!email.isEmpty(), "email parameter must be present");
        return trabajadorServiceImpl.findByEmailStartingWith(email);
    }


    // POST: http://localhost:8080/api/v1/trabajadores
    // Content-Type: application/json
    // Payload: { "nombre": "Maury Ruiz", "Telefono": "601547841", ... }
    @PostMapping
    Trabajador createNewTrabajador(@Valid @RequestBody Trabajador trabajador) {
        log.debug("Received request to create the {}", trabajador);
        return trabajadorServiceImpl.save(trabajador);
    }

    // PUT: http://localhost:8080/api/v1/trabajadores/1
    // Content-Type: application/json
    @PutMapping
    void updateTrabajador(@RequestBody Trabajador trabajador) {
        log.debug("Received request to update the {}", trabajador);
        trabajadorServiceImpl.update(trabajador);
    }


    // DELETE: http://localhost:8080/api/v1/trabajadores/1
    @DeleteMapping(path = "{trabajadorId}")
    void deleteTrabajador(@PathVariable("trabajadorId") Long id) {
        log.debug("DELETE REQUEST FOR CUSTOMER WITH ID {}", id);
//        trabajadorServiceImpl.delete(id);
    }
}
