package es.polaflix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import es.polaflix.domain.*;

public interface SeriesRepository extends Repository<Serie, Integer>{

	List<Serie> findAll();
	
	Serie findById(int id);
	
	Serie findByNombre(String nombre);
	
	 @Query("SELECT s FROM Serie s WHERE s.nombre LIKE CONCAT(:inicial,'%') ORDER BY s.nombre asc")
	 public List<Serie> findByInicial(@Param("inicial") char inicial);
	 
	 @Query("SELECT s FROM Serie s WHERE s.nombre LIKE CONCAT('%',:text,'%') ORDER BY s.nombre asc")
	 public List<Serie> findBySearchQuery(@Param("text") String text);
	 
	 Serie save(Serie s);
	 
	 SerieEmpezada save(SerieEmpezada s);
	 
	 CapituloFactura save(CapituloFactura cf);
	 
	 Factura save(Factura f);
}
