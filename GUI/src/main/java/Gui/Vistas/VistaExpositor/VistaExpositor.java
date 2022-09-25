package Gui.Vistas.VistaExpositor;

import Congreso.Expositor;
import Congreso.Registro;
import Gui.Vistas.Vista;
import Gui.Vistas.Dashboard.Dashboard;
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

	}

	@Override
	public void eliminar() {

	}
}