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
	
	/**
	 * <p>Obtener una lista de objetos {@code Proyecto} de una lista de {@code ArrayList<String[]>}</p>
	 * @param lista La lista que contiene los registros de los proyectos
	 * @return La lista con todos los objetos {@code Proyecto}
	 * */
	public static ArrayList<Proyecto> getProyectosFromList(ArrayList<String[]> lista) {
		ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
		lista.forEach(registro -> proyectos.add(new Proyecto(registro)));
		return proyectos;
	}
	
	/**
	 * <p>Obtener una lista de tipo {@code Proyecto} dado el nombre de una tabla</p>
	 * @param tabla El nombre de la tabla que contiene los datos
	 * @return La lista con todos los objetos {@code Proyecto}
	 * */
	public static ArrayList<Proyecto> getProyectosFromTable(String tabla) {
		ArrayList<String[]> lista = AccesoDatos.getListFromTable(tabla);
		ArrayList<Proyecto> proyectos = getProyectosFromList(lista);
		return proyectos;
	}
	
	/**
	 * <p>Obtener una lista de tipo {@code Proyecto} dada la ruta de un fichero y un separador</p>
	 * @param ruta La ruta del fichero que contiene los datos
	 * @param separador Una cadena que representa el separador entre los datos de un mismo registro en el fichero
	 * @return La lista con todos los objetos {@code Proyecto}
	 * */
	public static ArrayList<Proyecto> getProyectosFromFile(String ruta, String separador) {
		ArrayList<String[]> lista = LecturaEscritura.getRegistersFromFile(ruta, separador);
		ArrayList<Proyecto> proyectos = getProyectosFromList(lista);
		return proyectos;
	}
}
