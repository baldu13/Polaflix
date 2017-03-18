package es.polaflix.domain;

import java.util.Set;

public abstract class Usuario implements Comparable<Usuario>{
	private int id;
	private String alias;
	private String password;
	private String cuentaBancaria;
	private Set<Serie> seriesTerminadas;
	private Set<Serie> seriesPendientes;
	private Set<SerieEmpezada> seriesEmpezadas;
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
