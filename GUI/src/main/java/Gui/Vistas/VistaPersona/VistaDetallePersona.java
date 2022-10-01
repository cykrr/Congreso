package Gui.Vistas.VistaPersona;

import Congreso.Persona;
import Gui.Vistas.Vista;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class VistaDetallePersona extends Vista {
    @FXML Text txtNombre, txtEdad, txtFono, txtCorreo;
    
    public VistaDetallePersona(Persona p) {
        super("/vistas/VistaDetallePersona.fxml");
    	
        txtNombre.setText(p.getNombre());
        txtEdad.setText(Integer.toString(p.getEdad()));
        txtFono.setText(Long.toString(p.getFono()));
        txtCorreo.setText(p.getCorreo());
    }
}
