package modelo;

public class Cientifico {
	private String dni;
	private String nombre;
	
	/**
	 * @param dni
	 * @param nombre
	 */
	public Cientifico(String dni, String nombre) {
		this.dni = dni;
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
