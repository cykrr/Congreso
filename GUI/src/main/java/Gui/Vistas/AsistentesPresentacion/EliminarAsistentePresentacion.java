package Gui.Vistas.AsistentesPresentacion;

import Congreso.Presentacion;
import Congreso.Registro;

public class EliminarAsistentePresentacion extends ModificarAsistentePresentacion {
	
	public EliminarAsistentePresentacion(Registro registro, Presentacion presentacion) {
		super(registro, presentacion, "/vistas/eliminarAsistentePresentacion.fxml");
		inicializar();
	}
	
}
