package es.polaflix;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.polaflix.domain.*;
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
			//CODIGO PARA PROBAR
			/*
				List<Serie> series = sr.findAll();
				for(Serie s: series)
					System.out.println(s.getNombre());
				Serie s = sr.findById(2);
				System.out.println(s.getNombre());
			*/
			List<Serie> ss = sr.findAll();
			for(Serie s: ss){
				System.out.println("Titulo: "+s.getNombre());
				System.out.println("Descripcion: "+s.getDescripcion());
				System.out.println();
				for(Temporada t: s.getTemporadas()){
					System.out.println("   Temporada "+t.getNumTemp());
					for(Capitulo c: t.getCapitulos()){
						System.out.println("      Capitulo "+c.getNumCap()+": "+c.getTitulo());
					}
				}
				System.out.println();
				System.out.println();
			}
		};
	}
}
