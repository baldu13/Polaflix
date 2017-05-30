package es.polaflix.domain;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

import es.polaflix.repositories.SeriesRepository;
import es.polaflix.repositories.UsersRepository;

@Entity
@Proxy(lazy = false)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario implements Comparable<Usuario>{
	@Id
	@GeneratedValue
	private int id;
	private String alias;
	private String password;
	private String cuentaBancaria;
	@ManyToMany
	@JoinTable(
			   name = "usuario_seriesTerminadas", 
			   joinColumns = @JoinColumn(name = "usuario_id"), 
			   inverseJoinColumns = @JoinColumn(name = "serie_id"))
	private Set<Serie> seriesTerminadas;
	@ManyToMany
	@JoinTable(
			   name = "usuario_seriesPendientes", 
			   joinColumns = @JoinColumn(name = "usuario_id"), 
			   inverseJoinColumns = @JoinColumn(name = "serie_id"))
	private Set<Serie> seriesPendientes;
	@OneToMany 
	@JoinTable(
			   name = "usuario_seriesEmpezadas", 
			   joinColumns = @JoinColumn(name = "usuario_id"), 
			   inverseJoinColumns = @JoinColumn(name = "serie_empezada_id"))
	private Set<SerieEmpezada> seriesEmpezadas;
	@OneToMany
	private Set<Factura> facturas;
	
	public Usuario() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public Set<Serie> getSeriesTerminadas() {
		return seriesTerminadas;
	}

	public void setSeriesTerminadas(Set<Serie> seriesTerminadas) {
		this.seriesTerminadas = seriesTerminadas;
	}

	public Set<Serie> getSeriesPendientes() {
		return seriesPendientes;
	}

	public void setSeriesPendientes(Set<Serie> seriesPendientes) {
		this.seriesPendientes = seriesPendientes;
	}

	public Set<SerieEmpezada> getSeriesEmpezadas() {
		return seriesEmpezadas;
	}

	public void setSeriesEmpezadas(Set<SerieEmpezada> seriesEmpezadas) {
		this.seriesEmpezadas = seriesEmpezadas;
	}

	public Set<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(Set<Factura> facturas) {
		this.facturas = facturas;
	}
	
	public void anadeCapVisto(CapituloFactura cf, SeriesRepository sr, UsersRepository ur){
		//Se añade el cargo a la cuenta
		boolean added = false;
		
		for(Factura f: facturas){
			if(!added && f.getAnyo()==cf.getAnyo() && f.getMes()==cf.getMes()){
				f.getCapitulos().add(cf);
				added = true;
			}
		}
		
		if(!added){
			Calendar now = Calendar.getInstance();
			int mes = now.get(Calendar.MONTH)+1;
			int anyo = now.get(Calendar.YEAR);
			Factura f = new Factura();
			f.setAnyo(anyo);
			f.setMes(mes);
			Set<CapituloFactura> newSet = new HashSet<CapituloFactura>();
			newSet.add(cf);
			f.setCapitulos(newSet);
			sr.save(f);
			facturas.add(f);
		}
		
		//Se anade el cap a la lista de caps vistos
		boolean visto = false;
		for(SerieEmpezada se: seriesEmpezadas){
			if(se.getSerie().getId() == cf.getSerie().getId()){
				for(Capitulo c: se.getCapitulos()){
					if(c.getId()==cf.getCapitulo().getId()){
						visto = true;
					}
				}
				if(!visto){
					//Añado el nuevo cap a los vistos
					System.err.println("Añadido a la lista de vistos!");
					se.getCapitulos().add(cf.getCapitulo());
					sr.save(se);
					visto = true;
				}
			}
		}
		
		//Si la serie no esta empezada...
		if(!visto){
			SerieEmpezada se = new SerieEmpezada();
			se.setSerie(cf.getSerie());
			Set<Capitulo> caps = new HashSet<Capitulo>();
			caps.add(cf.getCapitulo());
			se.setCapitulos(caps);
			sr.save(se);
			seriesEmpezadas.add(se);
			if(seriesPendientes.contains(cf.getSerie())){
				seriesPendientes.remove(cf.getSerie());
			}
			if(seriesTerminadas.contains(cf.getSerie())){
				seriesTerminadas.remove(cf.getSerie());
			}
		}
		
		//Si hemos visto todos los capitulos, la pasamos a terminada
		int capsVistos = 0;
		int capsTotales = 0;
		for(Temporada t: cf.getSerie().getTemporadas()){
			capsTotales += t.getCapitulos().size();
		}
		for(SerieEmpezada se: seriesEmpezadas){
			if(se.getSerie().getId()==cf.getSerie().getId()){
				capsVistos = se.getCapitulos().size();
				if(capsVistos>=capsTotales){
					seriesTerminadas.add(cf.getSerie());
					seriesEmpezadas.remove(se);
				}
				break;
			}
		}
		//Guardamos para terminar
		ur.save(this);
	}
	
	public void anadeNuevaSerie(Serie s, UsersRepository ur){
		boolean registrada = false;
		registrada = (seriesPendientes.contains(s) || seriesTerminadas.contains(s));
		for(SerieEmpezada se: seriesEmpezadas){
			if(se.getSerie().getId() == s.getId()){
				registrada = true;
				break;
			}
		}
		if(!registrada){
			this.seriesPendientes.add(s);
			ur.save(this);
		}
	}
	
	@Override
	public boolean equals(Object o){
		Usuario u = (Usuario) o;
		return alias.equals(u.getAlias());
	}
	
	@Override
	public int compareTo(Usuario u){
		return alias.compareTo(u.getAlias());
	}
}
