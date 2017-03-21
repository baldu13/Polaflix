package es.polaflix.domain;

import java.util.Set;

import javax.persistence.*;

@Entity
public class SerieEmpezada implements Comparable<SerieEmpezada>{

	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	private Serie serie;
	@ManyToMany
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
