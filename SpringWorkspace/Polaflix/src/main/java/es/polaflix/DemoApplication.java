package es.polaflix;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.polaflix.repositories.*;

@SpringBootApplication
public class DemoApplication{

	@Autowired
	static SeriesRepository sr;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner runner(SeriesRepository sr, UsersRepository ur){
		return args -> {
			/*
			Usuario u = new UsuarioCuotaFija();
			u.setAlias("Mike");
			u.setPassword("algo");
			u.setCuentaBancaria("Ninguna");
			ur.save(u);
			*/
		};
	}
}
