package es.polaflix;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.HashSet;
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
			//Insercion de datos de ejemplo en la base de datos, a parte de los datos estaticos
			//introducidos mediante script sql a la hora de crear la BBDD
			/*Usuario u = ur.findByAlias("gryphus");
			Serie s = sr.findById(1);
			SerieEmpezada se = new SerieEmpezada();
			se.setSerie(s);
			Set<Capitulo> caps = s.getTemporadas().iterator().next().getCapitulos();
			Set<Capitulo> vistos = new HashSet<Capitulo>();
			for(Capitulo c: caps){
				if(c.getId()%2==1){
					vistos.add(c);
				}
			}
			se.setCapitulos(vistos);
			Set<SerieEmpezada> sem = new HashSet<SerieEmpezada>();
			sem.add(se);
			u.setSeriesEmpezadas(sem);
			Set<Serie> set = new HashSet<Serie>();
			set.add(sr.findById(3));
			u.setSeriesTerminadas(set);
			sr.save(se);
			ur.save(u);*/
		};
	}
}
