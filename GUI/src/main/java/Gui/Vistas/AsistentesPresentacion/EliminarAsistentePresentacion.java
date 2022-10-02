package Gui.Vistas.AsistentesPresentacion;

import Congreso.Presentacion;
import Congreso.Registro;
import javafx.stage.Stage;

public class EliminarAsistentePresentacion extends ModificarAsistentePresentacion {
	
    /**	@brief Constructor de la vista
     * 
     * Carga el archivo fxml que corresponde para abrirlo como ventana.
     * @param registro Es el atributo de clase Registro que contiene todas las colecciones.
     * @param presentacion Es el atributo de clase Presentacion que contiene toda la informacion de la presentacion.
     * @param stage Contiene el escenario.
     * */
	public EliminarAsistentePresentacion(Registro registro, Presentacion presentacion, Stage stage) {
		super(registro, presentacion, stage, "/vistas/eliminarAsistentePresentacion.fxml");
		inicializar();
	}
	
}
