package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import control.LecturaEscritura;
import modelo.dao.AccesoDatos;

public class Proyecto {
	private String id;
	private String nombre;
	private int horas;
	
	/**
	 * @param id
	 * @param nombre
	 * @param horas
	 */
	public Proyecto(String id, String nombre, int horas) {
		this.id = id;
		this.nombre = nombre;
		this.horas = horas;
	}
	
	/**
	 * @param campos Se le pasa un array con todos los atributos necesarios
	 */
	public Proyecto(String[] campos) {
		this(campos[0], campos[1], Integer.parseInt(campos[2]));
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}

	/**
	 * <h2>Inserta un fichero txt en la tabla proyectos</h2>
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
	 * <h2>Inserta un registro de tipo {@code String[]} en la tabla Proyectos</h2>
	 * @param registro Los datos de un cientifico en un {@code String[]}
	 * @param conexion La conexion con la base de datos
	 * */
	private static void insertRegister(String[] registro) {
		Connection conexion = AccesoDatos.getConnection();
		String sql = "INSERT INTO proyectos (id, nombre, horas) VALUES (?,?,?)";
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setString(1, registro[0]);
			pstmt.setString(2, registro[1]);
			pstmt.setInt(3, Integer.parseInt(registro[2]));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException al insertar datos.");
		}
	}
}
