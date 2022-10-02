package Gui.Vistas.AsistentesPresentacion;

import java.util.Iterator;

import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Registro;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class AgregarAsistentePresentacion extends ModificarAsistentePresentacion {
	
	public AgregarAsistentePresentacion(Registro registro, Presentacion presentacion, Stage stage) {
		super(registro, presentacion, stage, "/vistas/agregarAsistentePresentacion.fxml");
		inicializar();
	}
	
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
