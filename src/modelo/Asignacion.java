package modelo;

public class Asignacion {
	private String cientifico;
	private String proyecto;
	
	/**
	 * @param cientifico
	 * @param proyecto
	 */
	public Asignacion(String cientifico, String proyecto) {
		this.cientifico = cientifico;
		this.proyecto = proyecto;
	}

	public String getCientifico() {
		return cientifico;
	}

	public void setCientifico(String cientifico) {
		this.cientifico = cientifico;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
}
