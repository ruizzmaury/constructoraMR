package com.example.constructora.config;

import com.example.constructora.JDBCRepository.CatLaboralServiceJDBC;
import com.example.constructora.JDBCRepository.GeneroServiceJDBC;
import com.example.constructora.domain.CategoriaLaboral;
import com.example.constructora.domain.Genero;
import com.example.constructora.domain.Trabajador;

import com.example.constructora.repository.CatLaboralRepository;
import com.example.constructora.repository.GeneroRepository;
import com.example.constructora.repository.TrabajadorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class TrabajadorConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerTrabajador (TrabajadorRepository trabajadorRepository,
                                                   GeneroRepository generoRepository,
                                                   CatLaboralRepository catLaboralRepository) {
        return args -> {
            Trabajador pepe = new Trabajador(
                    "43233344P",
                    "Pepe",
                    generoRepository.getById("Hombre"),
                    971904352,
                    "pepaso@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 24),
                    "calle lo ma fuelte",
                    catLaboralRepository.getById("Albañil 1")
            );

            Trabajador maria = new Trabajador(
                    "53233333L",
                    "Maria Fuelte",
                    generoRepository.getById("Mujer"),
                    625874512,
                    "maria@gmail.com",
                    LocalDate.of(1998, Month.FEBRUARY, 20),
                    "calle de aún maa fueltes",
                    catLaboralRepository.getById("Maestro Obra")
            );


            trabajadorRepository.saveAll(
                    List.of(pepe, maria)
            );


            System.out.println("-----------------------------------------------------------------------");



        };

    }


//    @Bean
//    TrabajadorRepo trabajadorRepo() {
//        System.out.println("LO MA FUELTE INSTANCIAOS");
//        return new TrabajadorFakeRepository();
//    }
}
