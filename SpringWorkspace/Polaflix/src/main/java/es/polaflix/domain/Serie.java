package es.polaflix.domain;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Serie implements Comparable<Serie>{

	@Id
	@GeneratedValue
	private int id;
	private String nombre;
	private String descripcion;
	@ManyToOne
	private TipoSerie tipo;
	@OneToMany(fetch = FetchType.EAGER)
	@OrderBy("numTemp asc")
	private Set<Temporada> temporadas;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			   name = "serie_creadores", 
			   joinColumns = @JoinColumn(name = "serie_id"), 
			   inverseJoinColumns = @JoinColumn(name = "creador_id"))
	@OrderBy("apellido asc")
	private Set<Creador> creadores;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			   name = "serie_actores", 
			   joinColumns = @JoinColumn(name = "serie_id"), 
			   inverseJoinColumns = @JoinColumn(name = "actor_id"))
	@OrderBy("apellido asc")
	private Set<Actor> actores;
	
	public Serie() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoSerie getTipo() {
		return tipo;
	}

	public void setTipo(TipoSerie tipo) {
		this.tipo = tipo;
	}

	public Set<Temporada> getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(Set<Temporada> temporadas) {
		this.temporadas = temporadas;
	}
	
	public Set<Creador> getCreadores() {
		return creadores;
	}

	public void setCreadores(Set<Creador> creadores) {
		this.creadores = creadores;
	}

	public Set<Actor> getActores() {
		return actores;
	}

	public void setActores(Set<Actor> actores) {
		this.actores = actores;
	}

	@Override
	public int compareTo(Serie s){
		int compare = nombre.compareTo(s.getNombre());
		if(compare == 0){
			compare = descripcion.compareTo(s.getDescripcion());
		}
		return compare;
	}
	
	@Override
	public boolean equals(Object o){
		Serie s = (Serie) o;
		return id == s.getId();
	}
}
