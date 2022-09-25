package Gui.Vistas.VPersona;

import java.io.IOException;
import java.util.LinkedList;

import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Registro;
import Gui.Alerta;
import Gui.EventoPersona;
import Gui.EventoPresentacion;
import Gui.Vistas.PopUp;
import Gui.Vistas.Vista;
import Gui.Vistas.Asistente.LeerAsistente;
import Gui.Vistas.Dashboard.Dashboard;
import Gui.Vistas.LeerPresentacion.LeerPresentacion;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VPersona extends Vista implements Vista.Manipulable {
    @FXML Text txtNombre, txtEdad, txtFono, txtCorreo;
    
    private Persona p;
    
    public VPersona(Persona p, Registro registro, Stage stage, Dashboard dashboard) {
        super(registro, stage, dashboard, "/vistas/vPersona.fxml");
        this.p = p;
    	
        txtNombre.setText(p.getNombre());
        txtEdad.setText(Integer.toString(p.getEdad()));
        txtFono.setText(Long.toString(p.getFono()));
        txtCorreo.setText(p.getCorreo());
    }

	@Override
	public void editar() {
    	LeerAsistente la = new LeerAsistente(getRegistro(), p);
    	la.setHeader("Editando asistente");
    	
    	PopUp popup = new PopUp(getStage(), la);
    	popup.setTitle("Editar asistente");
            
        Persona retorno = (Persona)popup.showDialog();
        if (retorno != null) {
        	getRegistro().editarAsistente(p, retorno);
            getDashboard().fireEvent(new EventoPersona(EventoPersona.EDITAR_PERSONA, p, retorno));
        }		
	}

	@Override
	public void eliminar() {
    	boolean opcion = Alerta.mostrarAlertaConfirmacion("¿Desea eliminar al asistente \"" + p.getNombre() + "\"?");
    	if(opcion) {
    		getRegistro().eliminarAsistente(p);
    		getDashboard().fireEvent(new EventoPersona(EventoPersona.ELIMINAR_PERSONA, p));
    	}
	}
}