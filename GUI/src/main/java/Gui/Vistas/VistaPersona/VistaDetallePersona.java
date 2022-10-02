package Gui.Vistas.VistaPersona;

import Congreso.Persona;
import Gui.Vistas.Vista;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class VistaDetallePersona extends Vista {
	// Elementos XML
	@FXML Text txtNombre, txtEdad, txtFono, txtCorreo;
    
    /**
     * Constructor de la clase VistaDetallePersona.
     * Muestra un asistente con su informacion.
     * Guarda los datos de el asistente en atributos Text para mostrar con FXML.
     * @param p Es el asistente a visualizar con sus datos.
     * */
    public VistaDetallePersona(Persona p) {
        super("/vistas/VistaDetallePersona.fxml");
    	
        txtNombre.setText(p.getNombre());
        txtEdad.setText(Integer.toString(p.getEdad()));
        txtFono.setText(Long.toString(p.getFono()));
        txtCorreo.setText(p.getCorreo());
    }
}
