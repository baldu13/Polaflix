package es.polaflix.domain;

public class CapituloFactura implements Comparable<CapituloFactura>{
	private int id;
	private double precio;
	private int dia;
	private int mes;
	private int anyo;
	private Capitulo capitulo;
	private Serie serie;
	
	public CapituloFactura() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
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

	public Capitulo getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(Capitulo capitulo) {
		this.capitulo = capitulo;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}
	
	@Override
	public int compareTo(CapituloFactura cf){
		return id-cf.getId();
	}
	
	@Override
	public boolean equals(Object o){
		CapituloFactura cf = (CapituloFactura) o;
		return cf.getId()==id;
	}
}
