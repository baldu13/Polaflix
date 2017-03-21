package es.polaflix.domain;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Temporada implements Comparable<Temporada>{

	@Id
	@GeneratedValue
	private int id;
	private int numTemp;
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Capitulo> capitulos;
	
	public Temporada() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumTemp() {
		return numTemp;
	}

	public void setNumTemp(int numTemp) {
		this.numTemp = numTemp;
	}

	public Set<Capitulo> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(Set<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}
	
	@Override
	public int compareTo(Temporada t){
		return numTemp-t.getNumTemp();
	}
	
	@Override
	public boolean equals(Object o){
		Temporada t = (Temporada) o;
		return id == t.getId();
	}
}
