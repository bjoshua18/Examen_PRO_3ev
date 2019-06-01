package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <h2>BaseDatos</h2>
 * <p>Esta clase se conecta a MySQL</p>
 * */
public class BaseDatos {

	private String host;
	private String dbName;
	private String dbUser;
	private String dbPass;
	private Connection conexion;
	
	/**
	 * <h2>BaseDatos constructor</h2>
	 * @param host Es el host donde est� la DB que te quieres conectar
	 * @param dbName El nombre de la DB
	 * @param dbUser El nombre de usuario para conectarte a DB
	 * @param dbPass La contrase�a para conectarte a DB
	 * */
	public BaseDatos(String host, String dbName, String dbUser, String dbPass) {
		this.host = host;
		this.dbName = dbName;
		this.dbUser = dbUser;
		this.dbPass = dbPass;
		this.conexion = crearConexion();
		System.out.println("Conexi�n iniciada");
	}
	
	/**
	 * <h1>Crea la conexi�n entre BaseDatos y MySQL mediante el driver JDBC</h1
	 * @return Devuelve un objeto de tipo Connection 
	 * */
	private Connection crearConexion() {
		String ruta = "jdbc:mysql://" + this.host + "/" + this.dbName + "?&user=" + this.dbUser + "&password=" + this.dbPass + "&serverTimezone=UTC";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(ruta);
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		return null;
	}

	public BaseDatos() {
		super();

	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPass() {
		return dbPass;
	}

	public void setDbPass(String dbPass) {
		this.dbPass = dbPass;
	}

	/**
	 * <p>Cierra la conexi�n con la base de datos</p>
	 * */
	public void closeConnection() {
		try {
			this.conexion.close();
			System.out.println("Conexi�n finalizada");
		} catch (SQLException e) {
			System.out.println("Fallo al cerrar la conexi�n.");
		}
	}
}
