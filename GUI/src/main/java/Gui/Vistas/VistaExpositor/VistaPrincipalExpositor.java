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
    @FXML Text txtNombre, txtEdad, txtFono, txtCorreo, txtPais, txtOcupacion;
    
    private Expositor e;
    
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