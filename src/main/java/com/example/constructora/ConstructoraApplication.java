package com.example.constructora;


import com.example.constructora.view.MainMenu;
import com.example.constructora.view.MenuLateral;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConstructoraApplication {


	public static void main(String[] args)  {

		SpringApplication.run(ConstructoraApplication.class, args);
		System.setProperty("java.awt.headless", "false");
//		MainMenu m = new MainMenu();
		MenuLateral menuLateral = new MenuLateral();
	}

}