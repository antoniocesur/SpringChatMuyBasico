package com.antonio.chat;

import com.antonio.chat.modelo.Usuario;
import com.antonio.chat.servicios.ServicioUsuarios;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Locale;

@SpringBootApplication
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ServicioUsuarios servicioUsuarios){
		return args -> {
			if(servicioUsuarios.findAll()!=null) {
				servicioUsuarios.save(new Usuario("Benito", "benito@benito.com"));
				for (int i = 0; i < 10; i++) {
					servicioUsuarios.save(new Usuario("María " + i, "maria" + i + "@benito.com"));
				}
			}
		};
	}

}
