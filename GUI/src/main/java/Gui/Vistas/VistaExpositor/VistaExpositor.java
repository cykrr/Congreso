package Gui.Vistas.VistaExpositor;

import Congreso.Expositor;
import Congreso.Registro;
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
        	getRegistro().editarExpositor(e, retorno);
            getDashboard().fireEvent(new EventoExpositor(EventoExpositor.EDITAR_EXPOSITOR, e, retorno));
        }
	}

	@Override
	public void eliminar() {
    	boolean opcion = Alerta.mostrarAlertaConfirmacion("¿Desea eliminar al expositor \"" + e.getNombre() + "\"?");
    	if(opcion) {
    		getRegistro().eliminarExpositor(e);
    		getDashboard().fireEvent(new EventoExpositor(EventoExpositor.ELIMINAR_EXPOSITOR, e));
    	}
	}
}