package Gui.Vistas.AsistentesPresentacion;

import java.util.Iterator;

import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Registro;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class AgregarAsistentePresentacion extends ModificarAsistentePresentacion {
	
    /**	@brief Constructor de la vista
     * 
     * Carga el archivo fxml que corresponde para abrirlo como ventana.
     * @param registro Es el atributo de clase Registro que contiene todas las colecciones.
     * @param presentacion Es el atributo de clase Presentacion que contiene toda la informacion de la presentacion.
     * @param stage Contiene el escenario.
     * */
	public AgregarAsistentePresentacion(Registro registro, Presentacion presentacion, Stage stage) {
		super(registro, presentacion, stage, "/vistas/agregarAsistentePresentacion.fxml");
		inicializar();
	}
	
    /**	Inicia el comoBox que contiene a los asistentes que no estan en la presentacion cuando se abre la ventana. */
	public void inicializar() {
		ObservableList<Persona> itemsAsistentes = getBoxAsistentes().getItems();
		Iterator<Persona> iteratorAsistentes = getRegistro().getAsistentes();
		
        while(iteratorAsistentes.hasNext()) {
        	Persona tmp = iteratorAsistentes.next();
        	if(getPresentacion().contieneAsistente(tmp) == false)
        		itemsAsistentes.add(tmp);
        }
	}
	
}
