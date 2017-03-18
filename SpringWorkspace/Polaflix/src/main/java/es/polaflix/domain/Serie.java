package es.polaflix.domain;

import java.util.Set;

public class Serie implements Comparable<Serie>{

	private int id;
	private String nombre;
	private String descripcion;
	private TipoSerie tipo;
	private Set<Temporada> temporadas;
	
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
