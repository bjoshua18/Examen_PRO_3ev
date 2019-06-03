package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import control.BaseDatos;
import modelo.Cientifico;
import modelo.Proyecto;

public class AccesoDatos {

	private static Connection conexion;
	private static BaseDatos bd;

	/**
	 * <h2>Obtener Lista desde una Tabla</h2>
	 * <p>Obtiene un {@code ArrayList<String[]} con todos los registros de una tabla dada</p>
	 * @param conexion La conexión con la base de datos
	 * @param tabla El nombre de la tabla
	 * @return El {@code ArrayList<String[]>} con todos los registros de la tabla
	 * */
	public static ArrayList<String[]> getListFromTable(String tabla) {
		ArrayList<String[]> lista = new ArrayList<String[]>();
		
		try {
			ResultSet rS = selectAllFromTable(tabla);
			ResultSetMetaData mD = rS.getMetaData();
			
			while (rS.next()) {
				String[] registro = new String[mD.getColumnCount()];
				for (int i = 0; i < registro.length; i++)
					registro[i] = rS.getString(i + 1);
				lista.add(registro);
			}

		} catch (SQLException e) {
			System.out.println("Algo falla con SQL...");
		} catch (NullPointerException e) {
			System.out.println("Ha ocurrido el típico NullPointerException...");
		}
		
		return lista;
	}
	
	/**
	 * <h2>Obtener ResultSet de una Tabla</h2>
	 * <p>Obtiene el {@code ResultSet} de una query tipo SELECT a una tabla dada</p>
	 * @param conexion La conexión con la base de datos
	 * @param tabla El nombre de la tabla
	 * @return El {@code ResultSet} de la query
	 * */
	private static ResultSet selectAllFromTable(String tabla) throws SQLException {
		String sql = "SELECT * FROM " + tabla;
		PreparedStatement pstmt = conexion.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	/**
	 * @param host Es el host donde está la DB que te quieres conectar
	 * @param dbName El nombre de la DB
	 * @param dbUser El nombre de usuario para conectarte a DB
	 * @param dbPass La contraseña para conectarte a DB
	 * @see BaseDatos
	 * */
	public static void startConnection(String host, String dbName, String dbUser, String dbPassword) {
		bd = new BaseDatos(host, dbName, dbUser, dbPassword);
		conexion = bd.getConexion();
	}
	
	/**
	 *<p>Obtener el atributo estático {@code conexion} de {@code AccesoDatos} que mantiene la conexión con la DB</p>
	 * */
	public static Connection getConnection() {
		return conexion;
	}

	/**
	 * <p>Cierra la conexión con la base de datos</p>
	 * */
	public static void closeConnection() {
		bd.closeConnection();
	}
	
	public static ArrayList<Proyecto> getProyectosFromCientifico(String dniCient) {
		ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
		String sql = "SELECT * FROM proyectos AS p INNER JOIN asignaciones AS a ON a.proyecto = p.id "
				+ "WHERE a.cientifico like \"" + dniCient + "\"";
		try {
			ResultSet rS = getResultSet(sql);
			ResultSetMetaData mD = rS.getMetaData();
			
			while (rS.next()) {
				String[] registro = new String[mD.getColumnCount()];
				for (int i = 0; i < registro.length; i++)
					registro[i] = rS.getString(i + 1);
				proyectos.add(new Proyecto(registro));
			}

		} catch (SQLException e) {
			System.out.println("Algo falla con SQL...");
		} catch (NullPointerException e) {
			System.out.println("Ha ocurrido el típico NullPointerException...");
		}
		
		return proyectos;
	}

	public static ArrayList<Cientifico> getCientificosFromProyecto(String idProyecto) {
		ArrayList<Cientifico> cientificos = new ArrayList<Cientifico>();
		String sql = "SELECT * FROM cientificos AS c INNER JOIN asignaciones AS a ON a.cientifico = c.dni "
				+ "WHERE a.proyecto like \"" + idProyecto + "\"";
		try {
			ResultSet rS = getResultSet(sql);
			ResultSetMetaData mD = rS.getMetaData();
			
			while (rS.next()) {
				String[] registro = new String[mD.getColumnCount()];
				for (int i = 0; i < registro.length; i++)
					registro[i] = rS.getString(i + 1);
				cientificos.add(new Cientifico(registro));
			}

		} catch (SQLException e) {
			System.out.println("Algo falla con SQL...");
		} catch (NullPointerException e) {
			System.out.println("Ha ocurrido el típico NullPointerException...");
		}
		return cientificos;
	}

	private static ResultSet getResultSet(String sql) throws SQLException {
		PreparedStatement pstmt = conexion.prepareStatement(sql);
		return pstmt.executeQuery();
	}
}
