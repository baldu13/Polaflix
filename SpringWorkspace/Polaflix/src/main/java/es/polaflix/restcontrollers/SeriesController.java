package es.polaflix.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import es.polaflix.domain.*;
import es.polaflix.repositories.SeriesRepository;

@RestController
public class SeriesController {

	@Autowired
	SeriesRepository sr;
	
	/**
	 * Obtiene las series
	 * @param texto Texto que contiene, solo aplicable si no se especifica inicial
	 * @param inicial Inicial de la serie (PRIORITARIO FRENTE A texto)
	 * @return Lista de series
	 */
	@RequestMapping(value = "/series", method = RequestMethod.GET)
	public List<Serie> seriesWithText(@RequestParam(value="withText", defaultValue="") String texto,@RequestParam(value="inicial", defaultValue="") String inicial){
		List<Serie> series;
		if(!"".equals(inicial)){
			series = sr.findByInicial(inicial.charAt(0));
		}else{
			series = sr.findBySearchQuery(texto);
		}
		return series;
	}
	
	@RequestMapping(value = "/series/{serie}", method = RequestMethod.GET)
	public ResponseEntity<Serie> getSerie(@PathVariable(value="serie") int serie){
		Serie s = sr.findById(serie);
		if(s == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Serie>(s,HttpStatus.OK);
	}
	
	
	
}
