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
	
    /**	@brief Constructor de la vista
     * 
     * Carga el archivo fxml que corresponde para abrirlo como ventana.
     * @param registro Es el atributo de clase Registro que contiene todos los asistentes.
     * @param presentacion Es la presentacion a la que modificaremos sus asistentes.
     * @param stage Es el escenario de la vista.
     * @param resourseName Es el nombre del recurso a usar.
     * */
    public ModificarAsistentePresentacion(Registro registro, Presentacion presentacion, Stage stage, String resourceName) {
        super(stage, resourceName);
        this.registro = registro;
        this.presentacion = presentacion;
    }
    
    /**	Inicia el comoBox que contiene a los asistentes de la presentacion cuando se abre la ventana. */
	public void inicializar() {
		ObservableList<Persona> itemsAsistentes = comboAsistentes.getItems();
		Iterator<Persona> iteratorAsistentes = registro.getAsistentes();
		
        while(iteratorAsistentes.hasNext()) {
        	Persona tmp = iteratorAsistentes.next();
        	if(presentacion.contieneAsistente(tmp))
        		itemsAsistentes.add(tmp);
        }
	}
	
	/**
	 * @return El combobox de asistentes de la clase como ComboBox
	 * */
	public ComboBox<Persona> getBoxAsistentes() {
		return comboAsistentes;
	}
	
	/** Retorna true si se ha seleccionado un asistente del comboBox y retorna false en caso contrario */
	@Override
	public boolean guardar() {
		asistente = comboAsistentes.getValue();
		if(asistente == null) {
			Alerta.mostrarAlertaAdvertencia("Seleccione un asistente");
			return false;
		}
		
		return true;	
	}
	
	/**
	 * @return El asistente de clase Asistente casteado en Object.
	 * */
	@Override
	public Object getValue() {
		return asistente;
	}
	
	/**
	 * @return El registro de clase Registro.
	 * */
	public Registro getRegistro() {
		return registro;
	}
	
	/**
	 * @return El presentacion de clase Presentacion.
	 * */
	public Presentacion getPresentacion() {
		return presentacion;
	}
	
}
