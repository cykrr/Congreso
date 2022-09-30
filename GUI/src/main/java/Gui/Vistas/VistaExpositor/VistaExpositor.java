package Gui.Vistas.VistaExpositor;

import Congreso.Expositor;
import Congreso.Presentacion;
import Congreso.Registro;
import Congreso.excepciones.NullExpositorException;
import Gui.Alerta;
import Gui.EventoExpositor;
import Gui.EventoPersona;
import Gui.Vistas.PopUp;
import Gui.Vistas.Vista;
import Gui.Vistas.Dashboard.Dashboard;
import Gui.Vistas.LeerExpositor.LeerExpositor;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VistaExpositor extends Vista implements Vista.Manipulable {
    @FXML Text txtNombre, txtEdad, txtFono, txtCorreo, txtPais, txtOcupacion;
    
    private Expositor e;
    
    public VistaExpositor(Expositor e, Registro registro, Stage stage, Dashboard dashboard) {
        super(registro, stage, dashboard, "/vistas/vExpositor.fxml");
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
    	LeerExpositor le = new LeerExpositor(getRegistro(), e);
    	le.setHeader("Editando expositor");
    	
    	PopUp popup = new PopUp(getStage(), le);
    	popup.setTitle("Editar expositor");
            
        Expositor retorno = (Expositor)popup.showDialog();
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
		for(int i = 0; i < getRegistro().getPresentaciones().size(); i++) {
			Presentacion p = getRegistro().getPresentaciones().get(i);
			if(p.getExpositor() == e) {
				Alerta.mostrarAlertaError(
						"Error al eliminar expositor \"" + e.getNombre() + "\"",
						"Expositor presenta en una o más presentaciones");
				return;
			}
		}
		
    	boolean opcion = Alerta.mostrarAlertaConfirmacion("¿Desea eliminar al expositor \"" + e.getNombre() + "\"?");
    	if(opcion) {
    		getRegistro().eliminarExpositor(e);
    		getDashboard().fireEvent(new EventoExpositor(EventoExpositor.ELIMINAR_EXPOSITOR, e));
    	}
	}
}