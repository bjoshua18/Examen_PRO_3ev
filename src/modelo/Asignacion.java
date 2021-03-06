package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import control.LecturaEscritura;
import modelo.dao.AccesoDatos;

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
	
	/**
	 * @param campos Se le pasa un array con todos los atributos necesarios
	 */
	public Asignacion(String[] campos) {
		this(campos[0], campos[1]);
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

	/**
	 * <h2>Inserta un fichero txt en la tabla asignaciones</h2>
	 * @param conexion La conexión con la base de datos
	 * @param ruta La ruta del fichero a insertar
	 * @param separador Una cadena que representa el separador entre los datos de un mismo registro en el fichero
	 * */
	public static void insertFileIntoTable(String ruta, String separador) {
		ArrayList<String[]> lista = LecturaEscritura.getRegistersFromFile(ruta, separador);

		lista.forEach((registro) ->	insertRegister(registro));
		
		System.out.println("Fin de inserción de datos.");
	}
	
	/**
	 * <h2>Inserta un registro de tipo {@code String[]} en la tabla asignaciones</h2>
	 * @param registro Los datos de un cientifico en un {@code String[]}
	 * @param conexion La conexion con la base de datos
	 * */
	private static void insertRegister(String[] registro) {
		Connection conexion = AccesoDatos.getConnection();
		String sql = "INSERT INTO asignaciones (cientifico, proyecto) VALUES (?,?)";
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setString(1, registro[0]);
			pstmt.setString(2, registro[1]);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException al insertar datos.");
		}
	}

	/**
	 * <p>Obtener una lista de objetos {@code Asignacion} de una lista de {@code ArrayList<String[]>}</p>
	 * @param lista La lista que contiene los registros de las asignaciones
	 * @return La lista con todos los objetos {@code Asignacion}
	 * */
	public static ArrayList<Asignacion> getAsignacionesFromList(ArrayList<String[]> lista) {
		ArrayList<Asignacion> asignaciones = new ArrayList<Asignacion>();
		lista.forEach(registro -> asignaciones.add(new Asignacion(registro)));
		return asignaciones;
	}
	
	/**
	 * <p>Obtener una lista de tipo {@code Asignacion} dado el nombre de una tabla</p>
	 * @param tabla El nombre de la tabla que contiene los datos
	 * @return La lista con todos los objetos {@code Asignacion}
	 * */
	public static ArrayList<Asignacion> getAsignacionesFromTable(String tabla) {
		ArrayList<String[]> lista = AccesoDatos.getListFromTable(tabla);
		ArrayList<Asignacion> asignaciones = getAsignacionesFromList(lista);
		return asignaciones;
	}
	
	/**
	 * <p>Obtener una lista de tipo {@code Asignacion} dada la ruta de un fichero y un separador</p>
	 * @param ruta La ruta del fichero que contiene los datos
	 * @param separador Una cadena que representa el separador entre los datos de un mismo registro en el fichero
	 * @return La lista con todos los objetos {@code Asignacion}
	 * */
	public static ArrayList<Asignacion> getAsignacionesFromFile(String ruta, String separador) {
		ArrayList<String[]> lista = LecturaEscritura.getRegistersFromFile(ruta, separador);
		ArrayList<Asignacion> asignaciones = getAsignacionesFromList(lista);
		return asignaciones;
	}
}
