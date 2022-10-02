package Gui.Vistas.VistaExpositor;

import Congreso.Expositor;
import Congreso.Registro;
import Congreso.excepciones.NullExpositorException;
import Gui.Alerta;
import Gui.EventoExpositor;
import Gui.Vistas.VistaPrincipal;
import Gui.Vistas.Dashboard.Dashboard;
import Gui.Vistas.LeerExpositor.LeerExpositor;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VistaPrincipalExpositor extends VistaPrincipal implements VistaPrincipal.Manipulable {
	// Elementos XML
	@FXML Text txtNombre, txtEdad, txtFono, txtCorreo, txtPais, txtOcupacion;
    
    // Valor de retorno
    private Expositor e;
    
    /**
     * Constructor de la clase VistaPrincipalExpositor.
     * Muestra un asistente con su informacion y con iconos habilitados para actuar sobre dicho asistente.
     * Guarda los datos del asistente en atributos Text para mostrar con FXML.
     * @param p Es la asistente a visualizar con sus datos en los parametros del atributo.
     * @param registro Es el atributo que posee todas las presentaciones, la coleccion que guarda asistentes.
     * @param stage Atributo con el escenario que se muestra en ventana con los asistentes.
     * @param dashboard Es el atributo con la escena de la ventana donde hay que agregar la vista principal persona. 
     * */
    public VistaPrincipalExpositor(Expositor e, Registro registro, Stage stage, Dashboard dashboard) {
        super(registro, stage, dashboard, "/vistas/VistaPrincipalExpositor.fxml");
        this.e = e;
    	
        txtNombre.setText(e.getNombre());
        txtEdad.setText(Integer.toString(e.getEdad()));
        txtFono.setText(Long.toString(e.getFono()));
        txtCorreo.setText(e.getCorreo());
        txtPais.setText(e.getPais());
        txtOcupacion.setText(e.getOcupacion());
    }
    
    /**
     * Recibe nuevos atributos para el expositor a traves de la clase LeerExpositor.
     * Se encarga de reemplazar los atributos del expositor con los leidos.
     * */
	@Override
	public void editar() {
    	LeerExpositor le = new LeerExpositor(getRegistro(), getStage(), e);
    	le.setHeader("Editando expositor");
    	le.setTitle("Editar expositor");
            
        Expositor retorno = (Expositor) le.showDialog();
        if (retorno != null) {
        	try {
				getRegistro().editarExpositor(e, retorno);
			} catch (NullExpositorException err) {
				err.printStackTrace();
			}
            getDashboard().fireEvent(new EventoExpositor(EventoExpositor.EDITAR_EXPOSITOR, e, retorno));
        }
	}
	
    /**
     * Muestra por pantalla una alerta de confirmacion para confirmar si esta de acuerdo con la accion.
     * Se encarga de eliminar un expositor por completo del registro de expositores, ademas de eliminarlo de la pantalla.
     * */
	@Override
	public void eliminar() {
		if(getRegistro().expositorEnPresentacion(e)) {
			Alerta.mostrarAlertaError(
					"Error al eliminar expositor \"" + e.getNombre() + "\"",
					"Expositor presenta en una o más presentaciones");
			return;
		}
		
    	boolean opcion = Alerta.mostrarAlertaConfirmacion("¿Desea eliminar al expositor \"" + e.getNombre() + "\"?");
    	if(opcion) {
    		getRegistro().eliminarExpositor(e);
    		getDashboard().fireEvent(new EventoExpositor(EventoExpositor.ELIMINAR_EXPOSITOR, e));
    	}
	}
}