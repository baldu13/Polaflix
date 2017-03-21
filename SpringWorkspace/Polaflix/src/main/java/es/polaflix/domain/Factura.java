package es.polaflix.domain;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Factura implements Comparable<Factura>{
	@Id
	@GeneratedValue
	private int id;
	private int mes;
	private int anyo;
	@OneToMany
	private Set<CapituloFactura> capitulos;
	
	public Factura() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public Set<CapituloFactura> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(Set<CapituloFactura> capitulos) {
		this.capitulos = capitulos;
	}
	
	@Override
	public boolean equals(Object o){
		Factura f = (Factura) o;
		return f.getId()==id;
	}
	
	@Override
	public int compareTo(Factura f){
		int compare = anyo-f.getAnyo();
		if(compare == 0){
			compare = mes-f.getMes();
		}
		return compare;
	}
	
	@Override
	public int hashCode(){
		return hashCode(); //Provisional
	}
}
