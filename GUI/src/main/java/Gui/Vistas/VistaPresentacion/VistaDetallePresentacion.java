package Gui.Vistas.VistaPresentacion;

import Congreso.Presentacion;
import Gui.Vistas.Vista;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class VistaDetallePresentacion extends Vista {
	// Elementos XML
	@FXML Text txtNombre, txtExpositor, txtDescripcion, txtFecha, 
    		   txtHora, txtDuracion, txtTotalAsistentes, txtAsistentes;
    
    /**
     * Constructor de la clase VistaDetallePresentacion.
     * Muestra una presentacion con su informacion.
     * Guarda los datos de la presentacion en atributos Text para mostrar con FXML.
     * @param p Es la presentacion a visualizar con sus datos.
     * */
    public VistaDetallePresentacion(Presentacion p) {
        super("/vistas/VistaDetallePresentacion.fxml");  
        
        txtNombre.setText(p.getNombre());
        txtExpositor.setText(p.getExpositor().getNombre());
        txtDescripcion.setText(p.getDescripcion());
        txtFecha.setText(p.getStringFecha());
        txtHora.setText(p.getStringHora());
        txtDuracion.setText(Integer.toString(p.getDuracion()) + " minutos");
    }

}
