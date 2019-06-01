package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import control.LecturaEscritura;
import modelo.dao.AccesoDatos;

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
	
	/**
	 * @param campos Se le pasa un array con todos los atributos necesarios
	 */
	public Cientifico(String[] campos) {
		this(campos[0], campos[1]);
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

	/**
	 * <h2>Inserta un fichero txt en la tabla cientificos</h2>
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
	 * <h2>Inserta un registro de tipo {@code String[]} en la tabla cientificos</h2>
	 * @param registro Los datos de un cientifico en un {@code String[]}
	 * @param conexion La conexion con la base de datos
	 * */
	private static void insertRegister(String[] registro) {
		Connection conexion = AccesoDatos.getConnection();
		String sql = "INSERT INTO cientificos (dni, nombre) VALUES (?,?)";
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setString(1, registro[0]);
			pstmt.setString(2, registro[1]);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException al insertar datos.");
		}
	}
}
