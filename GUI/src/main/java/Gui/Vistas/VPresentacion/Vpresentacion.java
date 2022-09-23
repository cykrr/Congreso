package Gui.Vistas.VPresentacion;

import java.io.IOException;
import java.util.LinkedList;

import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Registro;
import Gui.Alerta;
import Gui.EventoPresentacion;
import Gui.Vistas.PopUp;
import Gui.Vistas.Dashboard.Dashboard;
import Gui.Vistas.LeerPresentacion.LeerPresentacion;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Vpresentacion extends VBox {
    @FXML Text txtNombre, txtExpositor, txtDescripcion, txtFecha, txtHora, txtDuracion, txtTotalAsistentes, txtAsistentes;
    @FXML ImageView imgButtonMostrar, imgButtonEditar, imgButtonEliminar;
    @FXML VBox boxVistaExtendida;
    
    private Dashboard dashboard;
    private Registro registro;
    private Stage stage;
    private Presentacion p;
    
    public Vpresentacion(Presentacion p, Registro registro, Stage stage, Dashboard dashboard) {
        super();
        this.dashboard = dashboard;
        this.registro = registro;
        this.stage = stage;
        this.p = p;
        
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
        } else {     
	        String str = "";
	        for(int i = 0; i < asistentes.size()-1; i++) {
	        	str += asistentes.get(i);
	        	str += ", ";
	        }
	        str += asistentes.get(asistentes.size()-1);
	        txtAsistentes.setText(str);
        }
        
        imgButtonMostrar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                alternarVistaExtendida();
                event.consume();
            }
        });
        
        imgButtonEditar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                editarPresentacion();
                event.consume();
            }
        });
        
        imgButtonEliminar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                eliminarPresentacion();
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
    
    public boolean estaExtendida() {
    	return boxVistaExtendida.isVisible();
    }
    
    public void editarPresentacion() {
    	LeerPresentacion lp = new LeerPresentacion(registro, p);
    	PopUp popup = new PopUp(stage, lp);
    	popup.setTitle("Editar presentación");
            
        Presentacion retorno = (Presentacion)popup.showDialog();
        if (retorno != null) {
        	registro.editarPresentacion(p, retorno);
            dashboard.fireEvent(new EventoPresentacion(EventoPresentacion.EDITAR_PRESENTACION, p, retorno));
        }
    }
    
    public void eliminarPresentacion() {
    	boolean opcion = Alerta.mostrarAlertaConfirmacion("¿Desea eliminar esta presentación?");
    	if(opcion) {
    		registro.eliminarPresentacion(p);
    		dashboard.fireEvent(new EventoPresentacion(EventoPresentacion.ELIMINAR_PRESENTACION, p));
    	}
    }
}
