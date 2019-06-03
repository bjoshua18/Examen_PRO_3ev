package mvc;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import modelo.Cientifico;
import modelo.Proyecto;
import modelo.dao.AccesoDatos;

public class Controller {
	@FXML private ComboBox<Cientifico> boxCientificos;
	@FXML private ListView<Proyecto> listProyectos;
	@FXML private ComboBox<Proyecto> boxProyectos;
	@FXML private ListView<Cientifico> listCientificos;
	
	public void cargarCientificos() {
		boxCientificos.getItems().clear();
		boxCientificos.getItems().addAll(Cientifico.getCientificosFromTable("cientificos"));
	}

	public void cargarProyectos() {
		boxProyectos.getItems().clear();
		boxProyectos.getItems().addAll(Proyecto.getProyectosFromTable("proyectos"));
	}
	
	public void cargarProyectosDeCientifico() {
		listProyectos.getItems().clear();
		String dniCient = boxCientificos.getValue().getDni();
		listProyectos.getItems().addAll(AccesoDatos.getProyectosFromCientifico(dniCient));
	}
	
	public void cargarCientificosDeProyecto() {
		listCientificos.getItems().clear();
		String idProyecto = boxProyectos.getValue().getId();
		listCientificos.getItems().addAll(AccesoDatos.getCientificosFromProyecto(idProyecto));
	}

}
