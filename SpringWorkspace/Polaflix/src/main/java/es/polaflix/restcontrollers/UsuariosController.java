package es.polaflix.restcontrollers;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
	
	@RequestMapping(value = "usuarios/{usuario}/empezadas/{id}", method = RequestMethod.GET)
	public ResponseEntity<SerieEmpezada> getSerieEmpezada(@PathVariable(value="usuario") String usuario, @PathVariable(value="id") int id){
		Usuario u = ur.findByAlias(usuario);
		if(u == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		SerieEmpezada se = null;
		for(SerieEmpezada s: u.getSeriesEmpezadas()){
			if(s.getSerie().getId()==id){
				se = s;
				break;
			}
		}
		return new ResponseEntity<SerieEmpezada>(se,HttpStatus.OK);
	}
	
	@RequestMapping(value = "usuarios/{usuario}/empezadas/{id}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> anadeSerie(@PathVariable(value="usuario") String usuario, @PathVariable(value="id") int idSerie){
		Usuario u = ur.findByAlias(usuario);
		Serie s = sr.findById(idSerie);
		if(u == null || s == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		u.anadeNuevaSerie(s,ur);
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
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
	
	@RequestMapping(value = "usuarios/{usuario}/capitulosVistos/{serie}/{temp}/{cap}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> anadeVisualizacion(@PathVariable(value="usuario") String usuario, @PathVariable(value="serie") int serie, @PathVariable(value="temp") int temp, @PathVariable(value="cap") int cap){
		Usuario u = ur.findByAlias(usuario);
		Serie s = sr.findById(serie);
		Capitulo capi = null;
		if(u == null || s == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		for(Temporada t: s.getTemporadas()){
			if(t.getNumTemp()==temp){
				for(Capitulo c: t.getCapitulos()){
					if(c.getNumCap()==cap){
						capi = c;
						break;
					}
				}
			}
		}
		if(capi == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Calendar now = Calendar.getInstance();
		int dia = now.get(Calendar.DAY_OF_MONTH);
		int mes = now.get(Calendar.MONTH)+1;
		int anyo = now.get(Calendar.YEAR);
		CapituloFactura cf = new CapituloFactura();
		cf.setAnyo(anyo);
		cf.setMes(mes);
		cf.setDia(dia);
		cf.setPrecio(s.getTipo().getPrecioCap());
		cf.setCapitulo(capi);
		cf.setSerie(s);
		sr.save(cf);
		
		u.anadeCapVisto(cf,sr);
		ur.save(u);
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	
	@RequestMapping(value = "usuarios/{usuario}/cargos/{anyo}/{mes}", method = RequestMethod.GET)
	public ResponseEntity<Factura> getCargos(@PathVariable(value="usuario") String usuario,@PathVariable(value="anyo") int anyo, @PathVariable(value="mes") int mes){
		Usuario u = ur.findByAlias(usuario);
		if(u == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Factura f = null;
		for(Factura fac: u.getFacturas()){
			System.out.println("Año: "+fac.getAnyo()+" Mes:"+fac.getMes());
			if(fac.getAnyo()==anyo && fac.getMes()==mes){
				f = fac;
				break;
			}
		}
		return new ResponseEntity<Factura>(f,HttpStatus.OK);
	}
	
	@RequestMapping(value = "usuarios/{usuario}/cuotaMax", method = RequestMethod.GET)
	public ResponseEntity<Double> getCuotaUsuario(@PathVariable(value="usuario") String usuario){
		Usuario u = ur.findByAlias(usuario);
		if(u == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		double cuota;
		if(u instanceof UsuarioCuotaFija){
			cuota = UsuarioCuotaFija.CUOTA_MENSUAL;
		} else {
			cuota = -1;
		}
		return new ResponseEntity<Double>(cuota,HttpStatus.OK);
	}
}
