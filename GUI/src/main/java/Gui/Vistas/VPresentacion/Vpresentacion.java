package Gui.Vistas.VPresentacion;

import java.io.IOException;
import java.util.LinkedList;

import Congreso.Persona;
import Congreso.Presentacion;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Vpresentacion extends VBox {
    @FXML Text txtNombre, txtExpositor, txtDescripcion, txtFecha, txtHora, txtDuracion, txtTotalAsistentes, txtAsistentes;
    @FXML ImageView imgButton;
    @FXML VBox boxVistaExtendida;
    
    public Vpresentacion(Presentacion p) {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/vPresentacion.fxml"));
        fxmlLoader.setController(this);
        Node n = null;

        try {
            n = fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.getChildren().add(n);
    	
        txtNombre.setText(p.getNombre());
        txtExpositor.setText(p.getExpositor().getNombre());
        txtDescripcion.setText(p.getDescripcion());
        txtFecha.setText(p.getStringFecha());
        txtHora.setText(p.getStringHora());
        txtDuracion.setText(Integer.toString(p.getDuracion()) + " minutos");
        
        LinkedList<Persona> asistentes = p.getAsistentes();
        txtTotalAsistentes.setText(Integer.toString(asistentes.size()));
        
        if(asistentes.size() == 0) {
        	txtAsistentes.setText("Ninguno");
        	return;
        }
        
        String str = "";
        for(int i = 0; i < asistentes.size()-1; i++) {
        	str += asistentes.get(i);
        	str += ", ";
        }
        str += asistentes.get(asistentes.size()-1);
        txtAsistentes.setText(str);
        
        imgButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                alternarVistaExtendida();
                event.consume();
            }
        });
        
        boxVistaExtendida.setManaged(false);
        boxVistaExtendida.setVisible(false);
    }
    
    public void alternarVistaExtendida() {
    	if(boxVistaExtendida.isVisible()) {
            boxVistaExtendida.setManaged(false);
            boxVistaExtendida.setVisible(false);
    	} else {
            boxVistaExtendida.setManaged(true);
            boxVistaExtendida.setVisible(true);
    	}
    }
}
