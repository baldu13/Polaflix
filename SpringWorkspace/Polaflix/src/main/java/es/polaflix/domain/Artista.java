package es.polaflix.domain;

public abstract class Artista implements Comparable<Artista>{

	private int id;
	private String nombre;
	private String apellido;
	
	public Artista(){}

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	@Override
	public String toString(){
		return nombre+" "+apellido;
	}
	
	@Override
	public boolean equals(Object o){
		Artista a = (Artista) o;
		return a.getNombre().equals(nombre) && a.getApellido().equals(apellido);
	}
	
	@Override
	public int compareTo(Artista a){
		int compare = apellido.compareTo(a.getApellido());
		if(compare == 0){ //Si su apellido es igual, comparamos por nombre
			compare = nombre.compareTo(a.getNombre());
		}
		return compare;
	}
	
	@Override
	public int hashCode(){
		String newHash = nombre+apellido;
		return newHash.hashCode();
	}
	
}
