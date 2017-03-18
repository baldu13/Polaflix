package es.polaflix.domain;

import java.util.Set;

public class Capitulo implements Comparable<Capitulo>{

	private int id;
	private int numCap;
	private String titulo;
	private String descripcion;
	private Set<String> enlaces;
	
	public Capitulo() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumCap() {
		return numCap;
	}

	public void setNumCap(int numCap) {
		this.numCap = numCap;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<String> getEnlaces() {
		return enlaces;
	}

	public void setEnlaces(Set<String> enlaces) {
		this.enlaces = enlaces;
	}
	
	@Override
	public boolean equals(Object o){
		Capitulo c = (Capitulo) o;
		return c.getTitulo().equals(titulo) && c.getDescripcion().equals(descripcion);
	}
	
	@Override
	public int compareTo(Capitulo c){
		return titulo.compareTo(c.getTitulo());
	}
	
	@Override
	public int hashCode(){
		String newHash = titulo+descripcion;
		return newHash.hashCode();
	}
}
