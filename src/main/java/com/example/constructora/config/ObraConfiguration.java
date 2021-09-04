package com.example.constructora.config;

import com.example.constructora.domain.Obra;
import com.example.constructora.repository.ObraRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class ObraConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerObra (ObraRepository obraRepository) {
        return args -> {
            Obra obra1 = new Obra(
                    "Cala Millor",
                    "CALA MILLOR SUR",
                    LocalDate.of(2020, Month.FEBRUARY, 20),
                    LocalDate.of(2021, Month.FEBRUARY, 20)
            );

            Obra obra2 = new Obra(
                    "Binissalem, carrer del Torró",
                    "BINISSALEM PLAZA",
                    LocalDate.of(2020, Month.MARCH, 2),
                    LocalDate.of(2021, Month.JULY, 11)
            );

            System.out.println(LocalDate.of(1998, Month.FEBRUARY, 20).toString());

            obraRepository.saveAll(
                    List.of(obra1, obra2)
            );


            System.out.println("-----------------------------------------------------------------------");


        };
    }
}



