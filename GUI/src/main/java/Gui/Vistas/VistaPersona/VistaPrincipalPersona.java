package Gui.Vistas.VistaPersona;

import Congreso.Persona;
import Congreso.Registro;
import Gui.Alerta;
import Gui.EventoPersona;
import Gui.Vistas.VistaPrincipal;
import Gui.Vistas.Dashboard.Dashboard;
import Gui.Vistas.LeerAsistente.LeerAsistente;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VistaPrincipalPersona extends VistaPrincipal implements VistaPrincipal.Manipulable {
    @FXML Text txtNombre, txtEdad, txtFono, txtCorreo;
    
    private Persona p;
    
    /**
     * Constructor de la clase VistaPrincipaPersona
     * Muestra un asistente con su informacion y con iconos habilitados para actuar sobre dicho asistente.
     * Guarda los datos del asistente en atributos Text para mostrar con FXML.
     * @param p Es la asistente a visualizar con sus datos en los parametros del atributo.
     * @param registro Es el atributo que posee todas las presentaciones, la coleccion que guarda asistentes.
     * @param stage Atributo con la escenario que se muestra en ventana con los asistentes.
     * @param dashboard Es el atributo con la escena de la ventana donde hay que agregar la vista principal persona. 
     * */
    public VistaPrincipalPersona(Persona p, Registro registro, Stage stage, Dashboard dashboard) {
        super(registro, stage, dashboard, "/vistas/VistaPrincipalPersona.fxml");
        this.p = p;
    	
        txtNombre.setText(p.getNombre());
        txtEdad.setText(Integer.toString(p.getEdad()));
        txtFono.setText(Long.toString(p.getFono()));
        txtCorreo.setText(p.getCorreo());
    }
    
    /**
     * Recibe nuevos atributos para el asistente a traves de la clase LeerAsistente.
     * Se encarga de reemplazar los atributos del asistente con los leidos.
     * */
	@Override
	public void editar() {
    	LeerAsistente la = new LeerAsistente(getRegistro(), getStage(), p);
    	la.setHeader("Editando asistente");
    	la.setTitle("Editar asistente");
            
        Persona retorno = (Persona) la.showDialog();
        if (retorno != null) {
        	getRegistro().editarAsistente(p, retorno);
            getDashboard().fireEvent(new EventoPersona(EventoPersona.EDITAR_PERSONA, p, retorno));
        }		
	}
	
    /**
     * Muestra por pantalla una alerta de confirmacion para confirmar si esta de acuerdo con la accion-
     * Se encarga de eliminar un asistente por completa del registro de asistentes, ademas de eliminarlo de la pantalla.
     * */
	@Override
	public void eliminar() {
    	boolean opcion = Alerta.mostrarAlertaConfirmacion("¿Desea eliminar al asistente \"" + p.getNombre() + "\"?");
    	if(opcion) {
    		getRegistro().eliminarAsistente(p);
    		getDashboard().fireEvent(new EventoPersona(EventoPersona.ELIMINAR_PERSONA, p));
    	}
	}
}
