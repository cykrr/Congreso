package Gui.Vistas.AsistentesPresentacion;

import Congreso.Presentacion;
import Congreso.Registro;
import javafx.stage.Stage;

public class EliminarAsistentePresentacion extends ModificarAsistentePresentacion {
	
	public EliminarAsistentePresentacion(Registro registro, Presentacion presentacion, Stage stage) {
		super(registro, presentacion, stage, "/vistas/eliminarAsistentePresentacion.fxml");
		inicializar();
	}
	
}
