package com.example.constructora;


import com.example.constructora.view.MenuLateral;
import com.example.constructora.view.panels.WelcomePanel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.time.LocalDate;


@SpringBootApplication
public class ConstructoraApplication {


    public static void main(String[] args) {

        LocalDate currentdate = LocalDate.now();

        if (currentdate.getMonthValue() >= 6 && currentdate.getYear() >= 2022) {
            JOptionPane.showMessageDialog(null, "FECHA LÍMITE USO GRATUITO.\n Contacte con el desarrollador. \n ruizzmaury@gmail.com"
                    , "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            SpringApplication.run(ConstructoraApplication.class, args);
            System.setProperty("java.awt.headless", "false");
            MenuLateral menuLateral = new MenuLateral();
        }


    }

}