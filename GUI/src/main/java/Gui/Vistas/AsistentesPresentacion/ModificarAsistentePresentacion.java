package Gui.Vistas.AsistentesPresentacion;

import java.util.Iterator;

import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Registro;
import Gui.Alerta;
import Gui.Vistas.PopUp;
import Gui.Vistas.PopUp.PopAble;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ModificarAsistentePresentacion extends PopUp implements PopAble {
	
	@FXML private ComboBox<Persona> comboAsistentes;
	
	private Registro registro;
	private Persona asistente;
	private Presentacion presentacion;
	
    public ModificarAsistentePresentacion(Registro registro, Presentacion presentacion, Stage stage, String resourceName) {
        super(stage, resourceName);
        this.registro = registro;
        this.presentacion = presentacion;
    }
    
	public void inicializar() {
		ObservableList<Persona> itemsAsistentes = comboAsistentes.getItems();
		Iterator<Persona> iteratorAsistentes = registro.getAsistentes();
		
        while(iteratorAsistentes.hasNext()) {
        	Persona tmp = iteratorAsistentes.next();
        	if(presentacion.contieneAsistente(tmp))
        		itemsAsistentes.add(tmp);
        }
	}
	
	public ComboBox<Persona> getBoxAsistentes() {
		return comboAsistentes;
	}

	@Override
	public boolean guardar() {
		asistente = comboAsistentes.getValue();
		if(asistente == null) {
			Alerta.mostrarAlertaAdvertencia("Seleccione un asistente");
			return false;
		}
		
		return true;	
	}

	@Override
	public Object getValue() {
		return asistente;
	}
	
	public Registro getRegistro() {
		return registro;
	}
	
	public Presentacion getPresentacion() {
		return presentacion;
	}
	
}
