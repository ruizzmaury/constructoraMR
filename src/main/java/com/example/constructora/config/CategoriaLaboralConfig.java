package com.example.constructora.config;

import com.example.constructora.JDBCRepository.CatLaboralServiceJDBC;
import com.example.constructora.domain.CategoriaLaboral;
import com.example.constructora.repository.CatLaboralRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class CategoriaLaboralConfig {

    @Bean
    CommandLineRunner commandLineRunnerCatLaboral (CatLaboralRepository catLaboralRepository) {
        System.out.println("HOLA DESDE EL COMMANDLINE CATLABORAL");

        return args -> {
            CategoriaLaboral peon = new CategoriaLaboral(
                    "Peón",
                    10
            );
            CategoriaLaboral albanyil1 = new CategoriaLaboral(
                    "Albañil 1",
                    15
            );
            CategoriaLaboral albanyil2 = new CategoriaLaboral(
                    "Albañil 2",
                    20
            );
            CategoriaLaboral maestroObra = new CategoriaLaboral(
                    "Maestro Obra",
                    25
            );

            catLaboralRepository.saveAll(
                    List.of(peon, albanyil1, albanyil2, maestroObra)
            );

            System.out.println("-----------------------------------------------------------------------");




        };
    }
}
