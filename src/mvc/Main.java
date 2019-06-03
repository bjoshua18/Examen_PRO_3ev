package mvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import modelo.dao.AccesoDatos;

public class Main extends Application {

	public static void main(String[] args) {
		AccesoDatos.startConnection("localhost", "laboratorio", "root", "");
		Application.launch(Main.class, args);
		AccesoDatos.closeConnection();
	}

	@Override
	public void start(javafx.stage.Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Vista.fxml"));
	    Parent root = loader.load();

	    Controller controller = loader.getController();
	    
	    //Set Data to FXML through controller
	    controller.cargarCientificos();
	    controller.cargarProyectos();
        
        stage.setTitle("Databases");
        stage.setScene(new Scene(root, 500, 550));
        stage.show();
	}

}
