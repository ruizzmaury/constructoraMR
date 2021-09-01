package com.example.constructora.trabajador;


import com.example.constructora.domain.Trabajador;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class TrabajadorFakeRepository implements TrabajadorRepo {
    @Override
    public List<Trabajador> getTrabajadores() {
        return null;
//        return Arrays.asList(
//                new Trabajador(
//                        1L,
//                        "Juaquín Pereira",
//                        Trabajador.genero.HOMBRE,
//                        686974852,
//                        "juaquipe@gmail.com",
//                        LocalDate.of(2000, Month.JANUARY,5),
//                        "c/ tu mama, 98",
//                        Trabajador.catLaboral.ALBANYIL1
//                ),
//                new Trabajador(
//                        1L,
//                        "Rosa Díaz",
//                        Trabajador.genero.MUJER,
//                        687451020,
//                        "rosadd@gmail.com",
//                        LocalDate.of(1997, Month.JANUARY,5),
//                        "c/ lo ma fuelte, 8",
//                        Trabajador.catLaboral.ALBANYIL1
//                )
//        );

    }
}
