package es.polaflix.restcontrollers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import es.polaflix.domain.*;
import es.polaflix.repositories.SeriesRepository;
import es.polaflix.repositories.UsersRepository;

@RestController
public class UsuariosController {

	@Autowired
	UsersRepository ur;
	@Autowired
	SeriesRepository sr;
	
	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public List<Usuario> usuarios(){
		return ur.findAll();
	}
	
	@RequestMapping(value = "usuarios/{usuario}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> getUsuario(@PathVariable(value="usuario") String usuario){
		Usuario u = ur.findByAlias(usuario);
		if(u == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(u,HttpStatus.OK);
	}
	
	@RequestMapping(value = "usuarios/{usuario}/empezadas", method = RequestMethod.GET)
	public ResponseEntity<Set<SerieEmpezada>> getSeriesEmpezadas(@PathVariable(value="usuario") String usuario){
		Usuario u = ur.findByAlias(usuario);
		if(u == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Set<SerieEmpezada>>(u.getSeriesEmpezadas(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "usuarios/{usuario}/pendientes", method = RequestMethod.GET)
	public ResponseEntity<Set<Serie>> getSeriesPendientes(@PathVariable(value="usuario") String usuario){
		Usuario u = ur.findByAlias(usuario);
		if(u == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Set<Serie>>(u.getSeriesPendientes(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "usuarios/{usuario}/terminadas", method = RequestMethod.GET)
	public ResponseEntity<Set<Serie>> getSeriesTerminadas(@PathVariable(value="usuario") String usuario){
		Usuario u = ur.findByAlias(usuario);
		if(u == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Set<Serie>>(u.getSeriesTerminadas(),HttpStatus.OK);
	}
}
