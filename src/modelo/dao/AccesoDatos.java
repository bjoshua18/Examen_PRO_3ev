package modelo.dao;

import java.sql.Connection;

import control.BaseDatos;

public class AccesoDatos {

	private static Connection conexion;
	private static BaseDatos bd;
	
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
}
