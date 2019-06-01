package control;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.Asignacion;
import modelo.Cientifico;
import modelo.Proyecto;
import modelo.dao.AccesoDatos;

public class Main {

	public static void main(String[] args) {
		AccesoDatos.startConnection("localhost", "laboratorio", "root", "");
		
		// Ejercicio 1
		
		/*
		 * 1.- Abrir la consola de comandos
		 * 2.- Dentro de la consola, ir a la ruta donde se encuentra el fichero laboratorio.sql
		 * 3.- Importar el fichero con el comando: mysql -u <dbUser> -p < laboratorio.sql
		 * Nota: '-p' es opcional según si la cuenta tiene o no password
		 * */
		
		// Ejercicio 4
		
		//Cientifico.insertFileIntoTable("ficheros/cientificos.txt", ",");
		//Proyecto.insertFileIntoTable("ficheros/proyectos.txt", ",");
		//Asignacion.insertFileIntoTable("ficheros/asignaciones.txt", ",");
		
		// Ejercicio 6
		
		ArrayList<String[]> arrayCientificos = AccesoDatos.getListFromTable("cientificos");
		ArrayList<String[]> arrayProyectos = AccesoDatos.getListFromTable("proyectos");
		ArrayList<String[]> arrayAsignaciones = AccesoDatos.getListFromTable("asignaciones");
		
		// Ejercicio 7
		
		ArrayList<Cientifico> cientificos1 = Cientifico.getCientificosFromList(arrayCientificos);
		ArrayList<Cientifico> cientificos2 = Cientifico.getCientificosFromTable("cientificos");
		ArrayList<Cientifico> cientificos3 = Cientifico.getCientificosFromFile("ficheros/cientificos.txt", ",");
		
		ArrayList<Proyecto> proyectos1 = Proyecto.getProyectosFromList(arrayProyectos);
		ArrayList<Proyecto> proyectos2 = Proyecto.getProyectosFromTable("proyectos");
		ArrayList<Proyecto> proyectos3 = Proyecto.getProyectosFromFile("ficheros/proyectos.txt", ",");
		
		ArrayList<Asignacion> asignaciones1 = Asignacion.getAsignacionesFromList(arrayAsignaciones);
		ArrayList<Asignacion> asignaciones2 = Asignacion.getAsignacionesFromTable("asignaciones");
		ArrayList<Asignacion> asignaciones3 = Asignacion.getAsignacionesFromFile("ficheros/asignaciones.txt", ",");
		
		// Ejercicio 8
		
		HashMap<String, Cientifico> mapaCientificos = Cientifico.getMapCientificosFromList(arrayCientificos);
		HashMap<String, Proyecto> mapaProyectos = Proyecto.getMapProyectosFromList(arrayProyectos);
		
		// Ejercicio 9
		
		/*
		 * 1.- Abrir la consola de comandos
		 * 2.- Dentro de la consola, ir a la ruta donde se encuentra la carpeta sql, entra en dicha carpeta
		 * 3.- Exportar la db laboratorio con el comando: mysqldump -u <dbUser> -p laboratorio > laboratorioResultado.sql
		 * Nota: '-p' es opcional según si la cuenta tiene o no password
		 * */
		

		AccesoDatos.closeConnection();
		System.out.println("Fin del programa");
	}

}
