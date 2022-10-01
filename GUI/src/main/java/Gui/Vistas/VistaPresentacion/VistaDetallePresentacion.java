package Gui.Vistas.VistaPresentacion;

import Congreso.Presentacion;
import Gui.Vistas.Vista;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class VistaDetallePresentacion extends Vista {
    @FXML Text txtNombre, txtExpositor, txtDescripcion, txtFecha, 
    		   txtHora, txtDuracion, txtTotalAsistentes, txtAsistentes;
    
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
