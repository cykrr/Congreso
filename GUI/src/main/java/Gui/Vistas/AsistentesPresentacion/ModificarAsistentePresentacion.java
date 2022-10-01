package Gui.Vistas.AsistentesPresentacion;

import java.io.IOException;
import java.util.Iterator;

import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Registro;
import Gui.Alerta;
import Gui.Vistas.PopUp.PopAble;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ModificarAsistentePresentacion extends GridPane implements PopAble {
	
	@FXML private Text txtHeader;
	@FXML private ComboBox<Persona> comboAsistentes;
	
	private Registro registro;
	private Persona asistente;
	private Presentacion presentacion;
	
    public ModificarAsistentePresentacion(Registro registro, Presentacion presentacion, String fileName)  {
        super();
        this.registro = registro;
        this.presentacion = presentacion;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fileName));
        fxmlLoader.setController(this);
        Node n = null;

        try {
            n = fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.getChildren().add(n);
    }
    
	public void inicializar(boolean agregando) {
		ObservableList<Persona> itemsAsistentes = comboAsistentes.getItems();
		Iterator<Persona> iteratorAsistentes = registro.getAsistentes();
		
        while(iteratorAsistentes.hasNext()) {
        	Persona tmp = iteratorAsistentes.next();
        	if(presentacion.contieneAsistente(tmp) != agregando)
        		itemsAsistentes.add(tmp);
        }
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
    
	public void setHeader(String text) {
		txtHeader.setText(text);
	}
	
}
