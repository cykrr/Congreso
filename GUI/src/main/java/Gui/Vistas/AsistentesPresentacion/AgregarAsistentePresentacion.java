package Gui.Vistas.AsistentesPresentacion;

import Congreso.Presentacion;
import Congreso.Registro;

public class AgregarAsistentePresentacion extends ModificarAsistentePresentacion {
	
	public AgregarAsistentePresentacion(Registro registro, Presentacion presentacion) {
		super(registro, presentacion, "/vistas/agregarAsistentePresentacion.fxml");
		inicializar(true);
	}
	
}
