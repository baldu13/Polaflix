package es.polaflix.domain;

import javax.persistence.*;

@Entity
public class TipoSerie implements Comparable<TipoSerie>{
	
	@Id
	@GeneratedValue
	private int id;
	private String nombre;
	private double precioCap;
	
	public TipoSerie() {}

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

	public double getPrecioCap() {
		return precioCap;
	}

	public void setPrecioCap(double precioCap) {
		this.precioCap = precioCap;
	}
	
	@Override
	public boolean equals(Object o){
		TipoSerie ts = (TipoSerie) o;
		return id == ts.getId();
	}
	
	@Override
	public int compareTo(TipoSerie ts){
		return nombre.compareTo(ts.getNombre());
	}
}
