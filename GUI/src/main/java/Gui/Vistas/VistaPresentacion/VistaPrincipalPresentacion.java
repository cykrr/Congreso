package Gui.Vistas.VistaPresentacion;

import java.util.LinkedList;

import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Registro;
import Gui.Alerta;
import Gui.EventoPresentacion;
import Gui.Vistas.PopUp;
import Gui.Vistas.VistaPrincipal;
import Gui.Vistas.AsistentesPresentacion.AgregarAsistentePresentacion;
import Gui.Vistas.AsistentesPresentacion.EliminarAsistentePresentacion;
import Gui.Vistas.Dashboard.Dashboard;
import Gui.Vistas.LeerPresentacion.LeerPresentacion;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VistaPrincipalPresentacion extends VistaPrincipal implements VistaPrincipal.Manipulable {
    @FXML Text txtNombre, txtExpositor, txtDescripcion, txtFecha, 
    		   txtHora, txtDuracion, txtTotalAsistentes, txtAsistentes;
    @FXML ImageView imgButtonAgregarAsistente, imgButtonEliminarAsistente;
    
    private Presentacion p;
    
    public VistaPrincipalPresentacion(Presentacion p, Registro registro, Stage stage, Dashboard dashboard) {
        super(registro, stage, dashboard, "/vistas/VistaPrincipalPresentacion.fxml");      
        this.p = p;
        
        txtNombre.setText(p.getNombre());
        txtExpositor.setText(p.getExpositor().getNombre());
        txtDescripcion.setText(p.getDescripcion());
        txtFecha.setText(p.getStringFecha());
        txtHora.setText(p.getStringHora());
        txtDuracion.setText(Integer.toString(p.getDuracion()) + " minutos");
        actualizarAsistentes();
        
        imgButtonAgregarAsistente.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                agregarAsistente();
                event.consume();
            }
        });
        
        imgButtonEliminarAsistente.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                eliminarAsistente();
                event.consume();
            }
        });  
    }
	
	private void agregarAsistente() {
    	AgregarAsistentePresentacion ap = new AgregarAsistentePresentacion(getRegistro(), p);
    	ap.setHeader("Agregando asistente a presentación " + p.getNombre());
    	
    	PopUp popup = new PopUp(getStage(), ap);
    	popup.setTitle("Agregar asistente a presentación");
        
        Persona retorno = (Persona) popup.showDialog();	
        if(retorno != null) {
        	p.agregarAsistente(retorno);
        	getDashboard().fireEvent(new EventoPresentacion(EventoPresentacion.MODIFICAR_ASISTENTES, p));
        }
	}
	
	private void eliminarAsistente() {
    	EliminarAsistentePresentacion ep = new EliminarAsistentePresentacion(getRegistro(), p);
    	ep.setHeader("Eliminando asistente de presentación " + p.getNombre());
    	
    	PopUp popup = new PopUp(getStage(), ep);
    	popup.setTitle("Eliminar asistente de presentación");
        
        Persona retorno = (Persona) popup.showDialog();	
        if(retorno != null) {
        	p.eliminarAsistente(retorno);
        	getDashboard().fireEvent(new EventoPresentacion(EventoPresentacion.MODIFICAR_ASISTENTES, p));
        }	
	}
    
    public void actualizarAsistentes() {
        LinkedList<Persona> asistentes = p.getAsistentes();
        txtTotalAsistentes.setText(Integer.toString(asistentes.size()));
        
        if(asistentes.size() == 0) {
        	txtAsistentes.setText("Ninguno");
        } else {     
	        String str = "";
	        for(int i = 0; i < asistentes.size()-1; i++) {
	        	str += asistentes.get(i);
	        	str += ", ";
	        }
	        str += asistentes.get(asistentes.size()-1);
	        txtAsistentes.setText(str);
        }
    }
    
    public void actualizarExpositor() {
    	txtExpositor.setText(p.getExpositor().getNombre());
    }
    
    @Override
    public void editar() {
    	LeerPresentacion lp = new LeerPresentacion(getRegistro(), p);
    	lp.setHeader("Editando presentacion");
    	
    	PopUp popup = new PopUp(getStage(), lp);
    	popup.setTitle("Editar presentación");
        
        Presentacion retorno = (Presentacion)popup.showDialog();
        if (retorno != null) {
        	getRegistro().editar(p, retorno);
            getDashboard().fireEvent(new EventoPresentacion(EventoPresentacion.EDITAR_PRESENTACION, p, retorno));
        }
    }
    
    @Override
    public void eliminar() {
    	boolean opcion = Alerta.mostrarAlertaConfirmacion("¿Desea eliminar la presentación \"" + p.getNombre() + "\"?");
    	if(opcion) {
    		getRegistro().eliminar(p);
    		getDashboard().fireEvent(new EventoPresentacion(EventoPresentacion.ELIMINAR_PRESENTACION, p));
    	}
    }
}
