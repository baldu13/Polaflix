package es.polaflix.domain;

import java.util.Set;

public class SerieEmpezada implements Comparable<SerieEmpezada>{

	private int id;
	private Serie serie;
	private Set<Capitulo> capitulos;
	
	public SerieEmpezada() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public Set<Capitulo> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(Set<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}
	
	@Override
	public int compareTo(SerieEmpezada se){
		return id-se.getId();
	}
	
	@Override
	public boolean equals(Object o){
		return this.compareTo((SerieEmpezada) o) == 0;
	}
}
