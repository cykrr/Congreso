package Gui.Vistas.VistaPresentacion;

import java.util.LinkedList;

import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Registro;
import Gui.Alerta;
import Gui.EventoPresentacion;
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
	// Elementos XML
    @FXML Text txtNombre, txtExpositor, txtDescripcion, txtFecha, 
    		   txtHora, txtDuracion, txtTotalAsistentes, txtAsistentes;
    @FXML ImageView imgButtonAgregarAsistente, imgButtonEliminarAsistente;
    
    // Valor de retorno
    private Presentacion p;
    
    /**
     * Constructor de la clase VistaPrincipaPrecentacion.
     * Muestra una presentacion con su informacion y con iconos habilitados para actuar sobre ella.
     * Guarda los datos de la presentacion en atributos Text para mostrar con FXML.
     * @param p Es la presentacion a visualizar con sus datos en los parametros del atributo.
     * @param registro Es el atributo que posee todas las presentaciones, la coleccion que guarda presentaciones.
     * @param stage Atributo con el escenario que se muestra en ventana con las presentaciones.
     * @param dashboard Es el atributo con la scena de la ventana donde hay que agregar la vista principal presentacion. 
     * */
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
    }// Cierra constructor
    
    /**
     * Metodo que agrega un nuevo asistente de la presentacion.
     * */
	private void agregarAsistente() {
    	AgregarAsistentePresentacion ap = new AgregarAsistentePresentacion(getRegistro(), p, getStage());
    	ap.setHeader("Agregando asistente a presentación \"" + p.getNombre() + "\"");
    	ap.setTitle("Agregar asistente a presentación");
        
        Persona retorno = (Persona) ap.showDialog();	
        if(retorno != null) {
        	p.agregarAsistente(retorno);
        	getDashboard().fireEvent(new EventoPresentacion(EventoPresentacion.MODIFICAR_ASISTENTES, p));
        }
	}
    
	/**
     * Metodo que elimina un asistente de la presentacion.
     * */
	private void eliminarAsistente() {
    	EliminarAsistentePresentacion ep = new EliminarAsistentePresentacion(getRegistro(), p, getStage());
    	ep.setHeader("Eliminando asistente de presentación \"" + p.getNombre() + "\"");
    	ep.setTitle("Eliminar asistente de presentación");
        
        Persona retorno = (Persona) ep.showDialog();	
        if(retorno != null) {
        	p.eliminarAsistente(retorno);
        	getDashboard().fireEvent(new EventoPresentacion(EventoPresentacion.MODIFICAR_ASISTENTES, p));
        }	
	}
	
	/**
     * Metodo inicializado en el constructor.
     * Se encarga de registrar los asistentes que posee la presentacion en un unico texto, para
     * que se puedan mostrar en pantalla con el formato Text.
     * */
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
    
    /**
     * Metodo inicializado en el constructor.
     * Se encarga de registrar el expositor que posee la presentacion en un unico texto, para
     * que se pueda mostrar en pantalla con el formato Text.
     * */
    public void actualizarExpositor() {
    	txtExpositor.setText(p.getExpositor().getNombre());
    }
    
    /**
     * Recibe nuevos atributos para la presentacion a traves de la clase LeerPresentacion.
     * Se encarga de reemplazar los atributos de la presentacion con los leidos.
     * */
    @Override
    public void editar() {
    	LeerPresentacion lp = new LeerPresentacion(getRegistro(), getStage(), p);
    	lp.setHeader("Editando presentacion");
    	lp.setTitle("Editar presentación");
        
        Presentacion retorno = (Presentacion) lp.showDialog();
        if (retorno != null) {
        	getRegistro().editarPresentacion(p, retorno);
            getDashboard().fireEvent(new EventoPresentacion(EventoPresentacion.EDITAR_PRESENTACION, p, retorno));
        }
    }
    
    /**
     * Muestra por pantalla una alerta de confirmacion para confirmar si esta de acuerdo con la accion.
     * Se encarga de eliminar una presentacion completa del registro, ademas de eliminarlo de la pantalla.
     * */
    @Override
    public void eliminar() {
    	boolean opcion = Alerta.mostrarAlertaConfirmacion("¿Desea eliminar la presentación \"" + p.getNombre() + "\"?");
    	if(opcion) {
    		getRegistro().eliminarPresentacion(p);
    		getDashboard().fireEvent(new EventoPresentacion(EventoPresentacion.ELIMINAR_PRESENTACION, p));
    	}
    }
}
