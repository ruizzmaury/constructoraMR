package com.example.constructora.config;


import com.example.constructora.JDBCRepository.GeneroServiceJDBC;
import com.example.constructora.domain.Genero;

import com.example.constructora.repository.GeneroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GeneroConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerGenero (GeneroRepository generoRepository) {
        return args -> {
            Genero hombre = new Genero(
                    "Hombre"
            );
            Genero mujer = new Genero(
                    "Mujer"
            );
            Genero otro = new Genero(
                    "Otro"
            );

            generoRepository.saveAll(
                    List.of(hombre, mujer, otro)
            );

            System.out.println("-----------------------------------------------------------------------");


        };
    }
}
