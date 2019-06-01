package control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <p>Se encarga de leer y escribir ficheros</p>
 * */
public class LecturaEscritura {
	/**
	 * <h2>Obtener Registros desde Fichero</h2>
	 * @param ruta Una cadena con la ruta del fichero del que quieres obtener los datos
	 * @param separador Una cadena que representa el separador entre los datos de un mismo registro en el fichero
	 * @return La lista con todos los registros de tipo {@code ArrayList<String[]>} 
	 * */
	public static ArrayList<String[]> getRegistersFromFile(String ruta, String separador) {
		ArrayList<String[]> lista = new ArrayList<String[]>();
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(ruta));
			String registro;
			
			while ((registro = fichero.readLine()) != null)
				lista.add(registro.split(separador));

				fichero.close();
		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");
		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
		
		return lista;
	}
}
