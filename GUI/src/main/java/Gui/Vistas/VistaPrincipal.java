package Gui.Vistas;

import Congreso.Registro;
import Gui.Vistas.Dashboard.Dashboard;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VistaPrincipal extends Vista {  
    @FXML ImageView imgButtonEditar, imgButtonEliminar;
    
    private Dashboard dashboard;
    private Registro registro;
    private Stage stage;
    private VBox box;
    
    public interface Manipulable {
        public void editar();
        public void eliminar();
    }
    
    public VistaPrincipal(Registro registro, Stage stage, Dashboard dashboard, String fileName) {
        super(fileName);
        this.dashboard = dashboard;
        this.registro = registro;
        this.stage = stage;
        this.box = this;
        
        imgButtonEditar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((Manipulable)box).editar();
                event.consume();
            }
        });
        
        imgButtonEliminar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((Manipulable)box).eliminar();
                event.consume();
            }
        });
        
    }
    
    public Registro getRegistro() {
    	return registro;
    }
    
    public Dashboard getDashboard() {
    	return dashboard;
    }
    
    public Stage getStage() {
    	return stage;
    }
}
